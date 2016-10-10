package au.com.nla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.nla.model.Book;
import au.com.nla.service.BookService;

/**
 * Controller for the Book page of the web app.
 * 
 * @author Huw
 *
 */
@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * Retreves all the books in the database and adds it to the model of the
	 * book.ftl page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String index(@ModelAttribute("model") ModelMap model) {

		List<Book> books = bookService.getBooks();
		model.addAttribute("bookList", books);

		return "books";
	}

}
