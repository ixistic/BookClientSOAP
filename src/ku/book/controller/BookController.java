package ku.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.ws.WebServiceException;

import ku.book.model.Book;
import ku.book.view.BookClientUI;



import com.williamsportwebdeveloper.BookServices;
import com.williamsportwebdeveloper.BookServicesSoap;

/**
 * Controller class for client service to query book database. 
 * Use unmarshal to convert String to object(BooK).
 * @author Veerapat Threeravipark 5510547022
 *
 */
public class BookController {
	
	private BookServicesSoap bookServicesSoap;
	private List<Book> bookList;
	
	/**
	 * Constructor of this class.
	 * @throws WebServiceException when network connection error
	 */
	public BookController() throws WebServiceException{
		try{
			BookServices bs = new BookServices();
			bookServicesSoap = bs.getBookServicesSoap();
		}catch(WebServiceException wse){
			throw wse;
		}
		bookList = new ArrayList<Book>();
	}
	
	/**
	 * Get books in database by book's title.
	 * @param title title of book
	 * @return list of book that query by title 
	 */
	public List<Book> getBooksByTitle( String title ){
		return getBooks(title,"");
	}
	
	/**
	 * Get books in database by book's author.
	 * @param author author of book
	 * @return list of book that query by author 
	 */
	public List<Book> getBooksByAuthor( String author ){
		return getBooks("", author);
	}
	
	/**
	 * Get books in database by book's title and book's author.
	 * @param title title of book
	 * @param author author of book
	 * @return list of book that query by title and author 
	 * @throws WebServiceException when network connection error
	 */
	public List<Book> getBooks( String title, String author ) throws WebServiceException{
		bookList = new ArrayList<Book>();
		List<Book> bookListByTitle = new ArrayList<Book>();
		List<Book> bookListByAuthor = new ArrayList<Book>();
		if(!title.equals("")){
			String booksByTitle;
			try{
				booksByTitle = bookServicesSoap.getBooksByTitle( title );
			}catch(WebServiceException wse){
				throw wse;
			}
			bookListByTitle = unmarshall( booksByTitle );
		}
		if(!author.equals("")){
			String booksByAuthor;
			try{
				booksByAuthor = bookServicesSoap.getBooksByAuthor( author );
			}catch(WebServiceException wse){
				throw wse;
			}
			bookListByAuthor = unmarshall( booksByAuthor );
		}
		if(bookListByTitle.isEmpty() && !bookListByAuthor.isEmpty()){
			bookList = bookListByAuthor;
		}
		else if(!bookListByTitle.isEmpty() && bookListByAuthor.isEmpty()){
			bookList = bookListByTitle;
		}
		else if(!bookListByTitle.isEmpty() && !bookListByAuthor.isEmpty()){
			bookListByAuthor.retainAll(bookListByTitle);
			bookList = bookListByAuthor;
		}
		return bookList;
	}
	
	/**
	 * Unmarshal to convert String to object(Book).
	 * @param result respond from SOAP web service
	 * @return list of book
	 */
	public List<Book> unmarshall( String result ){
		List<Book> books = new ArrayList<Book>();
		if(result != null){
			String[] cleanData = formatData(result);
			for(String rawBook : cleanData){
				String[] detailBook = rawBook.split(",");
				int id = Integer.parseInt(detailBook[0]);
				String title = detailBook[1];
				String author = detailBook[2];
				Book book = new Book(id,title,author);
				books.add(book);
			}
		}
		return books;
	}
	
	/**
	 * Format raw response data from server to clean data
	 * @param result raw respond data
	 * @return String array of books
	 */
	public String[] formatData(String result){
		if(!result.isEmpty() && !result.equals("[]") && result.length() >= 2){
			result = result.substring(2, result.length()-2);
			result = result.replaceAll("\"", "");
			String[] bookList = result.split(Pattern.quote("],["));
			return bookList;
		}
		return null;
	}
	
}
