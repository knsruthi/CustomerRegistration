package com.registration.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.form.dao.CustomerDAO;
import com.registration.form.model.Customer;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	CustomerDAO customerDAO;

	@Autowired
	public void setUserDao(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	@Override
	public Customer findById(Integer id) {
		return customerDAO.findById(id);
	}


	@Override
	public List<Customer> findAll() {
		return customerDAO.findAllCustomers();
	}

	@Override
	public void saveOrUpdate(Customer customer) {

		if (findById(customer.getId())==null) {
			customerDAO.save(customer);
		} else {
			customerDAO.update(customer);
		}

	}

}