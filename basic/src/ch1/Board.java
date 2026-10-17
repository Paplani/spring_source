package ch1;

public class Board {
    // 멤버변수, property, 속성
    private Long id;
    private String title;
    private String contents;
    private User user;

    // 생성자 : 멤버 변수의 초기화 담당
    public Board(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    // 생성자 여러개 가능, 파라미터 개수는 틀려야 함. (오버로딩)
    public Board(Long id, String title, String contents, User user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Board [id=" + id + ", title=" + title + ", contents=" + contents + ", user=" + user + "]";
    }

    

}
