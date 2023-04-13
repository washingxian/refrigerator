package com.szu.refrigerator.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.entity.UserTarget;
import com.szu.refrigerator.service.UserTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户目标控制器
 *
 * @author apple
 * @date 2023/03/07
 */
@RestController
@RequestMapping("/userTarget")
public class UserTargetController {

    @Autowired
    UserTargetService userTargetService;


    /**
     * 通过uid查询该用户的所有目标
     * @param uid
     * @return
     */
    @GetMapping
    public R<List<UserTarget>> getByUid(@RequestHeader("token") String uid){
        return R.success(userTargetService.list(new LambdaQueryWrapper<UserTarget>().eq(UserTarget::getUid,uid)));
    }

    /**
     * 修改用户目标
     * @param userTarget
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody UserTarget userTarget){
        userTargetService.updateById(userTarget);
        return R.success("用户目标已修改");
    }

    /**
     * 添加用户目标
     * @param userTarget
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody UserTarget userTarget){
        userTargetService.save(userTarget);
        return R.success("新增用户目标成功");
    }

    /**
     * 删除用户目标
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable Long id){
        userTargetService.remove(new LambdaQueryWrapper<UserTarget>().eq(UserTarget::getUt_id,id));
        return R.success("已删除用户目标");
    }

}

