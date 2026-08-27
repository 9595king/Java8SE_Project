package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // 1. 도서관 객체 생성
        Library library = new Library("중앙 도서관");
        System.out.println("=== " + library.getName() + " 관리 시스템 ===");

        // 2. 도서 추가 (addBook)
        System.out.println("\n[1] 도서 등록");
        library.addBook(new Book("자바의 정석", "남궁성", "978-8994492032", 2016));
        library.addBook(new Book("클린 코드", "로버트 C. 마틴", "978-8966260959", 2013));
        library.addBook(new Book("이펙티브 자바", "조슈아 블로크", "978-8966262281", 2018));
        library.addBook(new Book("객체지향의 사실과 오해", "조영호", "978-8998139766", 2015));
        library.addBook(new Book("코딩의 기술", "로버트 C. 마틴", "978-8966260000", 2020));
        System.out.println("도서 등록 완료!");

        printLibraryStatus(library);

        // 3. 도서 검색 테스트 (findByTitle, findByAuthor, findByISBN)
        System.out.println("\n[2] 도서 검색 테스트");
        
        System.out.println("-> 제목 검색 ('클린 코드'):");
        Book bookByTitle = library.findByTitle("클린 코드");
        System.out.println("   " + (bookByTitle != null ? bookByTitle : "검색 결과 없음"));

        System.out.println("-> 저자 검색 ('로버트 C. 마틴'):");
        List<Book> booksByAuthor = library.findByAuthor("로버트 C. 마틴");
        for (Book book : booksByAuthor) {
            System.out.println("   " + book);
        }

        System.out.println("-> ISBN 검색 ('978-8966262281'):");
        Book bookByIsbn = library.findByISBN("978-8966262281");
        System.out.println("   " + (bookByIsbn != null ? bookByIsbn : "검색 결과 없음"));

        // 4. 도서 대출 테스트 (checkOutBook)
        System.out.println("\n[3] 도서 대출 테스트");
        String targetIsbn = "978-8994492032"; // 자바의 정석 ISBN
        
        System.out.println("-> '자바의 정석' 대출 시도...");
        boolean isCheckedOut = library.checkOutBook(targetIsbn);
        System.out.println("   대출 성공 여부: " + isCheckedOut);

        System.out.println("-> 이미 대출된 '자바의 정석' 재대출 시도...");
        boolean isReCheckedOut = library.checkOutBook(targetIsbn);
        System.out.println("   대출 성공 여부: " + isReCheckedOut);

        printLibraryStatus(library);

        // 5. 대출 가능 목록 조회 (getAvailableBooks)
        System.out.println("\n[4] 대출 가능한 도서 목록");
        for (Book book : library.getAvailableBooks()) {
            System.out.println("   " + book);
        }

        // 6. 도서 반납 테스트 (returnBook)
        System.out.println("\n[5] 도서 반납 테스트");
        System.out.println("-> '자바의 정석' 반납 시도...");
        boolean isReturned = library.returnBook(targetIsbn);
        System.out.println("   반납 성공 여부: " + isReturned);

        printLibraryStatus(library);
    }

    // 수량 상태 출력 메소드
    private static void printLibraryStatus(Library library) {
        System.out.println("----------------------------------------");
        System.out.println("  총 도서 수: " + library.getTotalBooks() + "권");
        System.out.println("  대출 가능 수: " + library.getAvailableBooksCount() + "권");
        System.out.println("  대출 중인 수: " + library.getBorrowedBooksCount() + "권");
        System.out.println("----------------------------------------");
    }
}