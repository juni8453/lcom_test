package com.lcom_test.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcom_test.example.domain.Product;
import com.lcom_test.example.mapper.ProductMapper;
import com.lcom_test.example.service.ProductService;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;

	@Override
	public void insertProduct(Product product) {
		productMapper.insertProduct(product);
		
	}

	@Override
	public List<Product> selectProductList() {
		return productMapper.selectProductList();
	}

	@Override
	public List<Product> selectProductListTest(int pageOpt) {
		return productMapper.selectProductListTest(pageOpt);
	}
}
