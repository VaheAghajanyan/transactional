package org.example.repository;

import org.example.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProduct(Product product) {
        String sql = "INSERT INTO product VALUES (?, ?)";
        Object[] args = {product.getId(), product.getName()};
        this.jdbcTemplate.update(sql, args);
    }
}
