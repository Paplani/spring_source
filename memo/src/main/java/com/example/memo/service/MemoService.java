package com.example.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.memo.dto.MemoDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private final ModelMapper modelMapper;

    public Long create(MemoDto dto) {

        // dto -> entity
        Memo memo = modelMapper.map(dto, Memo.class);
        return memoRepository.save(memo).getId();
    }

    public MemoDto read(Long id) {
        // entity -> dto
        Memo memo = memoRepository.findById(id).get();
        return modelMapper.map(memo, MemoDto.class);
    }

    public List<MemoDto> readAll() {
        // entity -> dto
        List<Memo> list = memoRepository.findAll();
        List<MemoDto> dtos = list.stream().map(memo -> modelMapper.map(memo, MemoDto.class))
                .collect(Collectors.toList());

        return dtos;

    }

    public Long update(MemoDto dto) {
        // dto -> entity
        Memo memo = memoRepository.findById(dto.getId()).get();
        memo.changeText(dto.getText());
        return memo.getId();
    }

    public void delete(Long id) {
        // entity -> dto
        memoRepository.deleteById(id);
    }

}
