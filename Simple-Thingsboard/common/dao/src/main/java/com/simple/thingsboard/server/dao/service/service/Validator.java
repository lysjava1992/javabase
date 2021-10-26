
package com.simple.thingsboard.server.dao.service.service;

import com.simple.thingsboard.server.common.data.page.PageLink;
import com.simple.thingsboard.server.dao.service.exception.IncorrectParameterException;

import java.util.List;
import java.util.UUID;

public class Validator {
    /**
     *   校验是否为空
     * @param id  UUID
     * @param errorMessage
     */
    public static void validateId(UUID id, String errorMessage) {
        if (id == null ) {
            throw new IncorrectParameterException(errorMessage);
        }
    }
    public static void validateIds(List<UUID> ids, String errorMessage) {
        if (ids == null || ids.isEmpty()) {
            throw new IncorrectParameterException(errorMessage);
        } else {
            for (UUID id : ids) {
                validateId(id, errorMessage);
            }
        }
    }
    public static void validateString(String val, String errorMessage) {
        if (val == null || val.isEmpty()) {
            throw new IncorrectParameterException(errorMessage);
        }
    }
    public static void validatePageLink(PageLink pageLink) {
        if (pageLink == null) {
            throw new IncorrectParameterException("必须指定分页配置");
        } else if (pageLink.getPageSize() < 1) {
            throw new IncorrectParameterException("分页配置：无效条数  '"+pageLink.getPageSize()+"'. 每页条数必须大于0.");
        } else if (pageLink.getPage() < 0) {
            throw new IncorrectParameterException("分页配置：无效页数'"+pageLink.getPage()+"'. 必须大于0.");
        }
    }
}
