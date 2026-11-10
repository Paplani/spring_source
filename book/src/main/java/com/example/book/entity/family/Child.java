package com.example.book.entity.family;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = "parent")
@Entity
@Builder

public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // EAGER : Many 쪽의 객체를 가져올때 One도 즉시 가져와라 => left join
    // LAZY : Many 쪽 객체만 처음에는 가져오고
    // optional = false : 무조건 부모가 있어야한다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parent parent;

    public void setParent(Parent parent) {
        if (this.parent != null) {
            this.parent.getChilds().remove(this);
        }
        this.parent = parent;
        parent.getChilds().add(this);
    }

}
