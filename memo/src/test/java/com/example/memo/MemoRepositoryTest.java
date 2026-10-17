package com.example.memo;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

// java에서 테스트 라이브러리 : junit

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void insertTest() {
        LongStream.range(1, 11).forEach((i) -> {
            // Memo memo = new Memo();
            Memo memo = Memo.builder()
                    .text("memo text" + i)
                    .build();
            memoRepository.save(memo);
        });
    }

    // R
    @Test
    public void readTest() {
        // List<Memo> list = memoRepository.findAll();
        // System.out.println(list);

        Memo memo = memoRepository.findById(1L).get();
        System.out.println(memo);
    }

    // U
    @Test
    public void updateTest() {
        // 수정할 memo 조회
        Memo memo = memoRepository.findById(1L).get();

        memo.changeText("변경 memo");
        memoRepository.save(memo);
    }

    @Test
    public void deleteTest() {
        // // 삭제할 memo 조회
        // Memo memo = memoRepository.findById(1L).get();
        // memoRepository.delete(memo);

        memoRepository.deleteById(2L);
    }

}
