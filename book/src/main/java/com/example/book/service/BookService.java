package com.example.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.book.Book;
import com.example.book.repository.book.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository; // 스프링이 생성자로 넣어줌
    private final ModelMapper mapper;

    // crud 호출

    public Long create(BookDto dto) {

        // dto => entity (ModelMapper)
        // Book book = mapper.map(dto, Book.class); DTO -> ENTITY

        // bookRepository.save(book) Insert 시킴

        return bookRepository.save(mapper.map(dto, Book.class)).getId();

    }

    public PageResultDto<BookDto> list(PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(),
                Sort.by("id").descending());
        Page<Book> result = bookRepository
                .findAll(bookRepository.makePredicate(pageRequestDto.getType(), pageRequestDto.getKeyword()), pageable);

        // entity => Dto 변경
        List<BookDto> dtoList = result.get().map(book -> mapper.map(book, BookDto.class)).collect(Collectors.toList());

        long totalCount = result.getTotalElements();
        return PageResultDto.<BookDto>withAll().dtoList(dtoList).totalCount(totalCount).pageRequestDto(pageRequestDto)
                .build();
    }

    public List<BookDto> readAll() {
        List<Book> books = bookRepository.findAll();

        // // entity => dto (한 권씩 변환해서 담기)
        // List<BookRequestDto> list = new ArrayList<>();
        // for (Book book : books) {
        // list.add(mapper.map(book, BookRequestDto.class));
        // }

        return books.stream().map(book -> mapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto readTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow();

        // entity => dto
        return mapper.map(book, BookDto.class);
    }

    public BookDto readIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);

        // entity => dto
        return mapper.map(book, BookDto.class);
    }

    public BookDto readId(Long id) {
        // 없으면 exception
        Book book = bookRepository.findById(id).orElseThrow();

        // entity => dto
        return mapper.map(book, BookDto.class);
    }

    public void update(BookDto dto) {

        // 먼저 수정할 대상 찾기 -> 값 바꾸기 -> 저장 -> update
        Book book = bookRepository.findById(dto.getId()).get();
        book.changePrice(dto.getPrice()); // changePrice는 entity에 커스텀 메서드로 선언해둠
        // save를 하면 create, update를 알아서 구별하는데 findById로 가져온게 있으면 update로 판단을 해줌
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

// 생성, 수정은 프론트에서 DTO로 받아온걸 ENTITY로 고쳐주는 작업 -> repo 호출 -> 처리
// 읽기, 삭제는 매개변수를 받아서 repo 호출해서 service 함수로 처리