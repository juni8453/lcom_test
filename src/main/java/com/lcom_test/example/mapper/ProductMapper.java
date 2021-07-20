package com.lcom_test.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lcom_test.example.domain.Cart;
import com.lcom_test.example.domain.Images;
import com.lcom_test.example.domain.Product;

@Mapper
public interface ProductMapper {
	public void insertProduct(Product product);

	public List<Product> selectProductList(int pageOpt);

	public Product getProduct(int pId);

	public void deleteProduct(int pId);

	public void insertPutCart(Cart cart);

	public List<Cart> selectCartList(String username);

}
