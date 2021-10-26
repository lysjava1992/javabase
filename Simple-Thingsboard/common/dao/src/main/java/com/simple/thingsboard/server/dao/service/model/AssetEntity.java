package com.simple.thingsboard.server.dao.service.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.simple.thingsboard.server.common.data.asset.Asset;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = ModelConstants.ASSET_COLUMN_FAMILY_NAME)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class AssetEntity extends BaseSqlEntity<Asset> {

    @Column(name = ModelConstants.ASSET_CUSTOMER_ID_PROPERTY)
    private UUID customerId;

    @Column(name = ModelConstants.ASSET_NAME_PROPERTY)
    private String name;

    @Column(name = ModelConstants.ASSET_TYPE_PROPERTY)
    private String type;

    @Column(name = ModelConstants.ASSET_LABEL_PROPERTY)
    private String label;

    @Column(name = ModelConstants.SEARCH_TEXT_PROPERTY)
    private String searchText;

    @Type(type = "json")
    @Column(name = ModelConstants.ASSET_ADDITIONAL_INFO_PROPERTY)
    private JsonNode additionalInfo;

    public AssetEntity() {
        super();
    }

    public AssetEntity(Asset asset) {
        if (asset.getId() != null) {
            this.setUuid(asset.getId());
            this.searchText=asset.getName();
        }
        this.setCreatedTime(asset.getCreatedTime());
        if (asset.getCustomerId() != null) {
            this.customerId = asset.getCustomerId();
        }
        this.name = asset.getName();
        this.type = asset.getType();
        this.label = asset.getLabel();
        this.additionalInfo = asset.getAdditionalInfo();
    }

    public AssetEntity(AssetEntity assetEntity) {
        this.setId(assetEntity.getId());
        this.setCreatedTime(assetEntity.getCreatedTime());
        this.customerId = assetEntity.getCustomerId();
        this.type = assetEntity.getType();
        this.name = assetEntity.getName();
        this.label = assetEntity.getLabel();
        this.searchText = assetEntity.getSearchText();
        this.additionalInfo = assetEntity.getAdditionalInfo();
    }


    public String getSearchTextSource() {
        return name;
    }


    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public Asset toAsset() {
        Asset asset = new Asset(id);
        asset.setCreatedTime(createdTime);
        if (customerId != null) {
            asset.setCustomerId(customerId);
        }
        asset.setName(name);
        asset.setType(type);
        asset.setLabel(label);
        asset.setAdditionalInfo(additionalInfo);
        return asset;
    }

    @Override
    public Asset toData() {
        return this.toAsset();
    }
}
