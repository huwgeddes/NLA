package au.com.nla.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import au.com.nla.model.Person;

@Component
public class PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Retrieve all the People from the database.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Person> getPeople() {
		return entityManager.createQuery("from Person").getResultList();

	}

}
