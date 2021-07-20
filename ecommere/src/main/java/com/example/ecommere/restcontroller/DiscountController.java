package com.example.ecommere.restcontroller;

import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.converter.DiscountConverter;
import com.example.ecommere.dto.DiscountDTO;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.exception.CreateDataFailException;
import com.example.ecommere.exception.DataNotFoundException;
import com.example.ecommere.exception.DeleteDataFailException;
import com.example.ecommere.exception.ParseEntityDtoException;
import com.example.ecommere.model.Discount;
import com.example.ecommere.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @Autowired
    DiscountConverter discountConverter;

    @GetMapping("/discounts")
    public ResponseEntity<ResponseDTO> getListDiscounts() throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<DiscountDTO> discountDTOList = new ArrayList<>();
        for (Discount discount : discountService.getAll()) {
            DiscountDTO discountDTO = discountConverter.convertToDTO(discount);
            discountDTOList.add(discountDTO);
        }
        response.setData(discountDTOList);
        response.setSuccessCode(SuccessCode.LIST_DISC_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/discounts/{id}")
    public ResponseEntity<ResponseDTO> getDiscount(@PathVariable(value = "id") Long discountId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        DiscountDTO discountDTO = discountConverter.convertToDTO(discountService.get(discountId));
        response.setData(discountDTO);
        response.setSuccessCode(SuccessCode.DISCOUNT_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/discounts")
    public ResponseEntity<ResponseDTO> createDiscount(@RequestBody DiscountDTO newDiscountDTO)
            throws ParseEntityDtoException, CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        Discount discount = discountConverter.convertToEntity(newDiscountDTO);
        DiscountDTO discountDTO = discountConverter.convertToDTO(discountService.create(discount));
        response.setData(discountDTO);
        response.setSuccessCode(SuccessCode.DISCOUNT_CREATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/discounts/{id}")
    public ResponseEntity<ResponseDTO> deleteDiscount(@PathVariable(value = "id") Long discountId)
            throws DeleteDataFailException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        Discount discount = discountService.delete(discountId);
        DiscountDTO discountDTO = discountConverter.convertToDTO(discount);
        response.setData(discountDTO);
        response.setSuccessCode(SuccessCode.DISCOUNT_DELETED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/discounts/{id}")
    public ResponseEntity<ResponseDTO> updateDiscount(@RequestBody DiscountDTO newDiscountDTO, @PathVariable(value = "id") Long  discountId) throws ParseEntityDtoException, DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Discount discount = discountConverter.convertToEntity(newDiscountDTO);
        DiscountDTO discountDTO = discountConverter.convertToDTO(discountService.update(discount, discountId));
        response.setData(discountDTO);
        response.setSuccessCode(SuccessCode.DISCOUNT_UPDATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }
}
