package com.lcom_test.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lcom_test.example.domain.Cart;
import com.lcom_test.example.domain.Heart;
import com.lcom_test.example.domain.Images;
import com.lcom_test.example.domain.Order;
import com.lcom_test.example.domain.Product;

@Mapper
public interface ProductMapper {
	public void insertProduct(Product product);

	public List<Product> selectProductList(Product product);

	public Product getProduct(int pId);
	
	public Product getProduct(String pName);

	public void deleteProduct(Product product);

	public void insertPutCart(Cart cart);

	public List<Cart> selectCartList(Cart cart);

	public void deleteCart(int ctId);

	public void likeProduct(int pId);

	public void insertHeart(Product product);

	public List<Product> selectHeartList(String username);

	public void cancelLike(int pId);

	public void deleteHeart(Product product);

	public List<Product> selectRankList(Product product);

	public List<Product> selectHotList(Product product);

	public void insertImage(Product product);

	public void updatepId(Images images);

	public void buyProduct(Order order);

	public List<Order> selectOrderList(Order order);

	public void updateQuantity(Order order);
}
