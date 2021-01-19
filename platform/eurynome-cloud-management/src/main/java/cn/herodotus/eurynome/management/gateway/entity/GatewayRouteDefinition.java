/*
 * Copyright (c) 2019-2020 the original author or authors.
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
 * Module Name: eurynome-cloud-management
 * File Name: GatewayRouteDefinition.java
 * Author: gengwei.zheng
 * Date: 2020/6/9 下午2:18
 * LastModified: 2020/6/9 下午1:20
 */

package cn.herodotus.eurynome.management.gateway.entity;

import cn.herodotus.eurynome.data.base.entity.BaseSysEntity;
import cn.herodotus.eurynome.management.gateway.entity.jpa.GatewayFilterDefinitionJsonListConverter;
import cn.herodotus.eurynome.management.gateway.entity.jpa.GatewayPredicateDefinitionJsonListConverter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 网关路由自定义管理实体
 *
 * @author gengwei.zheng
 */
@Entity
@Table(name = "gateway_definitions", indexes = {@Index(name = "gateway_definitions_gid_idx", columnList = "id")})
public class GatewayRouteDefinition extends BaseSysEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", length = 64)
    private String id;

    @Convert(converter = GatewayPredicateDefinitionJsonListConverter.class)
    @Column(name = "predicates", columnDefinition = "TEXT")
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    @Convert(converter = GatewayFilterDefinitionJsonListConverter.class)
    @Column(name = "filters", columnDefinition = "TEXT")
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    @Column(name = "uri", length = 1024)
    private String uri;

    /**
     * Order 是数据库关键字
     * int 和 Integer 差别还是有点大，Integer会抛空
     */
    @Column(name = "filter_order")
    private Integer order = 0;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getLinkedProperty() {
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GatewayPredicateDefinition> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<GatewayPredicateDefinition> predicates) {
        this.predicates = predicates;
    }

    public List<GatewayFilterDefinition> getFilters() {
        return filters;
    }

    public void setFilters(List<GatewayFilterDefinition> filters) {
        this.filters = filters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GatewayRouteDefinition that = (GatewayRouteDefinition) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("uri", uri)
                .append("order", order)
                .toString();
    }
}