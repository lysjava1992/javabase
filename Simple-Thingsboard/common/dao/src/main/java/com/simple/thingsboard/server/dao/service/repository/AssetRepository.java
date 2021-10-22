package com.simple.thingsboard.server.dao.service.repository;

import com.simple.thingsboard.server.dao.service.model.AssetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AssetRepository extends PagingAndSortingRepository<AssetEntity, UUID> {
    AssetEntity findByName(String name);

    List<AssetEntity> findByIdIn(List<UUID> assetIds);

    List<AssetEntity> findByCustomerIdAndIdIn(UUID customerId, List<UUID> assetIds);

    @Query("SELECT a FROM AssetEntity a WHERE LOWER(a.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<AssetEntity> findAssets(@Param("textSearch") String textSearch,
                                 Pageable pageable);

    @Query("SELECT a FROM AssetEntity a WHERE a.type = :type " +
            "AND LOWER(a.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<AssetEntity> findAssetsByType(@Param("type") String type,
                                       @Param("textSearch") String textSearch,
                                       Pageable pageable);

    @Query("SELECT a FROM AssetEntity a WHERE a.customerId = :customerId " +
            "AND LOWER(a.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<AssetEntity> findAssetsByCustomerId(@Param("customerId") UUID customerId,
                                             @Param("textSearch") String textSearch,
                                             Pageable toPageable);

    @Query("SELECT a FROM AssetEntity a WHERE a.customerId = :customerId " +
            "AND  a.type = :type AND LOWER(a.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<AssetEntity> findAssetsByCustomerIdAndType(@Param("customerId") UUID customerId,
                                                    @Param("type") String type,
                                                    @Param("textSearch") String textSearch,
                                                    Pageable toPageable);

    @Query("UPDATE  AssetEntity a SET  a.customerId=null WHERE a.customerId = :customerId")
    void unassignCustomerAssets(UUID customerId);

    @Query("SELECT DISTINCT a.type FROM AssetEntity a")
    List<String> findAssetTypes();
}
