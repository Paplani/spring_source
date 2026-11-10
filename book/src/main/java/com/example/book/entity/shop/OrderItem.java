package com.example.book.entity.shop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"order", "item"})
@Entity
@Builder
@Getter 

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    // 하나의 주문에는 여러 주문상품이 들어갈 수 있다
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    // 하나의 상품은 여러 주문상품에 포함될수 있다
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    private int orderPrice;
    private int count;

    public void changeOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
