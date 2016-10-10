package au.com.nla.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to represent a user/person/book-borrower is linked to the {@Link Book}
 * class through a one-to-many relationship
 * 
 * @author Huw
 *
 */
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id", nullable = false, unique = true)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "email", nullable = false)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	@JsonIgnore
	private List<Book> loanedBooks = new ArrayList<Book>();

	public Person() {

	}

	public Person(String name, String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Book> getLoanedBooks() {
		return loanedBooks;
	}

	public void setLoanedBooks(List<Book> loanedBooks) {
		this.loanedBooks = loanedBooks;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", loanedBooks=" + loanedBooks + "]";
	}

}
