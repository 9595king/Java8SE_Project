package mylab.student.entity;

// 사용자 정의 예외 클래스(InvalidGradeException)를 사용하기 위해 임포트
import mylab.student.exception.InvalidGradeException;

/**
 * 학생 정보를 관리하는 엔티티(Entity) 클래스
 * 캡슐화(Encapsulation) 원칙에 따라 필드는 private, 접근용 메서드는 public으로 구현
 */
public class Student {
    // [필드(멤버 변수)]
    // 외부 객체에서 직접 접근하지 못하도록 private으로 은닉 처리
    private String studentId; // 학번
    private String name;      // 이름
    private String major;     // 전공
    private int grade;        // 학년

    // [생성자]
    // 1. 기본 생성자: 객체 생성 시 초기화 없이 생성할 때 사용
    public Student() {
    }

    // 2. 매개변수가 있는 생성자: 객체 생성 시 모든 필드를 전달받아 초기화
    public Student(String studentId, String name, String major, int grade) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        // grade 필드에 직접 할당하지 않고 유효성 검사가 포함된 setGrade() 호출
        setGrade(grade);
    }

    // [Getter / Setter 메서드]
    
    // 학번 조회 (Getter)
    public String getStudentId() {
        return studentId;
    }

    // 학번 수정 (Setter)
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // 이름 조회 (Getter)
    public String getName() {
        return name;
    }

    // 이름 수정 (Setter)
    public void setName(String name) {
        this.name = name;
    }

    // 전공 조회 (Getter)
    public String getMajor() {
        return major;
    }

    // 전공 수정 (Setter)
    public void setMajor(String major) {
        this.major = major;
    }

    // 학년 조회 (Getter)
    public int getGrade() {
        return grade;
    }

    /**
     * 학년 수정 (Setter) 및 데이터 유효성 검사
     * @param grade 변경할 학년 (1~4 범위만 허용)
     // param(파라미터, 매개변수) : 메서드나 함수가 실행될 때 외부에서 전달받는 입력값
     */
    public void setGrade(int grade) {
        try {
            // 1~4 범위를 벗어나는 데이터가 들어오면 예외를 발생시킴
            if (grade < 1 || grade > 4) {
                throw new InvalidGradeException("학년은 1~4 사이의 값이어야 합니다.");
            }
            // 검증을 통과한 유효한 값인 경우에만 인스턴스 변수에 저장
            this.grade = grade;
        } catch (InvalidGradeException e) {
            // 발생한 예외 객체에서 설정한 에러 메시지를 가져와 콘솔에 출력
            System.out.println(e.getMessage());
        }
    }

    // [정보 출력 메서드]
    // 학생의 이름, 전공, 학년 정보를 가공하여 콘솔에 출력
    public void printInfo() {
        System.out.println(name + " / " + major + " / " + grade + "학년");
    }
}