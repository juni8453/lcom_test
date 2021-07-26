package com.lcom_test.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcom_test.example.domain.Cart;
import com.lcom_test.example.domain.Images;
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
	public List<Product> selectProductList(int pageOpt) {
		return productMapper.selectProductList(pageOpt);
	}
	

	@Override
	public Product getProduct(int pId) {
		return productMapper.getProduct(pId);
	}

	@Override
	public void deleteProduct(int pId) {
		productMapper.deleteProduct(pId);
	}

	@Override
	public void insertPutCart(Cart cart) {
		productMapper.insertPutCart(cart);
		
	}

	@Override
	public List<Cart> selectCartList(Cart cart) {
		return productMapper.selectCartList(cart);
	}

	@Override
	public void deleteCart(int ctId) {
		productMapper.deleteCart(ctId);
		
	}
}
