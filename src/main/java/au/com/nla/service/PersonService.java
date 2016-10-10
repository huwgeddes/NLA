package au.com.nla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import au.com.nla.dao.PersonDao;
import au.com.nla.model.Person;

@Service("personService")
public class PersonService {

	@Autowired @Qualifier("personDao")
	private PersonDao personDao;

	public List<Person> getPeople() {
		return personDao.getPeople();
	}

}
