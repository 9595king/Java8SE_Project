package mylab.student.control;

// 다른 패키지(mylab.student.entity)에 위치한 Student 클래스를 불러오기 위한 import 선언
import mylab.student.entity.Student;

/**
 * Student 클래스의 캡슐화 구현 및 학년 유효성 검증(1~4학년) 로직을 검증하는 테스트 클래스
 */
public class StudentTest {
    
    // Java 프로그램의 시작점(Entry Point) 메서드
	// String[] args : 프로그램이 실행될 때 커맨드 라인에서 전달받는 문자열 배열 매개변수
    public static void main(String[] args) {
        
        // 1. 정상 범위의 데이터(3학년)를 전달하여 Student 객체 생성 및 초기화
        Student student = new Student("20240001", "김민수", "컴퓨터공학", 3);
        
        // 2. 객체에 정상적으로 값이 저장되었는지 확인하기 위해 학생 정보 출력
        //    출력 결과: "김민수 / 컴퓨터공학 / 3학년"
        student.printInfo();

        // 3. 유효성 검증 테스트를 진행함을 알리는 콘솔 메시지 출력
        // System.out.println() : 자바에서 괄호 안의 내용을 콘솔 화면에 출력하고 자동으로 줄 바꿈(개행)을 해주는 표준 출력 명령어
        System.out.println("5학년으로 변경");
        
        // 4. 허용 범위(1~4학년)를 벗어나는 데이터인 '5'를 setGrade() 메서드의 인수로 전달
        //    -> setGrade() 내부의 범위 검사 logic이 작동하여 예외 메시지("학년은 1~4 사이의 값이어야 합니다.")를 콘솔에 출력함
        student.setGrade(5);
    }
}