package com.example.board.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.board.entity.Board;
import com.example.board.entity.QBoard;
import com.example.board.entity.QMember;
import com.example.board.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Object[] getBoardByBno(Long bno) {

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(board.writer, member);
        query.where(board.bno.eq(bno));

        // bno의 댓글 개수 구하기
        JPQLQuery<Long> replyCount = JPAExpressions.select(reply.rno.count()).from(reply).where(reply.board.eq(board))
                .groupBy(reply.board);

        JPQLQuery<Tuple> tuple = query.select(board, member, replyCount);

        Object[] result = tuple.fetch().get(0).toArray();
        return result;
    }

    @Override
    public Page<Object[]> list(String type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(member).on(board.writer.eq(member));

        // bno의 댓글 개수 구하기
        JPQLQuery<Long> replyCount = JPAExpressions.select(reply.rno.count()).from(reply).where(reply.board.eq(board))
                .groupBy(reply.board);

        JPQLQuery<Tuple> tuple = query.select(board, member, replyCount);

        // where 절 bno > 0 and b.title = '~'
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(board.bno.gt(0));

        // type = "t" or type = "tc" or type = "tcw"
        // where 절 bno > 0 and b.title like '%자바%'
        // where 절 bno > 0 and b.title like '%자바%' or b.content like '%자바%'
        // where 절 bno > 0 and b.title like '%자바%' or b.content like '%자바%' or b.writer
        // like '%자바%'
        if (type != null) {
            String[] typeArr = type.split("");
            // tcw 일때 typeArr = {"t","c","w"}

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(board.writer.name.contains(keyword));
                        break;
                }
            }

            builder.and(conditionBuilder);
        }

        tuple.where(builder);

        // tuple.orderBy(board.bno.desc());

        // 페이지 나누기
        // QuerydslPredicatedExecutor를 받는 경우에는 메소드로 제공해줌.

        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            // order 컬럼 가져오기
            String prop = order.getProperty();

            PathBuilder<Board> orderByExpression = new PathBuilder<>(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();
        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);

    }

}
