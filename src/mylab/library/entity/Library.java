package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private String name;
    private List<Book> books;

    // 생성자
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // 도서 추가
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }

    // 제목으로 도서 검색 (단건)
    public Book findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    // 저자로 도서 검색 (다건)
    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    // ISBN으로 도서 검색 (단건)
    public Book findByISBN(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // 이전 메서드 명과의 호환성을 위한 래퍼 메서드
    public Book findBookByTitle(String title) { return findByTitle(title); }
    public List<Book> findBooksByAuthor(String author) { return findByAuthor(author); }
    public Book findBookByISBN(String isbn) { return findByISBN(isbn); }

    // ISBN으로 도서 대출
    public boolean checkOutBook(String isbn) {
        Book book = findByISBN(isbn);
        if (book != null) {
            return book.checkOut(); // Book 내부의 checkOut() 호출
        }
        return false;
    }

    // ISBN으로 도서 반납
    public boolean returnBook(String isbn) {
        Book book = findByISBN(isbn);
        if (book != null && !book.isAvailable()) {
            book.returnBook(); // Book 내부의 returnBook() 호출
            return true;
        }
        return false;
    }

    // 대출 가능한 도서 목록 반환
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    // 전체 도서 목록 반환
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // 모든 도서 갯수 반환
    public int getTotalBooks() {
        return books.size();
    }

    // 대출 가능한 도서 갯수 반환
    public int getAvailableBooksCount() {
        return (int) books.stream()
                .filter(Book::isAvailable)
                .count();
    }

    // 대출 중인 도서 갯수 반환
    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }

    // Getter / Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}