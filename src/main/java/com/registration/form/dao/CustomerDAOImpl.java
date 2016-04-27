package com.registration.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.registration.form.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Customer findById(Integer id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM CUSTOMERS WHERE id=:id";

		Customer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new CustomerMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Failed to retrieve");
		}

		return result;

	}
	

	@Override
	public void update(Customer customer) {

		String sql = "UPDATE CUSTOMERS SET FIRST_NAME=:first_name, LAST_NAME=:last_name, ADDRESS_1=:address_1, " + "ADDRESS_2=:address_2, CITY=:city, STATE=:state, "
				+ "COUNTRY=:country, ZIP_CODE=:zip_code, EMAIL=:email, USER_NAME=:user_name, PASSWORD=:password, REG_DATE=:reg_date  WHERE id=:id";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(customer));

	}

	@Override
	public List<Customer> findAllCustomers() {

		String sql = "SELECT FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, COUNTRY,ZIP_CODE, REG_DATE FROM CUSTOMERS order by reg_date";
		List<Customer> result = namedParameterJdbcTemplate.query(sql, new CustomerMapper());

		return result;

	}

	@Override
	public void save(Customer customer) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		if(customer.getReg_date() == null){
			customer.setReg_date(new Date());
		}
		String sql = "INSERT INTO CUSTOMERS(FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, COUNTRY, ZIP_CODE, EMAIL, USER_NAME, PASSWORD, REG_DATE) "
				+ "VALUES ( :first_name, :last_name, :address_1, :address_2, :city, :state, :country, :zip_code, :email, :user_name, :password, :reg_date)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(customer), keyHolder);
		customer.setId(keyHolder.getKey().intValue());
		
	}


	private SqlParameterSource getSqlParameterByModel(Customer customer) {

		

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", customer.getId());
		paramSource.addValue("first_name", customer.getFirst_name());
		paramSource.addValue("last_name", customer.getLast_name());
		paramSource.addValue("address_1", customer.getAddress_1());
		paramSource.addValue("address_2", customer.getAddress_2());
		paramSource.addValue("city", customer.getPassword());
		paramSource.addValue("state", customer.getState());
		paramSource.addValue("country", customer.getCountry());
		paramSource.addValue("zip_code", customer.getZip_code());
		paramSource.addValue("email", customer.getEmail());
		paramSource.addValue("user_name", customer.getUser_name());
		paramSource.addValue("password",customer.getPassword());
		paramSource.addValue("reg_date", customer.getReg_date());

		return paramSource;
	}

	private static final class CustomerMapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setFirst_name(rs.getString("first_name"));
			customer.setLast_name(rs.getString("last_name"));
			customer.setAddress_1(rs.getString("address_1"));
			customer.setAddress_2(rs.getString("address_2"));
			customer.setCity(rs.getString("city"));
			customer.setState(rs.getString("state"));
			customer.setZip_code(rs.getString("zip_code"));
			customer.setCountry(rs.getString("country"));
			customer.setReg_date(rs.getDate("reg_date"));
			
			return customer;
		}
	}

	


}