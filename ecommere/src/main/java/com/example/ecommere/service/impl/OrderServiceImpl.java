package com.example.ecommere.service.impl;

import com.example.ecommere.model.Orders;
import com.example.ecommere.repository.OrderRepository;
import com.example.ecommere.service.OrderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

    public Orders get(Long orderId) {
        Optional<Orders> optOrder = orderRepository.findById(orderId);
        return optOrder.orElse(null);
    }

    public Orders create(Orders orders) {
        return orderRepository.save(orders);
    }

    public void delete(Long orderId) {
        Optional<Orders> optOrder = orderRepository.findById(orderId);
        optOrder.map(order -> {
            order.setIsDeleted(true);
            return orderRepository.save(order);
        });
        log.info("Order deleted " + optOrder.get().getId());
    }
}
