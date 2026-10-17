package ch1;



public class Test {
    public static void main(String[] args) {

        // Data Type
        // 기본 타입 : 문자(문자, 문자열), 숫자(정수, 실수), 불리언
        // 'a' : 문자 char
        // "a" : 문자열 String
        // 정수 : int(4바이트), long(8바이트) + byte, short
        // 실수 : float(4바이트), double(8바이트)

        // 객체타입 : 대문자로 시작, 배열
        // 'a' : 문자 Character
        // "a" : 문자열 String
        // 정수 : integer(4바이트), Long(8바이트) + byte, short
        // 실수 : Float(4바이트), Double(8바이트)

        System.out.println("Hello");

        // 객체 생성 시 생성자 사용 (파라미터가 없으면) => default 생성자
        // 클래스에 생성자가 하나도 없을 때 default 생성자를 자동으로 만들어서 해줌
        Board board = new Board(1L, "", "");

    }
}
