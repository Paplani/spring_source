package com.example.book.family;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.entity.family.Child;
import com.example.book.entity.family.Parent;
import com.example.book.repository.family.ChildRepository;
import com.example.book.repository.family.ParentRepository;

@Transactional
@SpringBootTest
public class ParentTest {
    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Commit
    @Test
    public void insert() {
        Parent parent = Parent.builder().name("parent1").build();
        parentRepository.save(parent);

        Child child1 = Child.builder().name("child1").parent(parent).build();
        childRepository.save(child1);

        child1 = Child.builder().name("child2").parent(parent).build();
        childRepository.save(child1);
    }

    // dirty check 안함
    @Transactional(readOnly = true)
    @Test
    public void read() {
        Parent p = parentRepository.findById(5L).get();
        System.err.println(p);
        p.getChilds().forEach(child -> System.out.println(child));
    }

    // dirty check 해줌
    // @Transactional이 있어야 변경 감지로 update가 나감
    @Transactional
    @Commit
    @Test
    public void update() {
        Parent p = parentRepository.findById(5L).get();
        p.changeName("change parent");
        // dirty check => save() 명시적으로 부르지 않아도 됨
    }

    @Commit
    @Test
    public void insertCascade() {
        Parent parent = Parent.builder().name("parent2").build();

        Child child1 = Child.builder().name("child1").parent(parent).build();

        Child child2 = Child.builder().name("child2").parent(parent).build();

        parent.getChilds().add(child1);
        parent.getChilds().add(child2);
        parentRepository.save(parent);
        // => 부모가 저장되면서 자식도 저장됨
        // 자식에 cascade = CascadeType.PERSIST 있어서
    }

    @Commit
    @Test
    public void insertCascade2() {
        Parent parent = Parent.builder().name("parent3").build();

        Child child1 = Child.builder().name("child1").build();
        child1.setParent(parent);

        Child child2 = Child.builder().name("child2").build();
        child2.setParent(parent);

        parentRepository.save(parent);
    }

    @Commit
    @Test
    public void delete() {

        // 자식 먼저 삭제, 그 다음에 부모 삭제
        childRepository.deleteById(9L);
        childRepository.deleteById(10L);

        parentRepository.deleteById(5L);
    }

    @Commit
    @Test
    public void deleteCascade() {

        // 부모 지우면 자식도 같이 삭제하기
        parentRepository.deleteById(6L);
    }

    @Commit
    @Test
    public void deleteOrphanTest() {

        Parent p = parentRepository.findById(7L).get();
        p.getChilds().remove(0);
        // parentRepository.save(p);
    }

}
