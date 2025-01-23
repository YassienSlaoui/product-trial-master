package com.product.trialback.mapper;

import com.product.trialback.dto.ProductDTO;
import com.product.trialback.model.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface  ProductMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    Product productDTOtoProduct(ProductDTO productDto);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productListToProductDTOList(List<Product> products);
}
