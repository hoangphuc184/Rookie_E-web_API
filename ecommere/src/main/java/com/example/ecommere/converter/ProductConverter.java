package com.example.ecommere.converter;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.dto.ProductDTO;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Category;
import com.example.ecommere.model.Discount;
import com.example.ecommere.model.Product;
import com.example.ecommere.repository.CategoryRepository;
import com.example.ecommere.repository.DiscountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ProductConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DiscountRepository discountRepository;

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
            Optional<Category> optCate = categoryRepository.findById(productDTO.getCategory_id());
            Category category = optCate.get();
            Optional<Discount> optDisc = discountRepository.findById(productDTO.getDiscount_id());
            Discount discount = optDisc.get();
            product.setCategory(category);
            product.setDiscount(discount);
            return product;
        }catch (Exception ex) {
            throw new ParseEntityDtoException(ErrorCode.DTO_ENTITY_FAIL_EXCEPTION);
        }
    }
}
