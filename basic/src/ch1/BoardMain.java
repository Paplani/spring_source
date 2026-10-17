package ch1;

public class BoardMain {
    public static void main(String[] args) {
        // user가 게시글을 작성
        User user = new User(1L, "홍길동", "hong123@gmail.com");
        Board board = new Board(1L, "게시글", "게시글", user);

        // 확인
        System.out.println(board); // ch1.Board@6d06d69c  -> toString()
    }
}
