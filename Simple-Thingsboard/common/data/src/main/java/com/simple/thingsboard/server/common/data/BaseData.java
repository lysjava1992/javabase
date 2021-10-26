
package com.simple.thingsboard.server.common.data;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseData implements Serializable {

    private static final long serialVersionUID = 5422817607129962637L;
    
    protected long createdTime;
    protected UUID id;
    public BaseData() {
        super();
    }

    public BaseData(UUID id) {
        this.id=id;
    }
    
    public BaseData(BaseData data) {
        this.id=data.getId();
        this.createdTime = data.getCreatedTime();
    }

    public long getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (createdTime ^ (createdTime >>> 32));
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseData other = (BaseData) obj;
        if (createdTime != other.createdTime)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseData [createdTime=");
        builder.append(createdTime);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
