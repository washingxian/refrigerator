package com.szu.refrigerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.dto.controllerDto.model.result.GetAllModelInfoResultDto;
import com.szu.refrigerator.entity.Model;
import com.szu.refrigerator.mapper.ModelMapper;
import com.szu.refrigerator.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模型服务impl
 *
 * @author 86188
 * @date 2023/03/08
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ConstantFromYml constantFromYml;

    /**
     * 获取所有冰箱型号信息
     *
     * @return {@link R}<{@link List}<{@link GetAllModelInfoResultDto}>>
     */
    public  R<List<GetAllModelInfoResultDto>> getAllModelInfo(){

        /**
         * 有点复杂的sql语句在xml里面实现了
         */
        List<GetAllModelInfoResultDto> allModelInfos = modelMapper.getAllModelInfo();

        /**
         * 数据库里面只存的图片的地址，这里把图片路径拼接上
         */
        allModelInfos.forEach(info-> info.getModelList().forEach(model ->
                model.setImgSrc(constantFromYml.getModelImgPath()+model.getImgSrc())));
        return  R.success(allModelInfos);
    }
}
