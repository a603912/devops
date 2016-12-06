package com.rohit.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.rohit.form.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Product findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM products WHERE id=:id";

		Product result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			
		}

		return result;
	}
	private static final class ProductMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("productName"));
			product.setProductPrice(rs.getInt("productPrice"));
			
			return product;
		}
	}

	@Override
	public List<Product> findAll() {
		String sql = "SELECT * FROM PRODUCTS";
		List<Product> result = namedParameterJdbcTemplate.query(sql, new ProductMapper());
		return result;

	}

	@Override
	public void save(Product product) {
KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO PRODUCTS(PRODUCTNAME, PRODUCTPRICE) "
				+ "VALUES ( :productName, :productPrice)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product), keyHolder);
		product.setId(keyHolder.getKey().intValue());
		
		
	}
	
private SqlParameterSource getSqlParameterByModel(Product product) {

		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", product.getId());
		paramSource.addValue("productName", product.getProductName());
		paramSource.addValue("productPrice", product.getProductPrice());
		

		return paramSource;
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE PRODUCTS SET PRODUCTNAME=:productName, PRODUCTprice=:productPrice WHERE id=:id";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product));
		
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM PRODUCTS WHERE id= :id";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
		
	}

}
