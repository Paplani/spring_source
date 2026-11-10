package com.example.book.repository.shop;

import java.util.List;

import com.example.book.entity.shop.Item;
import com.example.book.entity.shop.Member;

public interface QueryDslOrderRepository {
    // 추상 메서드
    List<Member> members();

    List<Item> items();

    List<Object[]> joinTest();

    List<Object[]> subQueryTest();
}
