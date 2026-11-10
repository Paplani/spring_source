package com.example.book.locker;

import java.util.concurrent.locks.Lock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.locker.Locker;
import com.example.book.entity.locker.SportsMember;
import com.example.book.repository.locker.LockerRepository;
import com.example.book.repository.locker.SportsMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LockerTest {

    // 테스트 클래스는 JUnit이 만들기 때문에 생성자 주입 대신 필드에 @Autowired를 붙여 받아요.
    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private SportsMemberRepository sportsMemberRepository;

    @Test
    public void insert() {
        Locker locker1 = lockerRepository.save(Locker.builder().name("locker1").build());

        sportsMemberRepository.save(SportsMember.builder().name("홍길동").locker(locker1).build());
    }

    // R
    @Test
    public void read() {

        // locker 기준으로 조회
        Locker locker1 = lockerRepository.findById(1L).get();
        System.out.println(locker1);

        // sportsMember
        System.out.println(sportsMemberRepository.findById(1L).get());
    }

    // oneToOne => EAGER
    @Transactional
    @Test
    public void read2() {

        // locker 기준으로 조회
        Locker locker1 = lockerRepository.findById(1L).get();
        System.out.println(locker1);
        System.out.println(locker1.getSportsMember().getName());

        // sportsMember
        SportsMember sportsMember = sportsMemberRepository.findById(1L).get();
        System.out.println(sportsMember.getLocker().getName());
    }

    @Test
    public void read3() {

        // locker 기준으로 조회
        Locker locker1 = lockerRepository.findById(1L).get();
        System.out.println(locker1);
        System.out.println(locker1.getSportsMember().getName());
    }
}
