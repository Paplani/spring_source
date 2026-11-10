package com.example.book.entity.shop;

import java.util.ArrayList;
import java.util.List;

import com.example.book.entity.shop.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "member", "orderItems" })
@Entity
@Builder
@Getter
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // 한명의 회원이 여러개의 주문이 가능하다
    // manyToOne의 기본은 EAGER, LAZY로 바꿔야 함
    // 주문에는 반드시 회원이 들어가 있어야 함 => optional = false
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    // 0, 1 -> Ordinary
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Builder.Default
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.REMOVE }, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

}
