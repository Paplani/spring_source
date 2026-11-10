package com.example.book.entity.locker;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "sportsMember")
@Entity
@Builder
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    private Long id;

    @Column(name = "locker_name")
    private String name;

    @OneToOne(mappedBy = "locker", fetch = FetchType.LAZY)
    private SportsMember sportsMember;

    public void changeName(String name) {
        this.name = name;
    }

}
