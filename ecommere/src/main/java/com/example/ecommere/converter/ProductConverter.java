package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.ProductDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Discount;
import com.example.ecommere.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductConverter {

    @Autowired
    ModelMapper modelMapper;

    public ProductDTO convertToDTO(Product product) throws ParseEntityDtoException {
        try {
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCategory_id(product.getCategory().getId());
            productDTO.setDiscount_id(product.getDiscount().getId());
            return productDTO;
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.ENTITY_DTO_FAIL_EXCEPTION);
        }
    }

    public Product convertToEntity(ProductDTO productDTO) throws ParseEntityDtoException {
        try {
            Product product = modelMapper.map(productDTO, Product.class);
            Category category = new Category();
            category.setId(productDTO.getCategory_id());
            Discount discount = new Discount();
            discount.setId(productDTO.getDiscount_id());
            product.setCategory(category);
            product.setDiscount(discount);
            return product;
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
