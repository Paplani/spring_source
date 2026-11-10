package com.example.book.entity.family;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import lombok.Setter;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "childs")
@Entity
@Builder
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<Child> childs = new ArrayList<>();

    public void changeName(String name) {
        this.name = name;
    }
}
