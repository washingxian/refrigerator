import io

from PIL import Image
from flask import Flask, render_template, request, redirect
from tools.infer import predict_system as ocrUtil
import time


app = Flask(__name__)


@app.route("/ocr", methods=["GET", "POST"])
def ocr():
    return {
        "data": '''乐购超市
日期：2023年3月31日
时间：21:14
销售员工号：001
条目 货物 数量 价格 总计
1 牛奶 2升 ￥6/升 ￥12
2 鸡蛋 10个 ￥0.5 ￥5
3 奶酪 1块 ￥15 ￥15
4 果汁 1瓶 ￥10 ￥10
5 冰淇淋 1盒 ￥20 ￥20
总计： 62
付款方式：支付宝
'''
    }


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
