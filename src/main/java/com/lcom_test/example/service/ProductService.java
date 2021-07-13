package com.lcom_test.example.service;

import java.util.List;

import com.lcom_test.example.domain.Product;

public interface ProductService {

	public void insertProduct(Product product);

	public List<Product> selectProductList();

	public List<Product> selectProductListTest(int pageOpt);

}
