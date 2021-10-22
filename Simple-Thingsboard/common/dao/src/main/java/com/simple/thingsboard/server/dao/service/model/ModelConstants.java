package com.simple.thingsboard.server.dao.service.model;

import java.util.UUID;

public class ModelConstants {
    public static final UUID NULL_UUID = UUID.fromString("13814000-1dd2-11b2-8080-808080808080");
   // 实体映射 公共部分
    public static final String ID_PROPERTY = "id";
    public static final String CREATED_TIME_PROPERTY = "created_time";
    public static final String CUSTOMER_ID_PROPERTY = "customer_id";
    public static final String ADDITIONAL_INFO_PROPERTY = "additional_info";
    public static final String SEARCH_TEXT_PROPERTY = "search_text";

    //资产
    public static final String ASSET_COLUMN_FAMILY_NAME = "asset";
    public static final String ASSET_CUSTOMER_ID_PROPERTY = CUSTOMER_ID_PROPERTY;
    public static final String ASSET_NAME_PROPERTY = "name";
    public static final String ASSET_TYPE_PROPERTY = "type";
    public static final String ASSET_LABEL_PROPERTY = "label";
    public static final String ASSET_ADDITIONAL_INFO_PROPERTY = ADDITIONAL_INFO_PROPERTY;
}
