package au.com.nla.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import au.com.nla.model.Book;

@Component
public class BookDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Retrieve all the books loaned out to a person represented by their
	 * person_id;
	 * 
	 * @param personId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getBooksLoanedToPerson(int personId) {

		Query query = entityManager.createQuery("from Book where person_id = ?");
		query.setParameter(1, personId);
		List<Book> books = query.getResultList();

		return books;

	}

	/**
	 * Retrieve all books from the database.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {

		Query query = entityManager.createQuery("from Book");
		List<Book> books = query.getResultList();

		return books;
	}
}
