package com.lcom_test.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.lcom_test.example.domain.Product;

@Mapper
public interface ProductMapper {
	public void insertProduct(Product product);

	public List<Product> selectProductList();

}
