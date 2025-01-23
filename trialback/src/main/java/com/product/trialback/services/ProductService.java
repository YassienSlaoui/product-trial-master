package com.product.trialback.services;

import com.product.trialback.dto.ProductDTO;
import com.product.trialback.model.Product;

import java.util.List;

public interface ProductService {
    
    List<ProductDTO> getProducts();
    ProductDTO createProduct(ProductDTO product);

    ProductDTO updateProduct(int id, ProductDTO product);
    ProductDTO getProduct(int id);
    void deleteProduct(int id);


}
