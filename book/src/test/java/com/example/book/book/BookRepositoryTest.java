package com.example.book.book;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.book.entity.book.Book;
import com.example.book.entity.book.QBook;
import com.example.book.repository.book.BookRepository;

@SpringBootTest
public class BookRepositoryTest {

    // spring이 만든 repository를 이 필드에 넣어줌
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void insert() {

        IntStream.rangeClosed(12, 100).forEach((i) -> {
            Book book = Book.builder().isbn("A101010" + i).title("파워 자바" + i).author("홍길동" + i).price(36000).build();

            bookRepository.save(book);
        });
    }

    @Test
    public void read() {

        List<Book> books = bookRepository.findAll();
        System.out.println(books);
    };

    @Test
    public void read1() {

        Book book = bookRepository.findById(1L).get();
        System.out.println(book);

        book = bookRepository.findByIsbn("A1010101");
        System.out.println(book);

        // List<Book> books = bookRepository.findBy();
        // System.out.println(books);

    };

    @Test
    public void read2() {
        // querydsl 적용후

        QBook book = QBook.book;

        Pageable pageable = PageRequest.of(0, 20, Sort.by("id").descending());
        Page<Book> result = bookRepository.findAll(book.id.gt(0L), pageable);
        System.out.println(result);
        System.out.println(result.getTotalElements());
    };

    @Test
    public void read3() {
        // querydsl makePredicate 적용후

        QBook book = QBook.book;

        Pageable pageable = PageRequest.of(0, 20, Sort.by("id").descending());
        Page<Book> result = bookRepository.findAll(bookRepository.makePredicate("t", "파워"), pageable);
        System.out.println(result);
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        System.out.println(result.getNumber());
        System.out.println(result.getNumberOfElements());
        System.out.println(result.getSize());
    };

    @Test
    public void update() {

        Book book = bookRepository.findById(1L).get();
        book.changePrice(42000);
        bookRepository.save(book);

    };

}
