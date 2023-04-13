import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class test1 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://tmp/I2NwunyAZ4ZKdcb4997da5dbo0a59cbd028cabefo27c.jpeg");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[1024];
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\大学学习\\大三下\\软工实训\\后端代码\\refrigerator\\zTest\\a.png");
        while (inputStream.read(bytes)!=-1){
            fileOutputStream.write(bytes);@
        }
        fileOutputStream.close();
        inputStream.close();

    }
}
