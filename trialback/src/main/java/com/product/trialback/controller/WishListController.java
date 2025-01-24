package com.product.trialback.controller;

import com.product.trialback.dto.ProductDTO;
import com.product.trialback.services.WhishListService;
import jakarta.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wishList")
public class WishListController {

    private final WhishListService whishListService;

    public WishListController(WhishListService whishListService) {
        this.whishListService = whishListService;
    }

    @GetMapping
    public List<ProductDTO> whishList(Principal principal){
        return whishListService.whishList(principal.getName());
    }
    @PostMapping
    public List<ProductDTO> addToWishList(Principal principal, @QueryParam("productId") Integer productId){
        return whishListService.addProductToWhishList(principal.getName(),productId );
    }

    @DeleteMapping
    public List<ProductDTO> removeFromWishList(Principal principal, @QueryParam("productId") Integer productId){
        return whishListService.removeProductFromWhishList(principal.getName(),productId );
    }
    @DeleteMapping("/drop")
    public List<ProductDTO> dropWishList(Principal principal){
        return whishListService.dropWhishList(principal.getName());
    }
}
