package cscie57a.assgn11.books.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cscie57a.assgn11.books.exception.BookDeletionException;
import cscie57a.assgn11.books.exception.ResourceNotFoundException;
import cscie57a.assgn11.books.model.Book;
import cscie57a.assgn11.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            logger.error("cannot delete Book because ID does not exist");
            throw new BookDeletionException("A book does not exist with id=" + bookId);
        }
    }

    @Override
    public Book findBookById(Long bookId) throws ResourceNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get();
        }

        throw new ResourceNotFoundException("A book does not exists with id=" + bookId);
    }

    @Override
    public Book updateBook(Book book, Long bookId) {
        Optional<Book> foundBook = bookRepository.findById(bookId);
        if (foundBook.isPresent()) {
            logger.info("Updating Book with: {}", book);
            foundBook.get().setTitle(book.getTitle());
            foundBook.get().setAuthor(book.getAuthor());
            foundBook.get().setPublisher(book.getPublisher());
            return saveBook(foundBook.get());
        } else {
            return saveBook(book);
        }
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
