package com.example.ecommere.restcontroller;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.ProductConverter;
import com.example.ecommere.dto.ProductDTO;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Product;
import com.example.ecommere.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/home")
public class PublicController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductConverter productConverter;

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> getProductDetail(@PathVariable(value = "id") Long productId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Product product = productService.get(productId);
        ProductDTO productDTO = productConverter.convertToDTO(product);
        if (productDTO == null) {
            response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION);
        }else {
            response.setData(productDTO);
            response.setSuccessCode(SuccessCode.PRODUCT_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getListProductsForHome(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "limit", defaultValue = "10") int limit)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productService.getAll(page, limit)) {
            ProductDTO productDTO = productConverter.convertToDTO(product);
            productDTOList.add(productDTO);
        }
        response.setData(productDTOList);
        response.setSuccessCode(SuccessCode.LIST_PRO_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseDTO> getListProductsByCategory(@RequestParam(value = "cate") Long categoryId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (categoryId == null){
            response.setErrorCode(ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION);
        }else {
            for(Product product : productService.getAllByCategory(categoryId)) {
                ProductDTO productDTO = productConverter.convertToDTO(product);
                productDTOList.add(productDTO);
            }
            response.setData(productDTOList);
            response.setSuccessCode(SuccessCode.LIST_PRO_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

}