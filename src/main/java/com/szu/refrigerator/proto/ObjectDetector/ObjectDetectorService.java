package com.szu.refrigerator.proto.ObjectDetector;

import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

interface ExtendResponseObserver<T> extends StreamObserver<T> {
    List<DetectResult> getDetectResultList();
    Throwable getThrowable();
}

@Service
@Slf4j
public class ObjectDetectorService {

    private static final int ChunkSize = 64 * 1024;

    private static final int TimeOut = 42;

    @GrpcClient("object-detector-server")
    private Channel channel;


//    private final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
//            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
//            // needing certificates.
//            .usePlaintext(true)
//            .build();

    public List<DetectResult> detect(InputStream imageInputStream) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        ObjectDetectorGrpc.ObjectDetectorStub stub = ObjectDetectorGrpc.newStub(channel);

        ExtendResponseObserver<DetectResult> resultStreamObserver = new ExtendResponseObserver<DetectResult>() {

            final List<DetectResult> detectResultList = new ArrayList<>();

            Throwable throwable = null;

            public List<DetectResult> getDetectResultList() {
                return detectResultList;
            }

            public Throwable getThrowable() {
                return throwable;
            }

            @Override
            public void onNext(DetectResult detectResult) {
                if (detectResult != null) {
                    detectResultList.add(detectResult);
                }
            }

            @Override
            public void onError(Throwable inThrowable) {
                log.info(throwable.getLocalizedMessage());
                throwable = inThrowable;
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        };

        StreamObserver<ImageChunk> imageChunkStreamObserver = stub.detectImage(resultStreamObserver);


        try {
            /// send image bytes by chunkSize
            byte[] buffer = new byte[ChunkSize];
            int read;
            while ((read = imageInputStream.read(buffer, 0, ChunkSize)) > 0) {
                ImageChunk imageChunk = ImageChunk.newBuilder()
                        .setBuffer(ByteString.copyFrom(buffer, 0, read))
                        .build();

                imageChunkStreamObserver.onNext(imageChunk);
            }

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }


        imageChunkStreamObserver.onCompleted();

        boolean success = false;
        try {

            if (countDownLatch.await(TimeOut, TimeUnit.SECONDS)) {
                success = true;
            }

        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        if (success) {
            return resultStreamObserver.getDetectResultList();
        }

        log.error("detect time out");

        return null;
    }

}
