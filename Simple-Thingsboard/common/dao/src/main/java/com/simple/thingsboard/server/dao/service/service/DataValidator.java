package com.simple.thingsboard.server.dao.service.service;

import com.simple.thingsboard.server.common.data.BaseData;
import com.simple.thingsboard.server.dao.service.exception.DataValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
@Slf4j
public abstract class DataValidator <T extends BaseData>{
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    public void validate(T data) {
        try {
            if (data == null) {
                throw new DataValidationException("Data object can't be null!");
            }
            validateDataImpl(data);
            if (data.getId() == null) {
                validateCreate( data);
            } else {
                validateUpdate( data);
            }
        } catch (DataValidationException e) {
            log.error("Data object is invalid: [{}]", e.getMessage());
            throw e;
        }
    }
    protected void validateDataImpl(T data) {
    }

    protected void validateCreate(T data) {
    }

    protected void validateUpdate(T data) {
    }
}
