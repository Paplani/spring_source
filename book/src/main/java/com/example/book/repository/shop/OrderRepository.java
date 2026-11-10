package com.example.book.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.book.entity.shop.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order>, QueryDslOrderRepository {

}
