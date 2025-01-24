package com.product.trialback.services.impl;

import com.product.trialback.dto.ProductDTO;
import com.product.trialback.model.WhishList;
import com.product.trialback.services.ProductService;
import com.product.trialback.services.WhishListService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WhishListServiceImp implements WhishListService {

    private final ProductService productService;
    Map<String, WhishList> whishListMap = new HashMap<>();

    public WhishListServiceImp(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductDTO> whishList(String username) {
        if (whishListMap.containsKey(username)) {
            Set<Integer> listOfProductId = whishListMap.get(username).getProductSet();
            return listOfProductId.stream().map(productService::getProduct).toList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<ProductDTO> addProductToWhishList(String username, Integer productId) {
        if (whishListMap.containsKey(username)) {
            whishListMap.get(username).getProductSet().add(productId);
            return this.whishList(username);
        }
        WhishList whishList = new WhishList();
        Set<Integer> set = new HashSet<>();
        set.add(productId);
        whishList.setProductSet(set);
        whishListMap.put(username, whishList);
        return this.whishList(username);
    }

    @Override
    public List<ProductDTO> removeProductFromWhishList(String username, Integer productId) {
        if (whishListMap.containsKey(username)) {
            if (whishListMap.get(username).getProductSet().contains(productId)) {
                whishListMap.get(username).getProductSet().remove(productId);
                return this.whishList(username);
            }
            throw new ValidationException("You don't have this product in your wishlist");
        }
        throw new ValidationException("You don't have wishlist");

    }

    @Override
    public List<ProductDTO> dropWhishList(String username) {
        if (whishListMap.containsKey(username)) {
            whishListMap.get(username).getProductSet().clear();
            return this.whishList(username);
        }
        throw new ValidationException("You don't have wishlist");
    }
}
