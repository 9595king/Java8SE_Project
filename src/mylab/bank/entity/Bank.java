package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    public String createSavingsAccount(String ownerName, double balance, double interestRate) {
        String accNum = "AC" + (nextAccountNumber++);
        accounts.add(new SavingsAccount(accNum, ownerName, balance, interestRate));
        return accNum;
    }

    public String createCheckingAccount(String ownerName, double balance, double withdrawalLimit) {
        String accNum = "AC" + (nextAccountNumber++);
        accounts.add(new CheckingAccount(accNum, ownerName, balance, withdrawalLimit));
        return accNum;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        // 결과값에 맞게 예외 메시지 수정
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account acc = findAccount(accountNumber);
        acc.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account acc = findAccount(accountNumber);
        acc.withdraw(amount);
    }

    public void transfer(String fromAccount, String toAccount, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account from = findAccount(fromAccount);
        Account to = findAccount(toAccount);
        
        from.withdraw(amount);
        to.deposit(amount);
    }

    public void printAllAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc.toString());
        }
    }
}