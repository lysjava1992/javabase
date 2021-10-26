package com.simple.thingsboard.server.controller;
import com.simple.thingsboard.server.common.data.asset.Asset;
import com.simple.thingsboard.server.dao.service.api.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api")
public class AssetController extends BaseController{

    @Autowired
    AssetService assetService;
    /**
     * 新建/更新资产
     * @param asset
     * @return
     * @throws 
     */
    @RequestMapping(value = "/asset", method = RequestMethod.POST)
    @ResponseBody
    public Asset saveAsset(@RequestBody Asset asset)  {
        try {
            // TODO  操作权限检查
//            checkEntity(asset.getId(), asset, Resource.ASSET);

            // 保存
           Asset savedAsset = checkNotNull(assetService.saveAsset(asset));

            //日志操作
//            logEntityAction(savedAsset.getId(), savedAsset,
//                    savedAsset.getCustomerId(),
//                    asset.getId() == null ? ActionType.ADDED : ActionType.UPDATED, null);

            return asset;
        } catch (Exception e) {
//            logEntityAction(emptyId(EntityType.ASSET), asset,
//                    null, asset.getId() == null ? ActionType.ADDED : ActionType.UPDATED, e);
            log.warn("saveAsset: ",e);
        }
        return null;
    }

//
//    /**
//     *  通过资产ID获取资产
//     *  没有 "customerTitle"和 "customerIsPublic"俩个字段
//     * @param strAssetId 资产ID
//     * @return
//     * @throws
//     */
//    @RequestMapping(value = "/asset/{assetId}", method = RequestMethod.GET)
//    @ResponseBody
//    public Asset getAssetById(@PathVariable(ASSET_ID) String strAssetId) throws ThingsboardException {
//        checkParameter(ASSET_ID, strAssetId);
//        try {
//            AssetId assetId = new AssetId(toUUID(strAssetId));
//            return checkAssetId(assetId, Operation.READ);
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//
//     * 通过资产ID获取资产
//     * @param strAssetId 资产ID
//     * @return
//     */
//    @RequestMapping(value = "/asset/info/{assetId}", method = RequestMethod.GET)
//    @ResponseBody
//    public AssetInfo getAssetInfoById(@PathVariable(ASSET_ID) String strAssetId) throws ThingsboardException {
//        checkParameter(ASSET_ID, strAssetId);
//        try {
//            AssetId assetId = new AssetId(toUUID(strAssetId));
//            return checkAssetInfoId(assetId, Operation.READ);
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }

