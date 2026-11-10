package com.example.book.locker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.locker.Locker;
import com.example.book.repository.locker.LockerRepository;
import com.example.book.repository.locker.SportsMemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class PersistenceTest {

    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private SportsMemberRepository sportsMemberRepository;

    @PersistenceContext
    EntityManager em; // 영속성 관리

    @Test
    public void persistenceTest() {

        Locker locker = Locker.builder().name("new 상태").build();
        System.out.println("1. 비영속 상태" + locker);

        // 영속성 관리 시작(managed 상태)
        em.persist(locker);
        System.out.println("2. 영속 상태 : " + locker);

        // 락커명 변경
        locker.changeName("변경");
        System.out.println("3.영속 상태에서 변경 : " + locker);

        // DB 에 반영하기
        em.flush();
        System.out.println("4. flush 후 DB 반영 완료");

        // 관리 중단
        em.detach(locker);
        locker.changeName("변경2");
        System.out.println("5.detach 상태에서 변경 : " + locker);
        // DB 반영
        em.flush();
    }

}
