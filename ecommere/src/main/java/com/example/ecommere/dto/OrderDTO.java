package com.example.ecommere.dto;

import com.example.ecommere.model.PaymentDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    @NotNull
    private Long user_id;

    private List<OrderItemDTO> orderItemList;

    private Double totalBill;

    @NotNull
    private PaymentDetailDTO paymentDetail;


}
