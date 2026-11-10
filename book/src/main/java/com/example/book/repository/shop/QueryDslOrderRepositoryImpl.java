package com.example.book.repository.shop;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.book.entity.shop.Item;
import com.example.book.entity.shop.Member;
import com.example.book.entity.shop.Order;
import com.example.book.entity.shop.QItem;
import com.example.book.entity.shop.QMember;
import com.example.book.entity.shop.QOrder;
import com.example.book.entity.shop.QOrderItem;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class QueryDslOrderRepositoryImpl extends QuerydslRepositorySupport implements QueryDslOrderRepository {

    public QueryDslOrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Member> members() {

        // 전체 멤버에서 "user1" 조회
        QMember member = QMember.member;

        JPQLQuery<Member> query = from(member);
        query.where(member.name.eq("user1")).orderBy(member.name.desc());
        query.select(member);

        // 결과를 추출
        List<Member> members = query.fetch();

        return members;
    }

    @Override
    public List<Item> items() {

        // item 에서 이름이 item1 이고 가격은 200000 이상인 아이템 추출
        QItem item = QItem.item;

        JPQLQuery<Item> query = from(item);
        query.where(item.name.eq("item1").and(item.price.gt(200000)));
        query.select(item);

        List<Item> items = query.fetch();
        return items;
    }

    @Override
    public List<Object[]> joinTest() {

        // 주문 내역, 주문자 내역 조회
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        // select o1.*, m.*
        // from orders o11 join members m on o1.user_id = m.user_id
        JPQLQuery<Order> query = from(order);
        query.join(member).on(order.member.eq(member));
        JPQLQuery<Tuple> tuple = query.select(order, member); // 타입이 튜플임

        List<Tuple> result = tuple.fetch();

        // Tuple => Object []
        List<Object[]> list = result.stream().map(t -> t.toArray()).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Object[]> subQueryTest() {

        // 동일한 주문 아이디에 주문한 상품의 총 개수를 확인
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QOrderItem orderItem = QOrderItem.orderItem;

        JPQLQuery<Order> query = from(order);
        query.join(member).on(order.member.eq(member)).join(orderItem).on(order.eq(orderItem.order));

        JPQLQuery<Long> orderCnt = JPAExpressions.select(orderItem.order.count()).from(orderItem)
                .where(orderItem.order.eq(order))
                .groupBy(orderItem.order);

        JPQLQuery<Integer> orderSum = JPAExpressions.select(orderItem.orderPrice.sum()).from(orderItem)
                .where(orderItem.order.eq(order))
                .groupBy(orderItem.order);

        JPQLQuery<Tuple> tuple = query.select(order, member, orderItem, orderCnt, orderSum);
        List<Tuple> result = tuple.fetch();

        return result.stream().map(t -> t.toArray()).collect(Collectors.toList());

    }

}
