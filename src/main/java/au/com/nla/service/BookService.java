package au.com.nla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import au.com.nla.dao.BookDao;
import au.com.nla.model.Book;

@Service("bookService")
public class BookService {

	@Autowired @Qualifier("bookDao")
	private BookDao bookDao;

	public List<Book> getBooksLoanedToPerson(int personId) {
		return bookDao.getBooksLoanedToPerson(personId);
	}

	public List<Book> getBooks() {
		return bookDao.getBooks();
	}
	
}
