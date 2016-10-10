package au.com.nla.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import au.com.nla.model.Book;
import au.com.nla.model.Person;

/**
 * Class to populate the database after application context startup.
 * 
 * @author Huw
 */
public class DatabaseListener implements ApplicationListener<ContextRefreshedEvent> {

	@PersistenceContext
	private EntityManager entityManager;

	private boolean haveDatabaseUpdatesRun = false;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (!haveDatabaseUpdatesRun) {
			addDatabaseObjects();
			haveDatabaseUpdatesRun = true;
		}
	}

	private void addDatabaseObjects() {

		Person personTom = new Person("Tom", "0411 111 555", "Tom@Home.com.au");
		entityManager.persist(personTom);

		Person personMark = new Person("Mark", "0411 222 555", "Mark@Outside.com.au");
		entityManager.persist(personMark);

		Person personChris = new Person("Chris", "0411 333 555", "Chris@Lost.com.au");
		entityManager.persist(personChris);

		Book book = new Book("Fellowship of the Ring", "J. R. R. Tolkien", "1", personTom);
		entityManager.persist(book);

		book = new Book("Two Towers", "J. R. R. Tolkien", "2", personTom);
		entityManager.persist(book);

		book = new Book("Return of the King", "J. R. R. Tolkien", "3", personTom);
		entityManager.persist(book);

		book = new Book("Philosopher's Stone", "J. K. Rowling", "4", personMark);
		entityManager.persist(book);

		book = new Book("Chamber of Secrets", "J. K. Rowling", "5", personMark);
		entityManager.persist(book);

		book = new Book("Prisoner of Azkaban", "J. K. Rowling", "6");
		entityManager.persist(book);

	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
