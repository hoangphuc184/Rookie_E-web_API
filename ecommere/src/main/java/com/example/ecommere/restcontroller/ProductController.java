package com.example.ecommere.restcontroller;


import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.ProductConverter;
import com.example.ecommere.dto.ProductDTO;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Product;
import com.example.ecommere.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductConverter productConverter;

    @GetMapping("/products")
    public ResponseEntity<ResponseDTO> getListProducts() throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productService.getAll()) {
            ProductDTO productDTO = productConverter.convertToDTO(product);
            productDTOList.add(productDTO);
        }
        response.setData(productDTOList);
        response.setSuccessCode(SuccessCode.LIST_PRO_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> getProduct(@PathVariable(value = "id") Long productId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        ProductDTO productDTO = productConverter.convertToDTO(productService.get(productId));
        response.setData(productDTO);
        response.setSuccessCode(SuccessCode.PRODUCT_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/products")
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody ProductDTO newProductDTO)
            throws ParseEntityDtoException, CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        Product product = productConverter.convertToEntity(newProductDTO);
        ProductDTO productDTO = productConverter.convertToDTO(productService.create(product));
        response.setData(productDTO);
        response.setSuccessCode(SuccessCode.PRODUCT_CREATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable(value = "id") Long productId)
            throws DeleteDataFailException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Product product = productService.delete(productId);
        ProductDTO productDTO = productConverter.convertToDTO(product);
        response.setData(productDTO);
        response.setSuccessCode(SuccessCode.PRODUCT_DELETED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody ProductDTO newProductDTO, @PathVariable(value = "id") Long productId)
            throws ParseEntityDtoException, DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Product product = productConverter.convertToEntity(newProductDTO);
        ProductDTO productDTO = productConverter.convertToDTO(productService.update(product, productId));
        response.setData(productDTO);
        response.setSuccessCode(SuccessCode.PRODUCT_UPDATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }
}
