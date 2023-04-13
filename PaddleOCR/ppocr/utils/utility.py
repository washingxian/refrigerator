# Copyright (c) 2020 PaddlePaddle Authors. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import logging
import os
import imghdr
import cv2
import fitz


def print_dict(d, logger, delimiter=0):
    """
    Recursively visualize a dict and
    indenting acrrording by the relationship of keys.
    """
    for k, v in sorted(d.items()):
        if isinstance(v, dict):
            logger.info("{}{} : ".format(delimiter * " ", str(k)))
            print_dict(v, logger, delimiter + 4)
        elif isinstance(v, list) and len(v) >= 1 and isinstance(v[0], dict):
            logger.info("{}{} : ".format(delimiter * " ", str(k)))
            for value in v:
                print_dict(value, logger, delimiter + 4)
        else:
            logger.info("{}{} : {}".format(delimiter * " ", k, v))


def get_check_global_params(mode):
    check_params = ['use_gpu', 'max_text_length', 'image_shape', \
                    'image_shape', 'character_type', 'loss_type']
    if mode == "train_eval":
        check_params = check_params + [ \
            'train_batch_size_per_card', 'test_batch_size_per_card']
    elif mode == "test":
        check_params = check_params + ['test_batch_size_per_card']
    return check_params


def get_image_file_list(img_file):
    
    paths = img_file.split(",")
    imgs_lists = []
    for path in paths:
        if path is None or not os.path.exists(path):
            raise Exception("not found any img file in {}".format(path))

        img_end = {'jpg', 'bmp', 'png', 'jpeg', 'rgb', 'tif', 'tiff', 'gif', 'GIF'}
        if os.path.isfile(path) and imghdr.what(path) in img_end:
            imgs_lists.append(path)
        elif os.path.isdir(path):
            for single_file in os.listdir(path):
                file_path = os.path.join(path, single_file)
                if os.path.isfile(file_path) and imghdr.what(file_path) in img_end:
                    imgs_lists.append(file_path)
        elif path[path.rindex("."):len(path)] == ".pdf":
            imagePath = "./pdf2img"
            pdfDoc = fitz.open(path)
            page = pdfDoc[0]
            # 每个尺寸的缩放系数为1.3，这将为我们生成分辨率提高2.6的图像。
            # 此处若是不做设置，默认图片大小为：792X612, dpi=96
            zoom_x = 2  # (1.33333333-->1056x816)   (2-->1584x1224)
            zoom_y = 2
            mat = fitz.Matrix(zoom_x, zoom_y).prerotate(0)
            pix = page.get_pixmap(matrix=mat, alpha=False)
            if not os.path.exists(imagePath):  # 判断存放图片的文件夹是否存在
                os.makedirs(imagePath)  # 若图片文件夹不存在就创建
            # 将图片写入指定的文件夹内
            imagePath = imagePath + path[path.rindex("/"):path.rindex(".")] + ".jpg"
            pix.save(imagePath)
            imgs_lists.append(imagePath)



    if len(imgs_lists) == 0:
        raise Exception("not found any img file in {}".format(img_file))
    imgs_lists = sorted(imgs_lists)
    return imgs_lists


def check_and_read_gif(img_path):
    if os.path.basename(img_path)[-3:] in ['gif', 'GIF']:
        gif = cv2.VideoCapture(img_path)
        ret, frame = gif.read()
        if not ret:
            logger = logging.getLogger('ppocr')
            logger.info("Cannot read {}. This gif image maybe corrupted.")
            return None, False
        if len(frame.shape) == 2 or frame.shape[-1] == 1:
            frame = cv2.cvtColor(frame, cv2.COLOR_GRAY2RGB)
        imgvalue = frame[:, :, ::-1]
        return imgvalue, True
    return None, False