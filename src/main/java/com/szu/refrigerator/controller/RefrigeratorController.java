package com.szu.refrigerator.controller;


import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.dto.controllerDto.fridge.param.InviteFriendToManageFridgeParamDto;
import com.szu.refrigerator.dto.controllerDto.fridge.result.GetAllRecordResultDto;
import com.szu.refrigerator.dto.controllerDto.fridge.result.GetUserRefrigeratorResultDto;
import com.szu.refrigerator.dto.controllerDto.fridge.result.getCurrentRefrigeratorStructure;
import com.szu.refrigerator.entity.Refrigerator;
import com.szu.refrigerator.service.RefrigeratorService;
import com.szu.refrigerator.service.UserRefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 冰箱控制器
 *
 * @author 冼铧城
 * @date 2023/03/07
 */
@RestController
@RequestMapping("/refrigerator")
public class RefrigeratorController {

    @Autowired
    RefrigeratorService refrigeratorService;

    @Autowired
    UserRefrigeratorService userRefrigeratorService;

    @Autowired
    ConstantFromYml constantFromYml;


    /**
     * 新建冰箱
     *
     * @param refrigerator 冰箱
     * @param uid          用户Id
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PostMapping
    public R<MSG_DATA> save(@RequestBody Refrigerator refrigerator, @RequestHeader("token") String uid) {
        refrigeratorService.saveWithUser(refrigerator, uid);
        return R.success(MSG_DATA.成功);
    }


    /**
     * 通过uid获取用户绑定的冰箱的信息
     *
     * @param uid uid
     * @return {@link R}<{@link List}<{@link Refrigerator}>>
     */
    @GetMapping
    public R<List<GetUserRefrigeratorResultDto>> getByUid(@RequestHeader("token") String uid) {



        List<GetUserRefrigeratorResultDto> userRefrigerators = refrigeratorService.getUserRefrigerator(uid);

        userRefrigerators.forEach(o->o.setBackgroundUrl(constantFromYml.getModelImgPath()+o.getBackgroundUrl()));

        return R.success(userRefrigerators);

    }

    /**
     * 修改冰箱信息 (迭代2）
     * @param refrigerator 冰箱
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PutMapping
    public R<MSG_DATA> updateFridge(@RequestBody Refrigerator refrigerator){

      return   refrigeratorService.updateFridge(refrigerator);

    }





    /**
     * 通过冰箱Id解绑冰箱(迭代2）
     *
     * @param fid 冰箱Id
     * @return {@link R}<{@link MSG_DATA}>
     */
    @DeleteMapping("/{fid}")
    public R<MSG_DATA> unboundById(@PathVariable Integer fid,@RequestHeader("token")String token){
        refrigeratorService.unboundById(fid,token);
        return R.success(MSG_DATA.成功);
    }


    /**
     * 设置默认冰箱(迭代2)
     *
     * @param fid   冰箱Id
     * @param token token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PutMapping("/setDefaultFridge/{fid}")
    public R<MSG_DATA>  setDefaultFridge(@PathVariable Integer fid , @RequestHeader("token")String token){

        refrigeratorService.setDefaultFridge(fid,token);
        return  R.success(MSG_DATA.成功);
    }

    /**
     * 获取当前冰箱结构(v3）
     *
     * @param token token
     * @return {@link R}<{@link List}<{@link getCurrentRefrigeratorStructure}>>
     */
    @GetMapping("/getCurrentRefrigeratorStructure")
    public  R<List<getCurrentRefrigeratorStructure>> getCurrentRefrigeratorStructure(@RequestHeader("token")String token){


        return  refrigeratorService.getCurrentRefrigeratorStructure(token);
  }



    /**
     * 邀请朋友来管理冰箱(v4）
     *
     * @param dto dto
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PostMapping("invite")
  public  R<MSG_DATA> inviteFriendToManageFridge(@RequestBody InviteFriendToManageFridgeParamDto dto){
        return  null;
  }



    /**
     * 获取指定冰箱所有记录（v4）
     * @param fid 冰箱Id
     * @return {@link R}<{@link GetAllRecordResultDto}>
     */
    @GetMapping("getAllRecord")
    public R<List<GetAllRecordResultDto>> getAllRecord(int fid){
        return  null;
  }




}

