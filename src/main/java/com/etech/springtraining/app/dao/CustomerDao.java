package com.etech.springtraining.app.dao;

import com.etech.springtraining.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
