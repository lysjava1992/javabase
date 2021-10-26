package com.simple.thingsboard.server.common.data.asset;

import com.simple.thingsboard.server.common.data.SearchTextBasedWithAdditionalInfo;

import java.io.Serializable;
import java.util.UUID;

public class Asset extends SearchTextBasedWithAdditionalInfo implements Serializable {
    private static final long serialVersionUID = 3452572734117039666L;
    /**
     * 分配给的客户ID
     */
    private UUID customerId;
    /**
     * 客户组
     */
    private String customerTitle;
    /**
     * 资产名称
     */
    private String name;
    /**
     * 资产类型
     */
    private String type;
    /**
     * 自定义标签
     */
    private String label;

    public Asset() {
        super();
    }

    public Asset(UUID id) {
        super(id);
    }

    public Asset(Asset asset) {
        super(asset);
        this.customerId = asset.getCustomerId();
        this.customerTitle=asset.getCustomerTitle();
        this.name = asset.getName();
        this.type = asset.getType();
        this.label = asset.getLabel();
    }


    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getSearchText() {
        return getName();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Asset [customerId=");
        builder.append(customerId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type);
        builder.append(", label=");
        builder.append(label);
        builder.append(", additionalInfo=");
        builder.append(getAdditionalInfo());
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }
}
