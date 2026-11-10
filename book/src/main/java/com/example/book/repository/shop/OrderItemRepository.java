package com.example.book.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.shop.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
