package com.registration.form.service;

import java.util.List;

import com.registration.form.model.Customer;

public interface RegistrationService {
	List<Customer> findAll();
	void saveOrUpdate(Customer customer);
	Customer findById(Integer id);
}