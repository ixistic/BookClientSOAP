package ku.book.model;

/**
 * Book data model.
 * Contain id, title and . 
 * @author Veerapat Threeravipark 5510547022
 *
 */
public class Book {
	private int id;
	private String title;
	private String author;

	/**
	 * Constructor of this class.
	 */
	public Book() {

	}

	/**
	 * Constructor of this class.
	 * That initial id, title and author.
	 * @param id identifier of book
	 * @param title title of book
	 * @param author author of book 
	 */
	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass())
			return false;
		Book book = (Book) other;
		return book.getId() == this.getId();
	}

}
