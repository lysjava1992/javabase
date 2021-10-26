package com.simple.thingsboard.server.dao.service.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.simple.thingsboard.server.common.data.asset.Asset;
import com.simple.thingsboard.server.common.data.EntitySubtype;
import com.simple.thingsboard.server.common.data.page.PageData;
import com.simple.thingsboard.server.common.data.page.PageLink;

import java.util.List;
import java.util.UUID;

public interface AssetService {
   /**
    *  新建|保存
    * @param asset 资产
    * @return
    */
   Asset saveAsset(Asset asset);

   /**
    * 查询
    * @param assetId  资产Id
    * @return 资产
    */
   Asset findAssetById(UUID assetId);

   /**
    *  异步查询
    * @param assetId 资产Id
    * @return 资产
    */
   ListenableFuture<Asset> findAssetByIdAsync(UUID assetId);

   /**
    *  查询
    * @param name 资产名称
    * @return 资产
    */
   Asset findAssetByName(String name);

   /**
    *  资产与客户绑定
    * @param assetId 资产Id
    * @param customerId 客户组Id
    * @return  资产
    */
   Asset assignAssetToCustomer(UUID assetId, UUID customerId);

   /**
    *  资产与客户解绑
    * @param assetId 资产ID
    * @return
    */
   Asset unassignAssetFromCustomer(UUID assetId);

   /**
    * 删除
    * @param assetId 资产ID
    */
   void deleteAsset(UUID assetId);

   /**
    * 分页查询
    * @param pageLink 分页信息
    * @return 数据
    */
   PageData<Asset> findAssets(PageLink pageLink);

   /**
    *分页查询
    * @param type 资产类型
    * @param pageLink 分页信息
    * @return
    */
   PageData<Asset> findAssetsByType(String type, PageLink pageLink);

   /**
    *  异步查询
    * @param assetIds 资产ID集合
    * @return
    */
   ListenableFuture<List<Asset>> findAssetsByIdsAsync(List<UUID> assetIds);

   /**
    * 分页查询
    * @param customerId  客户组Id
    * @param pageLink 分页
    * @return
    */
   PageData<Asset> findAssetsByCustomerId( UUID customerId, PageLink pageLink);

   /**
    *
    * @param customerId 客户组Id
    * @param type 资产类型
    * @param pageLink 分页
    * @return
    */
   PageData<Asset> findAssetsByCustomerIdAndType( UUID customerId, String type, PageLink pageLink);

   /**
    *  异步查询
    * @param customerId 客户组Id
    * @param assetIds 资产ID 集合
    * @return
    */
   ListenableFuture<List<Asset>> findAssetsByCustomerIdAndIdsAsync(UUID customerId, List<UUID> assetIds);

   /**
    * 解绑
    * @param customerId 客户Id
    */
   void unassignCustomerAssets(UUID customerId);

   // ListenableFuture<List<Asset>> findAssetsByQuery( AssetSearchQuery query);

   ListenableFuture<List<EntitySubtype>> findAssetTypes();
}
