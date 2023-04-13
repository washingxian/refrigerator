import io

from PIL import Image
from flask import Flask, render_template, request, redirect
from tools.infer import predict_system as ocrUtil
import time


app = Flask(__name__)


@app.route("/ocr", methods=["GET", "POST"])
def ocr():
    print(8888888)
    file = request.files["file"]
    if not file:
        print("没有正确上传图片")
        return "你没有正确上传图片"

    img_bytes = file.read()
    img = Image.open(io.BytesIO(img_bytes))
    temp_file = "./temp/" + str(int(time.time() * 1000000)) + ".png"
    img.save(temp_file)
    print(temp_file)
    ocr_res = ocrUtil.ocr(temp_file)

    return {
        "data": ocr_res
    }


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
