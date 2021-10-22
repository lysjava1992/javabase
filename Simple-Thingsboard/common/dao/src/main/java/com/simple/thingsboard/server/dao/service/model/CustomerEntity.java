package com.simple.thingsboard.server.dao.service.model;

import com.simple.thingsboard.server.common.data.Customer;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = ModelConstants.ASSET_COLUMN_FAMILY_NAME)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class CustomerEntity  extends BaseSqlEntity<Customer>{
    @Override
    public Customer toData() {
        return null;
    }
}
