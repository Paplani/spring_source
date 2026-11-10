package com.example.board.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResultDto<E> {
    private List<E> dtoList;
    private List<Integer> pageNumList;
    private PageRequestDto pageRequestDto;

    private boolean prev, next;

    private long totalCount;

    private int prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResultDto(List<E> dtoList, PageRequestDto pageRequestDto, long totalCount) {
        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalCount = totalCount;

        int end = (int) (Math.ceil(pageRequestDto.getPage() / 10.0)) * 10;
        int start = end - 9;

        int last = (int) (Math.ceil((totalCount / (double) pageRequestDto.getSize())));

        end = end > last ? last : end;
        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDto.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        
        if (next) {
            this.nextPage = end + 1;
        }

        totalPage = this.pageNumList.size();
        this.current = pageRequestDto.getPage();
    }

}
