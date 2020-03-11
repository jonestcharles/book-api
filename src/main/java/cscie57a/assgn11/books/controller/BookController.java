package cscie57a.assgn11.books.controller;

import cscie57a.assgn11.books.model.Book;
import cscie57a.assgn11.books.service.BookService;
import cscie57a.assgn11.books.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API Interface
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    /**
     * GET all books
     * @return ResponseEntity of books as JSON
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBooks() {
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * GET book by id
     * Using @PathVariable
     * @param bookId
     * @return ResponseEntity of book as JSON
     */
    @GetMapping(value = "/{bookId}")
    public ResponseEntity<?> getBookById (@PathVariable("bookId") Long bookId) {
        return new ResponseEntity<>(bookService.findBookById(bookId), HttpStatus.OK);
    }

    /**
     * POST method to create a book
     * Using @RequestBody
     * @param book
     * @return ResponseEntity upon successful book creation
     */
    @PostMapping
    public ResponseEntity<?> addNewBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    /**
     * PUT method for updating a Book
     * Using @RequestBody
     * Using @PathVariable
     * @param newBook Request Body consisting of the new book
     * @param bookId the ID of the book to update
     * @return ResponseEntity with new/updated Book and status
     */
    @PutMapping(value = "/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody Book newBook, @PathVariable("bookId") Long bookId) {
        return new ResponseEntity<>(bookService.updateBook(newBook, bookId), HttpStatus.OK);
    }

    /**
     * DELETE method for Book
     * @param bookId
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
