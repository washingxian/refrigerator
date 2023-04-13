package com.szu.refrigerator.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.fridge.result.GetUserRefrigeratorResultDto;
import com.szu.refrigerator.dto.controllerDto.fridge.result.getCurrentRefrigeratorStructure;
import com.szu.refrigerator.entity.Refrigerator;
import com.szu.refrigerator.entity.UserRefrigerator;
import com.szu.refrigerator.mapper.RefrigeratorMapper;
import com.szu.refrigerator.mapper.UserRefrigeratorMapper;
import com.szu.refrigerator.service.RefrigeratorService;
import com.szu.refrigerator.service.UserRefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
@Service
public class RefrigeratorServiceImpl extends ServiceImpl<RefrigeratorMapper, Refrigerator> implements RefrigeratorService {
    @Autowired
    UserRefrigeratorService userRefrigeratorService;

   @Autowired
    RefrigeratorMapper refrigeratorMapper;

   @Autowired
    UserRefrigeratorMapper userRefrigeratorMapper;



    @Override
    public void saveWithUser(Refrigerator refrigerator,String uid) {
        this.save(refrigerator);
        /**
         * 将该用户的所有冰箱都设为不是默认
         */
        userRefrigeratorMapper.update(null,new UpdateWrapper<UserRefrigerator>()
                .eq("uid",uid)
                .set("fDefault",0)
        );

        UserRefrigerator userRefrigerator = new UserRefrigerator();
        userRefrigerator.setUid(uid);
        /**
         * 将新建的冰箱设为默认
         */
        userRefrigerator.setFDefault(true);
        userRefrigerator.setFid(refrigerator.getFid());


        userRefrigeratorService.save(userRefrigerator);
    }




    @Override
    public List<GetUserRefrigeratorResultDto> getUserRefrigerator(String uid) {


        /**
         * 获取指定用户所有冰箱
         * 实现细节在sql中
         */
        return refrigeratorMapper.getUserRefrigerator(uid);
    }

    @Override
    public R<MSG_DATA> setDefaultFridge(Integer fid, String token) {
        String uid = token;
        /**
         * 将用户所用冰箱设为非默认
         */
        userRefrigeratorMapper.update(null,new UpdateWrapper<UserRefrigerator>()
                .eq("uid",uid).set("fDefault",0));

        /**
         * 将指定用户的指定冰箱设为默认冰箱
         */
        userRefrigeratorMapper.update(null,new UpdateWrapper<UserRefrigerator>()
                .eq("fid",fid).eq("uid",uid).set("fDefault",1));

        return  R.success(MSG_DATA.成功);
    }

    @Override
    public R<MSG_DATA> updateFridge(Refrigerator refrigerator) {
        /**
         * 传过来的冰箱字段合法才修改
         */
         refrigeratorMapper.update(null,new UpdateWrapper<Refrigerator>()
                 .eq("fid",refrigerator.getFid())
                 .set(refrigerator.getName()!=null,"name",refrigerator.getName())
                 .set(refrigerator.getMid()!=0,"mid",refrigerator.getMid())
         );
         return R.success(MSG_DATA.成功);
    }

    @Override
    public R<MSG_DATA> unboundById(Integer fid, String token) {

        /**
         * 目前使用的token就是用户的id
         */
        String uid = token;

        /**
         * 获取要解绑的冰箱
         */
        UserRefrigerator userRefrigerator = userRefrigeratorMapper.selectOne(new QueryWrapper<UserRefrigerator>()
                .eq("fid", fid)
                .eq("uid", uid)
        );


        /**
         * 解绑冰箱
         */
        userRefrigeratorMapper.delete(new QueryWrapper<UserRefrigerator>()
                .eq("fid", fid)
                .eq("uid", uid)
        );



        /**
         * 如果解绑的冰箱是默认冰箱
         */
        if(userRefrigerator.getFDefault()){
            /**
             * 如果用户还有其他绑定的冰箱，随机挑一个作为默认冰箱
             */
            List<UserRefrigerator> userRefrigerators = userRefrigeratorMapper.selectList(new QueryWrapper<UserRefrigerator>()
                    .eq("uid",uid)
                    .last("limit 1"));
            if(userRefrigerators.size()>0){
                UserRefrigerator userRefrigerator1 = userRefrigerators.get(0);
               userRefrigerator1.setFDefault(true);
                userRefrigeratorMapper.update(userRefrigerator1,new QueryWrapper<UserRefrigerator>().eq("uid",userRefrigerator1.getUid()).eq("fid",userRefrigerator1.getFid()));
            }
        }



        return  R.success(MSG_DATA.成功);


    }

    @Override
    public R<List<getCurrentRefrigeratorStructure>> getCurrentRefrigeratorStructure(String token) {
        String uid = token;
        List<getCurrentRefrigeratorStructure> currentRefrigeratorStructure = JSONObject.parseArray(refrigeratorMapper.getCurrentRefrigeratorStructure(uid), getCurrentRefrigeratorStructure.class);
        return   R.success(currentRefrigeratorStructure);
    }


}
