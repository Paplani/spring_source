package com.example.book.entity.shop;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "orderItems" })
@Entity
@Builder
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    private int price;

    private int stockQuantity;

    @Builder.Default
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void changeStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
