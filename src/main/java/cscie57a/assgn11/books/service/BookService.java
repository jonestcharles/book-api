package cscie57a.assgn11.books.service;

import cscie57a.assgn11.books.exception.ResourceNotFoundException;
import cscie57a.assgn11.books.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book saveBook(Book book);

    void deleteBook(Long bookId);

    Book findBookById(Long bookId) throws ResourceNotFoundException;
}
