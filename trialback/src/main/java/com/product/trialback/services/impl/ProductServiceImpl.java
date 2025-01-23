package com.product.trialback.services.impl;

import com.product.trialback.dto.ProductDTO;
import com.product.trialback.mapper.ProductMapper;
import com.product.trialback.model.Product;
import com.product.trialback.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    List<Product> products = new ArrayList<>();


    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @PostConstruct
    private void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("products.json")).getFile());
            List<Product> productList = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            products.addAll(productList);
        } catch (IOException e) {
            log.error("json parse failed");
        }
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.productListToProductDTOList(products);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        products.add(productMapper.productDTOtoProduct(productDTO));
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        Optional<Product> prodUpdated = products.stream().filter(p -> p.getId() == id).findFirst();
        if (prodUpdated.isPresent()) {
            int index = products.indexOf(prodUpdated.get());
            products.set(index, productMapper.productDTOtoProduct(productDTO));
        } else {
            log.warn("product not existed");
            return null;
        }
        return productDTO;
    }

    @Override
    public ProductDTO getProduct(int id) {
        Optional<Product> prod = products.stream().filter(p -> p.getId() == id).findFirst();

        return productMapper.productToProductDTO(prod.get());
    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> prod = products.stream().filter(p -> p.getId() == id).findFirst();
        if (prod.isPresent()) {
            products.remove(prod.get());
        } else {
            log.warn("product not exist");
        }
    }
}
