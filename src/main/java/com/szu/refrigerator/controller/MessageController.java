package com.szu.refrigerator.controller;

import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.message.result.GetAllMsgResultDto;
import org.springframework.web.bind.annotation.*;

/**
 * 消息模块
 *
 * @author 86188
 * @date 2023/04/09
 */
@RestController
@RequestMapping("msg")
public class MessageController {

    /**
     * 获取指定冰箱所有信息(v4)
     *
     * @param fid   冰箱Id
     * @param token token
     * @return {@link R}<{@link GetAllMsgResultDto}>
     */
    @GetMapping("getAll")
    R<GetAllMsgResultDto> getAllMsg(int fid, @RequestHeader("token")String token){
        return  null;
    }


    /**
     * 一键设置所有信息已读
     *
     * @param fid   冰箱Id
     * @param token token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PostMapping("setAllRead")
    R<MSG_DATA> setALlRead(int fid, @RequestHeader("token")String token){
        return  null;
    }



    /**
     * 一键删除所有已读（v4)
     *
     * @param fid   冰箱Id
     * @param token token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @DeleteMapping("delAllRead")
    R<MSG_DATA> deleteAllRead(int fid, @RequestHeader("token")String token){
        return null;
    }



}
