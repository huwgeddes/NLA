package au.com.nla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.nla.model.Book;
import au.com.nla.model.Person;
import au.com.nla.service.BookService;
import au.com.nla.service.PersonService;

/**
 * Controller for the main-page or Person page of the web-app.
 * 
 * @author Huw
 *
 */
@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private BookService bookService;

	/**
	 * Retrieves all people from the database and displays the main page of the
	 * web app.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute("model") ModelMap model) {

		List<Person> people = personService.getPeople();
		model.addAttribute("userList", people);

		return "index";
	}

	/**
	 * Ajax get request to retreive all the books loaned out to specific person.
	 * Returns a List of {@link Book}s loaned out to a peron represented by
	 * their @param id.
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkedoutbooks", method = RequestMethod.GET)
	public List<Book> getCheckedOutBook(@RequestParam("id") int id) {

		List<Book> loanedBooks = bookService.getBooksLoanedToPerson(id);
		return loanedBooks;

	}

}
