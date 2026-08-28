package mylab.bank.control;

import mylab.bank.entity.Account;
import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;

public class BankDemo {
    public static void main(String[] args) throws Exception { // 계좌 생성 시 발생할 수 있는 Exception 던지기
        Bank bank = new Bank();
        
        System.out.println("=== 계좌 생성 ===");
        bank.createSavingsAccount("홍길동", 10000.0, 3.0);
        System.out.println("Saving(저축) 계좌가 생성되었습니다: " + bank.findAccount("AC1000"));
        
        bank.createCheckingAccount("김철수", 20000.0, 5000.0);
        System.out.println("체킹 계좌가 생성되었습니다: " + bank.findAccount("AC1001"));
        
        bank.createSavingsAccount("이영희", 30000.0, 2.0);
        System.out.println("저축 계좌가 생성되었습니다: " + bank.findAccount("AC1002"));

        System.out.println("\n=== 모든 계좌 목록 ===");
        bank.printAllAccounts();
        System.out.println("===================");

        System.out.println("\n=== 입금/출금 테스트 ===");
        bank.deposit("AC1000", 5000.0);
        bank.withdraw("AC1001", 3000.0);

        System.out.println("\n=== 이자 적용 테스트 ===");
        // UML에 따라 Bank 클래스 외부에서 Account를 찾아 instanceof로 형변환 후 처리
        Account acc1000 = bank.findAccount("AC1000");
        if (acc1000 instanceof SavingsAccount) {
            ((SavingsAccount) acc1000).applyInterest();
        }

        System.out.println("\n=== 계좌 이체 테스트 ===");
        bank.transfer("AC1002", "AC1001", 5000.0);
        System.out.println("5000.0원이 AC1002에서 AC1001로 송금되었습니다.");

        System.out.println("\n=== 모든 계좌 목록 ===");
        bank.printAllAccounts();
        System.out.println("===================");

        // 예외 상황 연출 및 결과 출력 처리
        try {
            // 체킹계좌 1회 한도 5000 초과 출금 시도
            bank.withdraw("AC1001", 6000.0);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            // 체킹계좌 1회 한도 5000 초과 송금 시도
            bank.transfer("AC1001", "AC1000", 6000.0);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            // 없는 계좌 조회 시도
            bank.findAccount("AC9999");
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}