package com.lcom_test.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcom_test.example.mapper.ProductMapper;
import com.lcom_test.example.service.ProductService;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;
}
