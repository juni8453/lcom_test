package com.lcom_test.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcom_test.example.domain.Cart;
import com.lcom_test.example.domain.Heart;
import com.lcom_test.example.domain.HomeImg;
import com.lcom_test.example.domain.Images;
import com.lcom_test.example.domain.Order;
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
	public List<Product> selectProductList(Product product) {
		return productMapper.selectProductList(product);
	}
	
	@Override
	public Product getProduct(String pName) {
		return productMapper.getProduct(pName);
	}

	@Override
	public void deleteProduct(Product product) {
		productMapper.deleteProduct(product);
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

	@Override
	public void likeProduct(int pId) {
		productMapper.likeProduct(pId);
	}

	@Override
	public void insertHeart(Product product) {
		productMapper.insertHeart(product);
		
	}

	@Override
	public List<Product> selectHeartList(String username) {
		return productMapper.selectHeartList(username);
	}

	@Override
	public void cancelLike(int pId) {
		productMapper.cancelLike(pId);
	}

	@Override
	public void deleteHeart(Product product) {
		productMapper.deleteHeart(product);
		
	}

	@Override
	public List<Product> selectRankList(Product product) {
		return productMapper.selectRankList(product);
	}

	@Override
	public List<Product> selectHotList(Product product) {
		return productMapper.selectHotList(product);
	}

	@Override
	public void insertImage(Product product) {
		productMapper.insertImage(product);
		
	}

	@Override
	public void updatepId(Images images) {
		productMapper.updatepId(images);
		
	}

	@Override
	public void buyProduct(Order order) {
		productMapper.buyProduct(order);
	}

	@Override
	public List<Order> selectOrderList(Order order) {		
		return productMapper.selectOrderList(order);
	}

	@Override
	public void updateQuantity(Order order) {
		productMapper.updateQuantity(order);
		
	}

	@Override
	public List<HomeImg> selectHomeImgList(HomeImg homeimg) {
		return productMapper.selectHomeImgList(homeimg);
	}

	
}