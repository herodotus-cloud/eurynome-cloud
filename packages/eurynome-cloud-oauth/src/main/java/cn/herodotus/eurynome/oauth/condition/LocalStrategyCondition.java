/*
 * Copyright (c) 2019-2021 Gengwei Zheng (herodotus@aliyun.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project Name: eurynome-cloud
 * Module Name: eurynome-cloud-oauth
 * File Name: LocalStrategyCondition.java
 * Author: gengwei.zheng
 * Date: 2021/07/28 19:04:28
 */

package cn.herodotus.eurynome.oauth.condition;

import cn.herodotus.eurynome.rest.enums.DataAccessStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 本地数据访问策略条件定义 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/7/28 19:04
 */
public class LocalStrategyCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(LocalStrategyCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("herodotus.platform.data-access-strategy", DataAccessStrategy.LOCAL.name());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.contains(property, DataAccessStrategy.LOCAL.name());
        log.debug("[Eurynome] |- Condition [Local Strategy Condition] is [{}]", result);
        return result;
    }
}
