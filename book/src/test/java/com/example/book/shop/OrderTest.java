package com.example.book.shop;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.book.entity.shop.Item;
import com.example.book.entity.shop.Member;
import com.example.book.entity.shop.Order;
import com.example.book.entity.shop.OrderItem;
import com.example.book.entity.shop.QItem;
import com.example.book.entity.shop.QOrder;
import com.example.book.entity.shop.OrderStatus;
import com.example.book.repository.shop.ItemRepository;
import com.example.book.repository.shop.MemberRepository;
import com.example.book.repository.shop.OrderItemRepository;
import com.example.book.repository.shop.OrderRepository;

@org.springframework.transaction.annotation.Transactional // dirty check
@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Commit
    @Test
    public void testMemberInsert() {

        IntStream.range(1, 6).forEach(i -> {
            Member member = Member.builder().name("user" + i).city("서울").build();
            memberRepository.save(member);
        });
    }

    @Commit
    @Test
    public void testItemInsert() {

        IntStream.range(1, 6).forEach(i -> {
            Item item = Item.builder().name("item" + i).price(i * 20000).stockQuantity(i * 5).build();
            itemRepository.save(item);
        });
    }

    @Commit
    @Test
    public void testOrderInsert() {

        // 주문하면? ex: 1번 user가 3번 제품 주문하다
        // order, orderitem, member

        // 누가 주문해?
        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(3L).get();

        Order order = Order.builder().orderStatus(OrderStatus.ORDER).member(member).build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder().item(item).order(order).orderPrice(item.getPrice()).count(1).build();
        orderItemRepository.save(orderItem);

    }

    @Commit
    @Test
    public void testCascadeInsert() {

        // 주문하면? ex: 1번 user가 3번 제품 주문하다
        // order, orderitem, member

        // 누가 주문해?
        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(3L).get();

        Order order = Order.builder().orderStatus(OrderStatus.ORDER).member(member).build();

        OrderItem orderItem = OrderItem.builder().item(item).order(order).orderPrice(item.getPrice()).count(1).build();

        order.getOrderItems().add(orderItem);
        orderRepository.save(order);

        // orderItemRepository.save(orderItem);

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Test
    public void testList() {

        // 주문내역 조회
        Order order = orderRepository.findById(1L).get();
        System.out.println(order); // orderRepository.save(order);

        // 주문 상품 조회
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> System.out.println(orderItem)); // OrderItem(id=1, orderPrice=60000, count=1)

        // 주문자 조회
        System.out.println(order.getMember()); // Member(id=1, name=user1, city=서울)

    }

    @Commit
    @Test
    public void testUpdate() {

        Item item = itemRepository.findById(2L).get();
        item.changeStockQuantity(150);

        OrderItem orderItem = orderItemRepository.findById(1L).get();
        orderItem.changeOrderPrice(28000);

        // itemRepository.save(item);
        // orderItemRepository.save(orderItem);

    }

    @Commit
    @Test
    public void testDelete() {

        // order_item 먼저 삭제하고 order 삭제

        // order 삭제하면 order_item도 같이 삭제되는 법 -> cascade 설정되있어야함
        orderRepository.deleteById(3L);

    }

    @Commit
    @Test
    public void testDelete2() {

        // orphanRemoval = true
        Order order = orderRepository.findById(2L).get();
        order.getOrderItems().remove(0);
    }

    @Test
    public void querydslTest() {

        QItem item = QItem.item;
        QOrder order = QOrder.order;
        // 제품명 item1 조회
        System.out.println(itemRepository.findAll(item.name.eq("item1")));

        // 제품명 item 으로 시작하는 제품 조회
        System.out.println(itemRepository.findAll(item.name.startsWith("item1")));

        // 제품명 item 으로 끝나는 제품 조회
        System.out.println(itemRepository.findAll(item.name.eq("item1").and(item.price.gt(20000))));

        // 제품명 item1 이고, 가격이 20000 초과인 제품 조회
        System.out.println(itemRepository.findAll(item.name.eq("item1").and(item.price.gt(20000))));

        // 주문자가 user1 이고, 상태가 ORDER 제품 조회
        // System.out.println(orderRepository.findAll(
        // order.member.eq(Member.builder().id(1L).build()).and(order.orderStatus.eq(OrderStatus.ORDER))));

    }

    @Test
    public void memberTest() {
        List<Member> list = orderRepository.members();
        System.out.println(list);

        List<Item> list1 = orderRepository.items();
        System.out.println(list1);

        List<Object[]> list2 = orderRepository.joinTest();
        for (Object[] objects : list2) {
            Order order = (Order) objects[0];
            Member member = (Member) objects[1];
            System.out.println(order);
            System.out.println(member);
        }

        List<Object[]> list3 = orderRepository.subQueryTest();
        for (Object[] objects : list3) {
            Order order = (Order) objects[0];
            Member member = (Member) objects[1];
            OrderItem orderItem = (OrderItem) objects[2];
            Long cnt = (Long) objects[3];
            Long sum = (Long) objects[4];
            System.out.println(order);
            System.out.println(member);
            System.out.println(orderItem);
            System.out.println(cnt);
            System.out.println(sum);
        }

    }
}