//    /**
//     * 权限{ TENANT_ADMIN }
//     * 删除资产
//     * @param strAssetId 资产ID
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/asset/{assetId}", method = RequestMethod.DELETE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void deleteAsset(@PathVariable(ASSET_ID) String strAssetId) throws ThingsboardException {
//        checkParameter(ASSET_ID, strAssetId);
//        try {
//            AssetId assetId = new AssetId(toUUID(strAssetId));
//            Asset asset = checkAssetId(assetId, Operation.DELETE);
//            assetService.deleteAsset(getTenantId(), assetId);
//
//            logEntityAction(assetId, asset,
//                    asset.getCustomerId(),
//                    ActionType.DELETED, null, strAssetId);
//
//        } catch (Exception e) {
//            logEntityAction(emptyId(EntityType.ASSET),
//                    null,
//                    null,
//                    ActionType.DELETED, e, strAssetId);
//            throw handleException(e);
//        }
//    }
//
//    /**
//     *   租户将资产分配给客户
//     * @param strCustomerId 客户ID
//     * @param strAssetId 资产ID
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/customer/{customerId}/asset/{assetId}", method = RequestMethod.POST)
//    @ResponseBody
//    public Asset assignAssetToCustomer(@PathVariable("customerId") String strCustomerId,
//                                       @PathVariable(ASSET_ID) String strAssetId) throws ThingsboardException {
//        checkParameter("customerId", strCustomerId);
//        checkParameter(ASSET_ID, strAssetId);
//        try {
//            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
//            Customer customer = checkCustomerId(customerId, Operation.READ);
//
//            AssetId assetId = new AssetId(toUUID(strAssetId));
//            checkAssetId(assetId, Operation.ASSIGN_TO_CUSTOMER);
//
//            Asset savedAsset = checkNotNull(assetService.assignAssetToCustomer(getTenantId(), assetId, customerId));
//
//            logEntityAction(assetId, savedAsset,
//                    savedAsset.getCustomerId(),
//                    ActionType.ASSIGNED_TO_CUSTOMER, null, strAssetId, strCustomerId, customer.getName());
//
//            return savedAsset;
//        } catch (Exception e) {
//
//            logEntityAction(emptyId(EntityType.ASSET), null,
//                    null,
//                    ActionType.ASSIGNED_TO_CUSTOMER, e, strAssetId, strCustomerId);
//
//            throw handleException(e);
//        }
//    }
//
//    /**
//     * 取消将资产分配给用户
//     * @param strAssetId
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/customer/asset/{assetId}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public Asset unassignAssetFromCustomer(@PathVariable(ASSET_ID) String strAssetId) throws ThingsboardException {
//        checkParameter(ASSET_ID, strAssetId);
//        try {
//            AssetId assetId = new AssetId(toUUID(strAssetId));
//            Asset asset = checkAssetId(assetId, Operation.UNASSIGN_FROM_CUSTOMER);
//            if (asset.getCustomerId() == null || asset.getCustomerId().getId().equals(ModelConstants.NULL_UUID)) {
//                throw new IncorrectParameterException("Asset isn't assigned to any customer!");
//            }
//
//            Customer customer = checkCustomerId(asset.getCustomerId(), Operation.READ);
//
//            Asset savedAsset = checkNotNull(assetService.unassignAssetFromCustomer(getTenantId(), assetId));
//
//            logEntityAction(assetId, asset,
//                    asset.getCustomerId(),
//                    ActionType.UNASSIGNED_FROM_CUSTOMER, null, strAssetId, customer.getId().toString(), customer.getName());
//
//            return savedAsset;
//        } catch (Exception e) {
//
//            logEntityAction(emptyId(EntityType.ASSET), null,
//                    null,
//                    ActionType.UNASSIGNED_FROM_CUSTOMER, e, strAssetId);
//
//            throw handleException(e);
//        }
//    }
//
//
//    /**
//     * 资产公开和私有
//     * @param strAssetId
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/customer/public/asset/{assetId}", method = RequestMethod.POST)
//    @ResponseBody
//    public Asset assignAssetToPublicCustomer(@PathVariable(ASSET_ID) String strAssetId) throws ThingsboardException {
//        checkParameter(ASSET_ID, strAssetId);
//        try {
//            AssetId assetId = new AssetId(toUUID(strAssetId));
//            Asset asset = checkAssetId(assetId, Operation.ASSIGN_TO_CUSTOMER);
//            Customer publicCustomer = customerService.findOrCreatePublicCustomer(asset.getTenantId());
//            Asset savedAsset = checkNotNull(assetService.assignAssetToCustomer(getTenantId(), assetId, publicCustomer.getId()));
//
//            logEntityAction(assetId, savedAsset,
//                    savedAsset.getCustomerId(),
//                    ActionType.ASSIGNED_TO_CUSTOMER, null, strAssetId, publicCustomer.getId().toString(), publicCustomer.getName());
//
//            return savedAsset;
//        } catch (Exception e) {
//
//            logEntityAction(emptyId(EntityType.ASSET), null,
//                    null,
//                    ActionType.ASSIGNED_TO_CUSTOMER, e, strAssetId);
//
//            throw handleException(e);
//        }
//    }
//
//    /**
//     * 返回没有一下字段
//     *  { "customerTitle": null, "customerIsPublic": false}
//     * @param pageSize 分页
//     * @param page  页数 0开始
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/tenant/assets", params = {"pageSize", "page"}, method = RequestMethod.GET)
//    @ResponseBody
//    public PageData<Asset> getTenantAssets(
//            @RequestParam int pageSize,
//            @RequestParam int page,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) String textSearch,
//            @RequestParam(required = false) String sortProperty,
//            @RequestParam(required = false) String sortOrder) throws ThingsboardException {
//        try {
//            TenantId tenantId = getCurrentUser().getTenantId();
//            PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
//            if (type != null && type.trim().length()>0) {
//                return checkNotNull(assetService.findAssetsByTenantIdAndType(tenantId, type, pageLink));
//            } else {
//                return checkNotNull(assetService.findAssetsByTenantId(tenantId, pageLink));
//            }
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//     * 权限{ TENANT_ADMIN ,CUSTOMER_USER }
//     *  获取租户所有资产列表 分页
//     * @param pageSize 每页size 必填
//     * @param page   页数 必填 0开始
//     * @param type   资产类型 选填
//     * @param textSearch  模糊查询 选填
//     * @param sortProperty  排序字段 选填
//     * @param sortOrder     排序方式 ASC/DESC 选填
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/tenant/assetInfos", params = {"pageSize", "page"}, method = RequestMethod.GET)
//    @ResponseBody
//    public PageData<AssetInfo> getTenantAssetInfos(
//            @RequestParam int pageSize,
//            @RequestParam int page,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) String textSearch,
//            @RequestParam(required = false) String sortProperty,
//            @RequestParam(required = false) String sortOrder) throws ThingsboardException {
//        try {
//            TenantId tenantId = getCurrentUser().getTenantId();
//            PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
//            if (type != null && type.trim().length() > 0) {
//                return checkNotNull(assetService.findAssetInfosByTenantIdAndType(tenantId, type, pageLink));
//            } else {
//                return checkNotNull(assetService.findAssetInfosByTenantId(tenantId, pageLink));
//            }
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//
//    /**
//     * 根据资产名称查询资产
//     * @param assetName 资产名称
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
//    @RequestMapping(value = "/tenant/assets", params = {"assetName"}, method = RequestMethod.GET)
//    @ResponseBody
//    public Asset getTenantAsset(
//            @RequestParam String assetName) throws ThingsboardException {
//        try {
//            TenantId tenantId = getCurrentUser().getTenantId();
//            return checkNotNull(assetService.findAssetByTenantIdAndName(tenantId, assetName));
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//     *  {TENANT_ADMIN,CUSTOMER_USER}
//     * @param strCustomerId 客户ID  必填
//     * @param pageSize 每页size  必填
//     * @param page  页数  必填
//     * @param type   资产类型 选
//     * @param textSearch  模糊查询 选
//     * @param sortProperty  排序字段 选
//     * @param sortOrder  升降序ASC/ DESC  选
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
//    @RequestMapping(value = "/customer/{customerId}/assets", params = {"pageSize", "page"}, method = RequestMethod.GET)
//    @ResponseBody
//    public PageData<Asset> getCustomerAssets(
//            @PathVariable("customerId") String strCustomerId,
//            @RequestParam int pageSize,
//            @RequestParam int page,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) String textSearch,
//            @RequestParam(required = false) String sortProperty,
//            @RequestParam(required = false) String sortOrder) throws ThingsboardException {
//        checkParameter("customerId", strCustomerId);
//        try {
//            TenantId tenantId = getCurrentUser().getTenantId();
//            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
//            checkCustomerId(customerId, Operation.READ);
//            PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
//            if (type != null && type.trim().length()>0) {
//                return checkNotNull(assetService.findAssetsByTenantIdAndCustomerIdAndType(tenantId, customerId, type, pageLink));
//            } else {
//                return checkNotNull(assetService.findAssetsByTenantIdAndCustomerId(tenantId, customerId, pageLink));
//            }
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//     *  {TENANT_ADMIN,CUSTOMER_USER}
//     * @param strCustomerId 客户ID  必填
//     * @param pageSize 每页size  必填
//     * @param page  页数  必填
//     * @param type   资产类型 选
//     * @param textSearch  模糊查询 选
//     * @param sortProperty  排序字段 选
//     * @param sortOrder  升降序ASC/ DESC  选
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
//    @RequestMapping(value = "/customer/{customerId}/assetInfos", params = {"pageSize", "page"}, method = RequestMethod.GET)
//    @ResponseBody
//    public PageData<AssetInfo> getCustomerAssetInfos(
//            @PathVariable("customerId") String strCustomerId,
//            @RequestParam int pageSize,
//            @RequestParam int page,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) String textSearch,
//            @RequestParam(required = false) String sortProperty,
//            @RequestParam(required = false) String sortOrder) throws ThingsboardException {
//        checkParameter("customerId", strCustomerId);
//        try {
//            TenantId tenantId = getCurrentUser().getTenantId();
//            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
//            checkCustomerId(customerId, Operation.READ);
//            PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
//            if (type != null && type.trim().length() > 0) {
//                return checkNotNull(assetService.findAssetInfosByTenantIdAndCustomerIdAndType(tenantId, customerId, type, pageLink));
//            } else {
//                return checkNotNull(assetService.findAssetInfosByTenantIdAndCustomerId(tenantId, customerId, pageLink));
//            }
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//     *  根据资产ID 数组 获取相应的资产列表
//     * @param strAssetIds
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
//    @RequestMapping(value = "/assets", params = {"assetIds"}, method = RequestMethod.GET)
//    @ResponseBody
//    public List<Asset> getAssetsByIds(
//            @RequestParam("assetIds") String[] strAssetIds) throws ThingsboardException {
//        checkArrayParameter("assetIds", strAssetIds);
//        try {
//            SecurityUser user = getCurrentUser();
//            TenantId tenantId = user.getTenantId();
//            CustomerId customerId = user.getCustomerId();
//            List<AssetId> assetIds = new ArrayList<>();
//            for (String strAssetId : strAssetIds) {
//                assetIds.add(new AssetId(toUUID(strAssetId)));
//            }
//            ListenableFuture<List<Asset>> assets;
//            if (customerId == null || customerId.isNullUid()) {
//                assets = assetService.findAssetsByTenantIdAndIdsAsync(tenantId, assetIds);
//            } else {
//                assets = assetService.findAssetsByTenantIdCustomerIdAndIdsAsync(tenantId, customerId, assetIds);
//            }
//            return checkNotNull(assets.get());
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//     * 复杂查询
//     * @param query
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
//    @RequestMapping(value = "/assets", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Asset> findByQuery(@RequestBody AssetSearchQuery query) throws ThingsboardException {
//        log.warn("请求参数{}",query);
//        checkNotNull(query);
//        checkNotNull(query.getParameters());
//        checkNotNull(query.getAssetTypes());
//        checkEntityId(query.getParameters().getEntityId(), Operation.READ);
//        try {
//            List<Asset> assets = checkNotNull(assetService.findAssetsByQuery(getTenantId(), query).get());
//            assets = assets.stream().filter(asset -> {
//                try {
//                    accessControlService.checkPermission(getCurrentUser(), Resource.ASSET, Operation.READ, asset.getId(), asset);
//                    return true;
//                } catch (ThingsboardException e) {
//                    return false;
//                }
//            }).collect(Collectors.toList());
//            return assets;
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
//
//    /**
//     * 权限{ TENANT_ADMIN ,CUSTOMER_USER }
//     * 获取当前租户资产的所有分类列表
//     * @return
//     * @throws ThingsboardException
//     */
//    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
//    @RequestMapping(value = "/asset/types", method = RequestMethod.GET)
//    @ResponseBody
//    public List<EntitySubtype> getAssetTypes() throws ThingsboardException {
//        try {
//            SecurityUser user = getCurrentUser();
//            TenantId tenantId = user.getTenantId();
//            ListenableFuture<List<EntitySubtype>> assetTypes = assetService.findAssetTypesByTenantId(tenantId);
//            return checkNotNull(assetTypes.get());
//        } catch (Exception e) {
//            throw handleException(e);
//        }
//    }
}
