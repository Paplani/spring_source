package com.example.book.repository.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.book.entity.book.Book;
import com.example.book.entity.book.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

    // where isbn = ''
    Book findByIsbn(String isbn);

    // where title = ''
    Optional<Book> findByTitle(String title);

    // // where title like '%자바'
    // List<Book> findByTitleContaining(String title);

    // // author
    // List<Book> findByAuthor(String author);

    // // 도서 가격이 12000 이상 35000 이하
    // List<Book> findByPriceBetween(int startPrice, int endPrice);

    // // 도서명이 자바이거나 작가이름이 자바인 도서 찾기
    // List<Book> findByTitleContainingOrAuthorContaining(String title, String
    // author);

    // // 도서명이 자바가 포함되어 있고 정렬 id > 0 (처리속도가 빨라짐), id desc
    // List<Book> findByTitleContainingAndIdGraterThanIdOrderByIdDesc(String title,
    // Long id);

    // query 작성할때 항상 Entity 기준
    @Query("SELECT b FROM Book b where b.title like %?1%")
    List<Book> findByTitle1(String title);

    @Query(value = "SELECT * FROM Book b where b.title like %?1%", nativeQuery = true)
    List<Book> findByTitle2(String title);

    @Query("SELECT b FROM Book b where b.title like %?1% or b.author like %?2%")
    List<Book> findByTitleAuthor(String title, String author);

    // 메소드 만들기
    // 1. 구현 방법이 없는 추상 메소드. 바로 위의 방법, 중괄호 없는걸 추상 메서드라고 함
    // 2. 아래방법
    public default Predicate makePredicate(String type, String keyword) {
        QBook book = QBook.book;

        // title like '%자바%' or writer like '%자바%' like category = '%자바%'
        // where title Like '%자바%' and id > 0
        // where writer Like '%자바%' and id > 0
        // where category Like '%자바%' and id > 0

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(book.id.gt(0L));

        if (type == null) {
            return builder;
        }

        if (type.equals("t")) {
            builder.and(book.title.contains(keyword));
        } else if (type.equals("w")) {
            builder.and(book.author.contains(keyword));
        } else if (type.equals("c")) {

        }

        return builder;
    }

}
