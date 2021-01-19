/*
 * Copyright (c) 2019-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * Project Name: eurynome-cloud
 * Module Name: eurynome-cloud-oauth
 * File Name: OauthConfiguration.java
 * Author: gengwei.zheng
 * Date: 2021/1/17 上午11:11
 * LastModified: 2021/1/17 上午11:11
 */

package cn.herodotus.eurynome.oauth.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Project: eurynome-cloud </p>
 * <p>File: OauthConfiguration </p>
 *
 * <p>Description: Oauth 包的启动配置 </p>
 * <p>
 * 注意：这里没有使用@Enable的形式，主要是考虑到启动顺序问题。OauthAutoConfiguration应该在SecurityAutoConfiguration之后配置
 *
 * @author : gengwei.zheng
 * @date : 2021/1/17 11:11
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {
        "cn.herodotus.eurynome.oauth.configuration",
        "cn.herodotus.eurynome.oauth.controller"
})
public class OauthAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("[Eurynome] |- Components [Herodotus OAuth] Auto Configure.");
    }
}