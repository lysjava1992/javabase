package com.simple.thingsboard.server.dao.service.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 *  将所有实体公共的部分提取出来
 *   @MappedSuperclass 表示不是一个完整的映射类
 * @param <D>
 */
@Data
@MappedSuperclass
public abstract class BaseSqlEntity<D> implements ToData<D>{
    @Id
    @Column(name = ModelConstants.ID_PROPERTY, columnDefinition = "uuid")
    protected UUID id;

    @Column(name = ModelConstants.CREATED_TIME_PROPERTY)
    protected long createdTime;


    public UUID getUuid() {
        return id;
    }


    public void setUuid(UUID id) {
        this.id = id;
    }


    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        if (createdTime > 0) {
            this.createdTime = createdTime;
        }
    }
}
