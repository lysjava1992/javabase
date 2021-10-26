package com.simple.thingsboard.server.dao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.simple.thingsboard.server.common.data.asset.Asset;
import com.simple.thingsboard.server.common.data.EntitySubtype;
import com.simple.thingsboard.server.common.data.page.PageData;
import com.simple.thingsboard.server.common.data.page.PageLink;
import com.simple.thingsboard.server.dao.service.api.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {JpaDaoConfig.class})
public class AssetServiceTest {
    @Autowired
    private AssetService service;

    @Test
    public void testSave() throws JsonProcessingException, ExecutionException, InterruptedException {
        Asset asset = creatAsset("测试5");
        Asset save = service.saveAsset(asset);
        Asset find = service.findAssetById(save.getId());
        log.info("保存->查询: [{}]", find);
        ListenableFuture<Asset> asyncAsset = service.findAssetByIdAsync(save.getId());
        log.info("异步->查询: [{}]", asyncAsset.get());
        Asset nameFind = service.findAssetByName("测试4");
        log.info("名称->查询: [{}]", nameFind);
        service.deleteAsset(nameFind.getId());
        log.info("删除");
    }

    @Test
    public void assignAndUn() {
        UUID customerId=UUID.fromString("13814000-1dd2-11b2-0000-808080808080");
        UUID assentId=UUID.fromString("2d87a65e-f3ef-4669-9336-93f7af1a9adb");
        service.assignAssetToCustomer(assentId,customerId);
        log.info("解绑前:[{}]",service.findAssetById(assentId));
        service.unassignAssetFromCustomer(assentId);
        log.info("解绑后:[{}]",service.findAssetById(assentId));
       // service.assignAssetToCustomer(assentId,customerId);
    }
    @Test
    public void testType() throws ExecutionException, InterruptedException {
        ListenableFuture<List<EntitySubtype>> types=   service.findAssetTypes();
        types.get().stream().forEach(System.out::println);
    }
    @Test
    public void testList() throws ExecutionException, InterruptedException {
        List<UUID> list=new ArrayList<>();
        list.add(UUID.fromString("2d87a65e-f3ef-4669-9336-93f7af1a9adb"));
        list.add(UUID.fromString("2d87a65e-f3ef-4669-9336-93f7af1a9a12"));
        ListenableFuture<List<Asset>> assets=service.findAssetsByIdsAsync(list);
        assets.get().stream().forEach(System.out::println);
        ListenableFuture<List<Asset>> assets2=service.findAssetsByCustomerIdAndIdsAsync(UUID.fromString("13814000-1dd2-11b2-8080-808080808080"),list);
        assets2.get().stream().forEach(System.out::println);
    }
   @Test
    public void testPage(){
       PageLink pageLink =new PageLink(10);
      // PageData<Asset> assetPageData=service.findAssets(pageLink);
       PageData<Asset> assetPageData=service.findAssetsByType("TEST",pageLink);
       log.info("总页数[{}]",assetPageData.getTotalPages());
       log.info("总数量[{}]",assetPageData.getTotalElements());
       log.info("是否有下一页[{}]",assetPageData.hasNext());
       log.info("数据[{}]",assetPageData.getData());
       PageData<Asset> assetPageData2=service.findAssetsByCustomerId(UUID.fromString("13814000-1dd2-11b2-8080-808080808080"),pageLink);
       log.info("数据[{}]",assetPageData2.getData());
       PageData<Asset> assetPageData3=service.findAssetsByCustomerIdAndType( UUID.fromString("13814000-1dd2-11b2-8080-808080808080"), "TEST", pageLink);;
       log.info("数据[{}]",assetPageData3.getData());
    }
    @Test
    public void testPage2(){
        PageData<Asset> assetPageData=null;
        int pageSize=1;
        int page=0;
        do{
            PageLink pageLink =new PageLink(pageSize,page);
             assetPageData=service.findAssets(pageLink);
            log.info("总页数[{}]",assetPageData.getTotalPages());
            log.info("总数量[{}]",assetPageData.getTotalElements());
            log.info("是否有下一页[{}]",assetPageData.hasNext());
            log.info("数据[{}]",assetPageData.getData());
            page++;
        }while (assetPageData.hasNext());
    }
    private Asset creatAsset(String name) throws JsonProcessingException {
        Random random = new Random();
        Asset asset = new Asset();
        asset.setName(name);
        asset.setType("BUILDING" + random.nextInt(4));
        String json = "{\"area\":1026.5}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        asset.setAdditionalInfo(jsonNode);
        return asset;
    }

}
