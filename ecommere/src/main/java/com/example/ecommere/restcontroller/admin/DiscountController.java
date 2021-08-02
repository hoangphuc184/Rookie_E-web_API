package com.example.ecommere.restcontroller.admin;

import com.example.ecommere.constant.ErrorCode;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @Autowired
    DiscountConverter discountConverter;

    @GetMapping("/discounts")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> getListDiscounts() throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        List<DiscountDTO> discountDTOList = new ArrayList<>();
        List<Discount> discounts = discountService.getAll();
        if (discounts == null) {
            response.setErrorCode(ErrorCode.LIST_DISC_NOT_FOUND_EXCEPTION);
        }else {
            for (Discount discount : discounts) {
                DiscountDTO discountDTO = discountConverter.convertToDTO(discount);
                discountDTOList.add(discountDTO);
            }
            response.setData(discountDTOList);
            response.setSuccessCode(SuccessCode.LIST_DISC_LOADED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/discounts/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> getDiscount(@PathVariable(value = "id") Long discountId)
            throws DataNotFoundException, ParseEntityDtoException {
        ResponseDTO response = new ResponseDTO();
        DiscountDTO discountDTO = discountConverter.convertToDTO(discountService.get(discountId));
        response.setData(discountDTO);
        response.setSuccessCode(SuccessCode.DISCOUNT_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/discounts")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ResponseDTO> updateDiscount(@RequestBody DiscountDTO newDiscountDTO, @PathVariable(value = "id") Long  discountId) throws ParseEntityDtoException, DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Discount discount = discountConverter.convertToEntity(newDiscountDTO);
        DiscountDTO discountDTO = discountConverter.convertToDTO(discountService.update(discount, discountId));
        if (discountDTO == null) {
            response.setErrorCode(ErrorCode.DISCOUNT_UPDATED_FAIL_EXCEPTION);
        } else {
            response.setData(discountDTO);
            response.setSuccessCode(SuccessCode.DISCOUNT_UPDATED_SUCCESS);
        }
        return ResponseEntity.ok().body(response);
    }
}
