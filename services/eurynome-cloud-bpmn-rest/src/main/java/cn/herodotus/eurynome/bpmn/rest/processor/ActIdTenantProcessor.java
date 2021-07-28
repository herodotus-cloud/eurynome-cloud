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
 * Module Name: eurynome-cloud-bpmn-rest
 * File Name: ActIdTenantProcessor.java
 * Author: gengwei.zheng
 * Date: 2021/07/20 19:20:20
 */

package cn.herodotus.eurynome.bpmn.rest.processor;

import cn.herodotus.eurynome.bpmn.rest.entity.ActIdTenant;
import cn.herodotus.eurynome.bpmn.rest.service.ActIdTenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Debezium Tenant 数据处理器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/7/20 16:55
 */
@Component
public class ActIdTenantProcessor extends AbstractProcessor<ActIdTenant> {

    private static final Logger log = LoggerFactory.getLogger(ActIdTenantProcessor.class);

    private final ActIdTenantService actIdTenantService;

    @Autowired
    public ActIdTenantProcessor(ActIdTenantService actIdTenantService) {
        this.actIdTenantService = actIdTenantService;
    }

    @KafkaListener(topics = {"herodotus.public.sys_department"}, groupId = "herodotus.debezium")
    public void received(String body) {
        log.info("[Eurynome] |- Recived Debezium event message from [sys_department], content is : [{}]", body);
        this.execute(body);
    }

    @Override
    public void delete(ActIdTenant entity) {
        this.actIdTenantService.deleteById(entity.getId());
    }

    @Override
    public ActIdTenant saveOrUpdate(ActIdTenant entity) {
        return this.actIdTenantService.saveOrUpdate(entity);
    }
}
