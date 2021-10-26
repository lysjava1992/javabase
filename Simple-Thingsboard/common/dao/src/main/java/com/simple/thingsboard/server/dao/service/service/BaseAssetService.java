package com.simple.thingsboard.server.dao.service.service;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.simple.thingsboard.server.common.data.asset.Asset;
import com.simple.thingsboard.server.common.data.EntitySubtype;
import com.simple.thingsboard.server.common.data.EntityType;
import com.simple.thingsboard.server.common.data.page.PageData;
import com.simple.thingsboard.server.common.data.page.PageLink;
import com.simple.thingsboard.server.dao.service.api.AssetService;
import com.simple.thingsboard.server.dao.service.exception.DataValidationException;
import com.simple.thingsboard.server.dao.service.model.AssetEntity;
import com.simple.thingsboard.server.dao.service.repository.AssetRepository;
import com.simple.thingsboard.server.dao.service.repository.DaoUtil;
import com.simple.thingsboard.server.dao.service.repository.JpaExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

import static com.simple.thingsboard.server.dao.service.model.ModelConstants.NULL_UUID;
import static com.simple.thingsboard.server.dao.service.service.Validator.*;

@Service
@Slf4j
public class BaseAssetService implements AssetService {
    public static final String INCORRECT_ASSET_ID = "无效的资产ID  ";
    public static final String INCORRECT_ASSET_IDS = "无效的资产ID列表  ";
    public static final String INCORRECT_CUSTOMER_ID = "无效的客户ID  ";
    public static final String INCORRECT_TYPE = "无效的资产类型  ";
    @Autowired
    JpaExecutorService executorService;
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset saveAsset(Asset asset) {
        assetValidator.validate(asset);
        Asset savedAsset = null;
        try {
            savedAsset = assetRepository.save(new AssetEntity(asset)).toAsset();
        } catch (Exception t) {
            ConstraintViolationException e = extractConstraintViolationException(t).orElse(null);
            if (e != null && e.getConstraintName() != null && e.getConstraintName().equalsIgnoreCase("asset_name_unq_key")) {
                throw new DataValidationException("Asset with such name already exists!");
            } else {
                throw t;
            }
        }
        return savedAsset;
    }

    @Override
    public Asset findAssetById(UUID assetId) {
        validateId(assetId, INCORRECT_ASSET_ID + assetId);
        return assetRepository.findById(assetId).get().toAsset();
    }

    @Override
    public ListenableFuture<Asset> findAssetByIdAsync(UUID assetId) {
        validateId(assetId, INCORRECT_ASSET_ID);
        return executorService.submit(() -> assetRepository.findById(assetId).get().toAsset());
    }

    @Override
    public Asset findAssetByName(String name) {
        return assetRepository.findByName(name).toAsset();
    }

    @Override
    public Asset assignAssetToCustomer(UUID assetId, UUID customerId) {
        Asset asset = findAssetById(assetId);
        asset.setCustomerId(customerId);
        return saveAsset(asset);
    }

    @Override
    public Asset unassignAssetFromCustomer(UUID assetId) {
        Asset asset = findAssetById(assetId);
        asset.setCustomerId(null);
        return saveAsset(asset);
    }

    @Override
    public void deleteAsset(UUID assetId) {
        validateId(assetId, INCORRECT_ASSET_ID + assetId);

        // TODO  解除资产关系

        assetRepository.deleteById(assetId);
    }

    @Override
    public PageData<Asset> findAssets(PageLink pageLink) {
        validatePageLink(pageLink);
        return DaoUtil.toPageData(assetRepository
                .findAssets(
                        Objects.toString(pageLink.getTextSearch(), ""),
                        DaoUtil.toPageable(pageLink)));
    }

