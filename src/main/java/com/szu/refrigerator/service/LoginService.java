package com.szu.refrigerator.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szu.refrigerator.common.BaseContext;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.mapper.UserMapper;
import com.szu.refrigerator.dto.controllerDto.account.param.LoginParamDto;
import com.szu.refrigerator.dto.controllerDto.account.result.LoginResultDto;
import com.szu.refrigerator.dto.wxApiDto.resultDto.WxLoginResultDto;
import com.szu.refrigerator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;
    @Value("${AppID}")
    String appID;
    @Value("${AppSecret}")
    String appSecret;

    /**
     * 登录
     *
     * @param loginParamDto 登录参数dto
     * @return {@link R}<{@link LoginResultDto}>
     */
    public   R<LoginResultDto> login(LoginParamDto loginParamDto){
      HashMap<String,Object> params = new HashMap<>();
      params.put("appid",appID);
      params.put("secret",appSecret);
      params.put("js_code",loginParamDto.getCode());
      params.put("grant_type","authorization_code");


      /**
       * 调用微信api获取用户openid
       */
      String res = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", params);
      WxLoginResultDto wxLoginResultDto = JSONObject.parseObject(res, WxLoginResultDto.class);

      if(wxLoginResultDto.getOpenid()==null){
          return  R.error("登录失败");
      }

      User user = userMapper.selectOne(new QueryWrapper<User>().eq("uid",wxLoginResultDto.getOpenid()));

      /**
       * 数据库中没有该用户，即用户未注册
       */
      if(user==null){
          /**
           * 进行用户注册，用户id为openid，头像初始化为默认头像，其他信息待用户自己填写
           */
          user  = User.builder()
                  .uid(wxLoginResultDto.getOpenid())
                  .avatar( "/images/initAvatar.png")
                  .build();
          userMapper.insert(user);
      }

      String uid = user.getUid();
      BaseContext.setCurrentId(uid);
      return R.success(LoginResultDto.builder().baseInfo(user).build());
  }
}
