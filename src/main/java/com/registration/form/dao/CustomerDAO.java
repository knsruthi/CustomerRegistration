package com.registration.form.dao;

import java.util.List;

import com.registration.form.model.Customer;

public interface CustomerDAO {
	List<Customer> findAllCustomers();
	void save(Customer customer);
	Customer findById(Integer id);
	void update(Customer customer);
}