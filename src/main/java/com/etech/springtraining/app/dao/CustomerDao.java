package com.etech.springtraining.app.dao;

import com.etech.springtraining.app.Gender;
import com.etech.springtraining.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Long> {


}
