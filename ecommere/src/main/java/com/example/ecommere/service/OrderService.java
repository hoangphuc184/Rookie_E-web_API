package com.example.ecommere.service;

import com.example.ecommere.model.Orders;

import java.util.List;

public interface OrderService {

    public List<Orders> getAll();

    public Orders get(Long orderId);

    public Orders create(Orders orders);

    public void delete(Long orderId);
}
