package com.product.trialback.controller;

import com.product.trialback.dto.ProductDTO;
import com.product.trialback.model.UserEntity;
import com.product.trialback.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") int id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO, Authentication authentication) {

        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        String email = userDetails.getMail();

        if (!"admin@admin.com".equals(email)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
        }
        return productService.createProduct(productDTO);
    }

    @PatchMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") int id, @RequestBody ProductDTO productDTO, Authentication authentication) {

        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        String email = userDetails.getMail();

        if (!"admin@admin.com".equals(email)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
        }
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id, Authentication authentication) {

        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        String email = userDetails.getMail();

        if (!"admin@admin.com".equals(email)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
        }
        productService.deleteProduct(id);
    }
}
