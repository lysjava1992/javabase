
package com.simple.thingsboard.server.dao.service.api;

import com.simple.thingsboard.server.common.data.Customer;

import java.util.UUID;

public interface CustomerService {

    Customer findCustomerById(UUID customerId);



}
