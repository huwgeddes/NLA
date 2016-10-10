package au.com.nla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to represent a Book. Is linked to the {@link Person} class through a
 * many-to-one relationship.
 * 
 * @author Huw
 *
 */
@Entity
@Table(name = "book", uniqueConstraints = { @UniqueConstraint(columnNames = "isbn") })
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false, unique = true)
	private int id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "isbn", nullable = false)
	private String ISBN;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	@JsonIgnore
	private Person person;

	public Book() {

	}
	
	public Book(String title, String author, String ISBN) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
	}

	public Book(String title, String author, String ISBN, Person person) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.person = person;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