    @Override
    public PageData<Asset> findAssetsByType(String type, PageLink pageLink) {
        validateString(type, INCORRECT_TYPE + type);
        validatePageLink(pageLink);
        return DaoUtil.toPageData(assetRepository.findAssetsByType(type,
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink)));
    }

    @Override
    public ListenableFuture<List<Asset>> findAssetsByIdsAsync(List<UUID> assetIds) {
        return executorService.submit(() ->
                DaoUtil.convertDataList(assetRepository.findByIdIn(assetIds)));
    }

    @Override
    public PageData<Asset> findAssetsByCustomerId(UUID customerId, PageLink pageLink) {
        validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
        validatePageLink(pageLink);
        return DaoUtil.toPageData(assetRepository.findAssetsByCustomerId(
                customerId,
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink)
        ));
    }

    @Override
    public PageData<Asset> findAssetsByCustomerIdAndType(UUID customerId, String type, PageLink pageLink) {
        validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
        validatePageLink(pageLink);
        validateString(type, INCORRECT_TYPE + type);
        return DaoUtil.toPageData(assetRepository.findAssetsByCustomerIdAndType(customerId,
                type,
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink)));
    }

    @Override
    public ListenableFuture<List<Asset>> findAssetsByCustomerIdAndIdsAsync(UUID customerId, List<UUID> assetIds) {
        validateId(customerId,INCORRECT_CUSTOMER_ID+ customerId);
        validateIds(assetIds,INCORRECT_ASSET_IDS+assetIds);
        return executorService.submit(()->DaoUtil.convertDataList(
                assetRepository.findByCustomerIdAndIdIn(customerId, assetIds)
        ));
    }

    @Override
    public void unassignCustomerAssets(UUID customerId) {
        validateId(customerId,INCORRECT_CUSTOMER_ID+customerId);
        assetRepository.unassignCustomerAssets(customerId);
    }

    @Override
    public ListenableFuture<List<EntitySubtype>> findAssetTypes() {
        ListenableFuture<List<EntitySubtype>> tenantAssetTypes=executorService.submit(() -> convertTenantAssetTypesToDto( assetRepository.findAssetTypes()));
        return Futures.transform(tenantAssetTypes,
                assetTypes -> {
                    assetTypes.sort(Comparator.comparing(EntitySubtype::getType));
                    return assetTypes;
                }, MoreExecutors.directExecutor());
    }
    private List<EntitySubtype> convertTenantAssetTypesToDto(List<String> types) {
        List<EntitySubtype> list = Collections.emptyList();
        if (types != null && !types.isEmpty()) {
            list = new ArrayList<>();
            for (String type : types) {
                list.add(new EntitySubtype(EntityType.ASSET, type));
            }
        }
        return list;
    }
    protected Optional<ConstraintViolationException> extractConstraintViolationException(Exception t) {
        if (t instanceof ConstraintViolationException) {
            return Optional.of((ConstraintViolationException) t);
        } else if (t.getCause() instanceof ConstraintViolationException) {
            return Optional.of((ConstraintViolationException) (t.getCause()));
        } else {
            return Optional.empty();
        }
    }

    private DataValidator<Asset> assetValidator =
            new DataValidator<Asset>() {
                @Override
                protected void validateCreate(Asset asset) {
                    if (asset.getId() == null) {
                        UUID uuid = UUID.randomUUID();
                        asset.setId(uuid);
                        asset.setCreatedTime(System.currentTimeMillis());
                    }
                    if (asset.getSearchText() == null) {

                    }
                }

                @Override
                protected void validateUpdate(Asset asset) {
                }

                @Override
                protected void validateDataImpl(Asset asset) {
                    if (ObjectUtils.isEmpty(asset.getType())) {
                        throw new DataValidationException("未指定资产类型!");
                    }
                    if (ObjectUtils.isEmpty(asset.getName())) {
                        throw new DataValidationException("未指定资产名称!");
                    }
                    if (asset.getCustomerId() == null) {
                        asset.setCustomerId(NULL_UUID);
                    } else if (!asset.getCustomerId().equals(NULL_UUID)) {
//                        Customer customer = customerDao.findById(asset.getCustomerId());
//                        if (customer == null) {
//                            throw new DataValidationException("无法将资产绑定到客户!");
//                        }
                    }
                }
            };
}
