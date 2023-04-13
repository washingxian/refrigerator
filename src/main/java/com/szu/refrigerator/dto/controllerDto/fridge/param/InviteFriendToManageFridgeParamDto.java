package com.szu.refrigerator.dto.controllerDto.fridge.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InviteFriendToManageFridgeParamDto {
    /**
     * 朋友手机号
     */
    String phoneNumOfFriend;

    /**
     * 冰箱id
     */
    int fid;
}
