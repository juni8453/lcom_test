package com.lcom_test.example.service;

import java.util.List;

import com.lcom_test.example.domain.Cart;
import com.lcom_test.example.domain.Images;
import com.lcom_test.example.domain.Product;

public interface ProductService {

	public void insertProduct(Product product);

	public List<Product> selectProductList(Product product);

	public Product getProduct(int pId);

	public void deleteProduct(int pId);

	public void insertPutCart(Cart cart);

	public List<Cart> selectCartList(Cart cart);

	public void deleteCart(int ctId);

	public void likeProduct(int pId);

	public void insertHeart(Product product);

	public List<Product> selectHeartList(String username);

	public void cancelLike(int pId);

	public void deleteHeart(Product product);

	public List<Product> selectRankList(Product product);

	}
