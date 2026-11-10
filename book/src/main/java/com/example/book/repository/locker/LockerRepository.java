package com.example.book.repository.locker;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.locker.Locker;

public interface LockerRepository extends JpaRepository<Locker, Long> {

}
