package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("중앙 도서관");

        System.out.println("=== 샘플 도서 추가 ===");
        addSampleBooks(library);

        System.out.println("\n=== 도서 검색 테스트 ===");
        testFindBook(library);

        System.out.println("\n=== 대출 테스트 ===");
        testCheckOut(library);

        System.out.println("\n=== 대출 가능 도서 목록 ===");
        displayAvailableBooks(library);

        System.out.println("\n=== 반납 테스트 ===");
        testReturn(library);

        System.out.println("\n=== 최종 대출 가능 도서 목록 ===");
        displayAvailableBooks(library);
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바의 정석", "남궁성", "9788994492032", 2016));
        library.addBook(new Book("클린 코드", "로버트 C. 마틴", "9788966260959", 2013));
        library.addBook(new Book("이펙티브 자바", "조슈아 블로크", "9788966262281", 2018));
        library.addBook(new Book("리팩터링", "마틴 파울러", "9791162241882", 2019));
        System.out.println("총 " + library.getTotalBooks() + "권의 도서가 추가되었습니다.");
    }

    private static void testFindBook(Library library) {
        String searchTitle = "클린 코드";
        Book foundBook = library.findBookByTitle(searchTitle);
        System.out.println("제목 검색 ('" + searchTitle + "'): " + (foundBook != null ? foundBook : "찾을 수 없음"));
    }

    private static void testCheckOut(Library library) {
        String isbn = "9788966260959"; // 클린 코드
        boolean success = library.checkOutBook(isbn);
        System.out.println("ISBN [" + isbn + "] 대출 처리: " + (success ? "성공" : "실패"));
    }

    private static void testReturn(Library library) {
        String isbn = "9788966260959"; // 클린 코드
        boolean success = library.returnBook(isbn);
        System.out.println("ISBN [" + isbn + "] 반납 처리: " + (success ? "성공" : "실패"));
    }

    private static void displayAvailableBooks(Library library) {
        System.out.println("대출 가능한 도서 수: " + library.getAvailableBooksCount() + " / 전체: " + library.getTotalBooks());
        for (Book book : library.getAvailableBooks()) {
            System.out.println(" - " + book);
        }
    }
}