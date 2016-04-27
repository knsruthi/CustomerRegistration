package com.registration.form.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.registration.form.model.Customer;
import com.registration.form.service.RegistrationService;
import com.registration.form.validator.CustomerFormValidator;


@Controller
public class CustomerController {

	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerFormValidator customerFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(customerFormValidator);
	}

	private RegistrationService regService;

	@Autowired
	public void setRegistrationService(RegistrationService regService) {
		this.regService = regService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/customers";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String showAllCustomers(Model model) {

		logger.debug("showAllCustomers()");
		model.addAttribute("customers", regService.findAll());
		return "customers/list";

	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public String saveOrUpdateCustomer(@ModelAttribute("customerForm") @Validated Customer customer,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateCustomer() : {}", customer);

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "customers/customerform";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if(customer.isNew()){
				redirectAttributes.addFlashAttribute("msg", "Customer added successfully!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");
			}
			
			regService.saveOrUpdate(customer);
			
			// POST/REDIRECT/GET
			return "redirect:/customers/" + customer.getId();

		

		}

	}
	
	@RequestMapping(value = "/customers/add", method = RequestMethod.GET)
	public String showAddCustomerForm(Model model) {

		logger.debug("showAddCustomerForm()");

		Customer customer = new Customer();

		model.addAttribute("customerForm", customer);

		populateDefaultModel(model);

		return "customers/customerform";

	}

	// show update form
	@RequestMapping(value = "/customers/{id}/update", method = RequestMethod.GET)
	public String showUpdateCustomerForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateCustomerForm() : {}", id);

		Customer customer = regService.findById(id);
		model.addAttribute("customerForm", customer);
		
		populateDefaultModel(model);
		
		return "customers/customerform";

	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public String showCustomer(@PathVariable("id") int id, Model model) {

		logger.debug("showCustomer() id: {}", id);

		Customer customer = regService.findById(id);
		if (customer == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Customer"+ " not found");
		}
		model.addAttribute("customer", customer);

		return "customers/show";

	}

	private void populateDefaultModel(Model model) {
		
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("customer/show");
		model.addObject("msg", "customer not found");

		return model;

	}

}