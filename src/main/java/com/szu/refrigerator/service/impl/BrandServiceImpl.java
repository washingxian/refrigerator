package com.szu.refrigerator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.dto.controllerDto.brand.result.BrandListAlphabetDto;
import com.szu.refrigerator.dto.controllerDto.brand.result.BrandModelsDto;
import com.szu.refrigerator.entity.Brand;
import com.szu.refrigerator.mapper.BrandMapper;
import com.szu.refrigerator.entity.Model;
import com.szu.refrigerator.mapper.ModelMapper;
import com.szu.refrigerator.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.NonNull;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ConstantFromYml constantFromYml;

    /**
     *
     * 获取字符串的首字母，字符串可以是英文或中文
     */
    @NonNull
    private static String first(@Nullable String str) {
        if (str == null || str.equals("")) {
            return "#";
        }
        char ch = str.charAt(0);
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A') + "";
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch + "";
        }
        try {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            // 设置大小写格式
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            // 设置声调格式：
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(ch, defaultFormat);
                if (array != null) {
                    return array[0].charAt(0) + "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "#";
    }

    public R<List<BrandListAlphabetDto>> getBrandListAlphabet() {
        List<Brand> brands = list();



        List<BrandListAlphabetDto> result = new ArrayList<>();

        Map<Character, List<Brand>> map = new HashMap<>();

        for (Brand brand : brands) {

            /**
             * 拼接图片路径
             */
            brand.setImgSrc(constantFromYml.getBrandImgPath()+brand.getImgSrc());
            Character character = Character.toUpperCase(first(brand.getName()).charAt(0));

            if (!map.containsKey(character)) {
                map.put(character, new ArrayList<>());
            }

            map.get(character).add(brand);
        }

        map.forEach((ch, list) -> {

            BrandListAlphabetDto brandListAlphabetDto = BrandListAlphabetDto.builder()
                    .letter(ch)
                    .data(list)
                    .build();

            result.add(brandListAlphabetDto);

        });

        /// sort
        result.sort((a, b) -> {
            return a.getLetter() - b.getLetter();
        });

        return R.success(result);
    }


    public R<BrandModelsDto> getBrandModels(Integer bid) {
        Brand brand = baseMapper.selectById(bid);
        /**
         * 拼接字符串
         */
        brand.setImgSrc(constantFromYml.getBrandImgPath()+brand.getImgSrc());
        /**
         * 获取指定品牌所有型号
         */
        List<Model> models = modelMapper.selectList(new QueryWrapper<Model>().eq("bid", brand.getBid()));
        models.forEach(model -> model.setImgSrc(constantFromYml.getModelImgPath()+model.getImgSrc()));
        return R.success(
                BrandModelsDto.builder()
                .brand(brand)
                .models(models)
                .build());
    }

}
