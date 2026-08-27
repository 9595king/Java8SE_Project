// 예외 처리 관련 클래스들을 독립적으로 분리하여 관리하기 위해 패키지 경로를 지정합니다.
package mylab.student.exception;

// 자바 표준 클래스인 Exception을 상속(extends)받아 새로운 예외 클래스를 정의합니다.
// 이를 통해 자바의 예외 처리 메커니즘(throw, catch 등) 내에서 객체로 다룰 수 있게 됩니다.
public class InvalidGradeException extends Exception {
	// 예외 발생 시 전달할 에러 메시지(예: "학년은 1~4 사이의 값이어야 합니다.")를 매개변수로 받는 생성자입니다.
    public InvalidGradeException(String message) {
    	// 부모 클래스인 Exception의 생성자를 호출하여 에러 메시지를 넘겨줍니다.
    	// 이렇게 저장된 메시지는 나중에 예외를 잡았을 때 e.getMessage()로 가져와 출력할 수 있습니다.
        super(message);
    }
}