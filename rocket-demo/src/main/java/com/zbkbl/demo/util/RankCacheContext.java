package com.zbkbl.demo.util;

/**
 * @author liuyang
 * @description
 * @date 2020/05/21 10:21
 **/
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RankCacheContext {
    private RankLatencyCache rankLatencyCache = new RankLatencyCache();
    private String currentStepName;

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RankLatencyCache {

        /**
         * 过滤耗时详情
         */
        private Map<String, Long> filterDetailLatency = new HashMap<>();

        /**
         * 模型排序耗时详情
         */
        private Map<String, Long> modelRankDetailLatency = new HashMap<>();

        /**
         * 二次排序耗时详情
         */
        private Map<String, Long> reRankDetailLatency = new HashMap<>();

        /**
         * 排序融合耗时详情
         */
        private Map<String, Long> rankFusionDetailLatency = new HashMap<>();

        /**
         * 模型排序总耗时
         */
        private long modelRankLatency;

        /**
         * 过滤总耗时
         */
        private long filterLatency;

        /**
         * 查询主动方特征耗时
         */
        private long activeFeatureLatency;

        /**
         * 调用被动方特征耗时
         */
        private long passiveFeatureLatency;

        /**
         * 二次排序总耗时
         */
        private long reRankLatency;

        /**
         * 模型融合耗时
         */
        private long modelFusionLatency;

        /**
         * 排序融合总耗时
         */
        private long rankFusionLatency;
    }
}
