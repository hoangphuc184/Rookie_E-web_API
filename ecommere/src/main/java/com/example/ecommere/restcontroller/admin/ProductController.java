package com.example.ecommere.restcontroller.admin;


import com.example.ecommere.constant.ErrorCode;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductConverter productConverter;

    @GetMapping("/products")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<ResponseDTO> getListProducts(@RequestParam(value = "page", defaultValue = "0") int page,
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

    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public ResponseEntity<ResponseDTO> getProduct(@PathVariable(value = "id") Long productId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        ProductDTO productDTO = productConverter.convertToDTO(productService.get(productId));
        response.setData(productDTO);
        response.setSuccessCode(SuccessCode.PRODUCT_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody ProductDTO newProductDTO)
            throws ParseEntityDtoException, CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        Product product = productConverter.convertToEntity(newProductDTO);
        ProductDTO productDTO = productConverter.convertToDTO(productService.create(product));
        response.setData(productDTO);
        response.setSuccessCode(SuccessCode.PRODUCT_CREATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO newProductDTO, @PathVariable(value = "id") Long productId)
            throws ParseEntityDtoException, DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Product newProduct = productConverter.convertToEntity(newProductDTO);
        ProductDTO productDTO = productConverter.convertToDTO(productService.update(newProduct, productId));
        if (productDTO == null) {
            response.setErrorCode(ErrorCode.CATEGORY_UPDATED_FAIL_EXCEPTION);
        } else {
            response.setData(productDTO);
            response.setSuccessCode(SuccessCode.PRODUCT_UPDATED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }
}
