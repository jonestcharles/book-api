package cscie57a.assgn11.books;

import cscie57a.assgn11.books.exception.ResourceNotFoundException;
import cscie57a.assgn11.books.model.Book;
import cscie57a.assgn11.books.repository.BookRepository;
import cscie57a.assgn11.books.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BooksApplicationTests {
	private static final String title = "A Good Book";
	private static final String publisher = "Penguin";
	private static final String author = "John Doe";

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Captor
	private ArgumentCaptor<Long> captorBookId;

	private List<Book> books;

	@Before
	public void init() {
		books = new ArrayList<>();

		Book book = new Book();
		book.setId(1L);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		books.add(book);
	}

	@Test
	public void getBooksTest() throws ResourceNotFoundException {

		// mock
		when(bookRepository.findAll()).thenReturn(books);

		// run service
		List<Book> books = bookServiceImpl.findAllBooks();

		// assert
		assertEquals("Verify correct book result count", 1, books.size());
	}

	@Test
	public void getBookByIdTest() throws ResourceNotFoundException {

		Long testId = 1L;

		// mock
		when(bookRepository.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(books.get(0)));

		// run service
		Book book = bookServiceImpl.findBookById(testId);

		// verify mock
		verify(bookRepository).findById(captorBookId.capture());

		// assert
		assertEquals("Verify correct book was found", book.getId(), captorBookId.getValue());
	}
}
