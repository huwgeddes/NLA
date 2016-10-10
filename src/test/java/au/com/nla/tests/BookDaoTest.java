package au.com.nla.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.com.nla.dao.BookDao;
import au.com.nla.model.Book;
import au.com.nla.model.Person;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "file:src/test/java/au/com/nla/config/text-context.xml",
		"file:WebContent/WEB-INF/classes/bean-context.xml", "file:WebContent/WEB-INF/classes/nla-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BookDao bookDao;

	@Before
	public void init() {
		String deleteBookQuery = "delete from Book";
		entityManager.createNativeQuery(deleteBookQuery).executeUpdate();

		String deletePersonQuery = "delete from Person";
		entityManager.createNativeQuery(deletePersonQuery).executeUpdate();

	}

	/**
	 * Test that bookDao correctly retrieving books loaned to a person.
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testBooksLoanedToAPerson() {

		Person personTom = new Person("Tom", "0411 111 555", "Tom@Home.com.au");
		entityManager.persist(personTom);

		Person personMark = new Person("Mark", "0411 222 555", "Mark@Outside.com.au");
		entityManager.persist(personMark);

		Book book = new Book("Fellowship of the Ring", "J. R. R. Tolkien", "1", personTom);
		entityManager.persist(book);

		book = new Book("Two Towers", "J. R. R. Tolkien", "2", personTom);
		entityManager.persist(book);

		book = new Book("Return of the King", "J. R. R. Tolkien", "3", personMark);
		entityManager.persist(book);

		List<Book> books = bookDao.getBooksLoanedToPerson(personTom.getId());
		assertEquals("Check filtering book results by the loaned to person_id", 2, books.size());

	}

	/**
	 * Test that the bookDao correctly retrieves all books from the database no
	 * matter who they are loaned to.
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testGetAllBooks() {

		Person personTom = new Person("Tom", "0411 111 555", "Tom@Home.com.au");
		entityManager.persist(personTom);

		Person personMark = new Person("Mark", "0411 222 555", "Mark@Outside.com.au");
		entityManager.persist(personMark);

		Book book = new Book("Fellowship of the Ring", "J. R. R. Tolkien", "1", personTom);
		entityManager.persist(book);

		book = new Book("Two Towers", "J. R. R. Tolkien", "2");
		entityManager.persist(book);

		book = new Book("Return of the King", "J. R. R. Tolkien", "3", personMark);
		entityManager.persist(book);

		List<Book> books = bookDao.getBooks();
		assertEquals("Check retreiving all books from the database", 3, books.size());
	}

}
