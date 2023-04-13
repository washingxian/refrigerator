package com.szu.refrigerator.dto.controllerDto.homepage.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomepageInfoResultDto {

    /**
     * 轮播图路径列表
     */
    List<String> cycleImages;

    /**
     * 当前冰箱名字
     */
    String currentFridgeName;

    /**
     * 过期物品类型数量
     */
    int expiredNum;


    /**
     * 临期物品类型数量
     */
    int closeToExpiredNum;

    /**
     * 未读信息数量
     */
    int notReadMsgNum;

    /**
     * 最新信息
     */
    String latestNews;
}
