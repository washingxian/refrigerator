<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    </#if>
<#--    <#if baseResultMap>-->
<#--        <!-- 通用查询映射结果 &ndash;&gt;-->
<#--        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">-->
<#--            <#list table.fields as field>-->
<#--                <#if field.keyFlag>&lt;#&ndash;生成主键排在第一位&ndash;&gt;-->
<#--                    <id column="${field.name}" property="${field.propertyName}" />-->
<#--                </#if>-->
<#--            </#list>-->
<#--            <#list table.commonFields as field>&lt;#&ndash;生成公共字段 &ndash;&gt;-->
<#--                <result column="${field.name}" property="${field.propertyName}" />-->
<#--            </#list>-->
<#--            <#list table.fields as field>-->
<#--                <#if !field.keyFlag>&lt;#&ndash;生成普通字段 &ndash;&gt;-->
<#--                    <result column="${field.name}" property="${field.propertyName}" />-->
<#--                </#if>-->
<#--            </#list>-->
<#--        </resultMap>-->

<#--    </#if>-->
<#--    <#if baseColumnList>-->
<#--        <!-- 通用查询结果列 &ndash;&gt;-->
<#--        <sql id="Base_Column_List">-->
<#--            <#list table.fields as field>-->
<#--                `${field.annotationColumnName}` <#if field_has_next>,</#if>-->
<#--            </#list>-->
<#--        </sql>-->

<#--    </#if>-->

    <insert id="updateAll">
        insert  into  `${table.name}` (
        <#list table.fields as field>
            `${field.annotationColumnName}` <#if field_has_next>,</#if>
        </#list>
        )
        values
        <foreach collection="list" separator="," item="item">
            (
            <#list table.fields as field>
                ${r"#{item."}${field.annotationColumnName}} <#if field_has_next>,</#if>
            </#list>
            )
        </foreach>
        on duplicate key  update

        <#list table.fields as field>
        `${field.annotationColumnName}`= values(  `${field.annotationColumnName}`)<#if field_has_next>,</#if>
        </#list>
    </insert>
</mapper>