package com.simple.thingsboard.server.controller;

import com.simple.thingsboard.server.common.data.audit.ActionType;
import com.simple.thingsboard.server.common.data.exception.CustomErrorCode;
import com.simple.thingsboard.server.common.data.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class BaseController {
//    @Autowired
//    protected AuditLogService auditLogService;
    <T> T checkNotNull(T reference) throws CustomException {
        if (reference == null) {
            throw new CustomException("Requested item wasn't found!", CustomErrorCode.ITEM_NOT_FOUND);
        }
        return reference;
    }
//    protected void logEntityAction(UUID entityId, Object entity, UUID customerId,
//                                   ActionType actionType, Exception e, Object... additionalInfo)  {
//        logEntityAction(getCurrentUser(), entityId, entity, customerId, actionType, e, additionalInfo);
//    }
//
//
//
//    protected <E extends HasName, I extends EntityId> void logEntityAction(User user, I entityId, E entity, CustomerId customerId,
//                                                                           ActionType actionType, Exception e, Object... additionalInfo) throws ThingsboardException {
//        if (customerId == null || customerId.isNullUid()) {
//            customerId = user.getCustomerId();
//        }
//        if (e == null) {
//            pushEntityActionToRuleEngine(entityId, entity, user, customerId, actionType, additionalInfo);
//        }
//        auditLogService.logEntityAction( customerId, user.getId(), user.getName(), entityId, entity, actionType, e, additionalInfo);
//    }

    protected Object getCurrentUser(){
      return null;
    }
}
