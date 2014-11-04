package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * 
 * @author Veerapat Threeravipark 5510547022
 *
 */
public class Book {
	@XmlAttribute
	private int id;
	private String title;
	private String actor;

	/**
	 * Constructor of this class.
	 */
	public Book() {

	}

	/**
	 * 
	 * @param id
	 * @param title
	 * @param actor
	 */
	public Book(int id, String title, String actor) {
		this.id = id;
		this.title = title;
		this.actor = actor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass())
			return false;
		Book book = (Book) other;
		return book.getId() == this.getId();
	}

}
