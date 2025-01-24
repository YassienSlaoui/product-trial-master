package com.product.trialback.services;

import com.product.trialback.dto.ProductDTO;

import java.util.List;

public interface WhishListService {

    List<ProductDTO> whishList(String username);
    List<ProductDTO> addProductToWhishList(String username,Integer productId);
    List<ProductDTO> removeProductFromWhishList(String username,Integer productId);
    List<ProductDTO> dropWhishList(String username);
}
