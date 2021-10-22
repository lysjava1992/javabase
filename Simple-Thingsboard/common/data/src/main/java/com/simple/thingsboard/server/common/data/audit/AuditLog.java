
package com.simple.thingsboard.server.common.data.audit;

import com.fasterxml.jackson.databind.JsonNode;
import com.simple.thingsboard.server.common.data.BaseData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
public class AuditLog extends BaseData {
    /***
     *  客户组Id
     */
    private UUID customerId;
    /**
     *  实体ID
     */
    private UUID entityId;
    /**
     * 用户ID
     */
    private UUID userId;
    /**
     * 名称
     */
    private String entityName;
    /**
     * 用户名称
     */
    private String userName;
    private ActionType actionType;
    private JsonNode actionData;
    private ActionStatus actionStatus;
    private String actionFailureDetails;

    public AuditLog() {
        super();
    }

    public AuditLog(UUID id) {
        super(id);
    }

    public AuditLog(AuditLog auditLog) {
        super(auditLog);
        this.customerId = auditLog.getCustomerId();
        this.entityId = auditLog.getEntityId();
        this.entityName = auditLog.getEntityName();
        this.userId = auditLog.getUserId();
        this.userName = auditLog.getUserName();
        this.actionType = auditLog.getActionType();
        this.actionData = auditLog.getActionData();
        this.actionStatus = auditLog.getActionStatus();
        this.actionFailureDetails = auditLog.getActionFailureDetails();
    }
}
