package main;

import java.util.List;

import model.Book;

import com.williamsportwebdeveloper.BookServices;
import com.williamsportwebdeveloper.BookServicesSoap;

import controller.BookController;

/**
 * Main class. Start the program from here.
 * 
 * @author Veerapat Threeravipark 5510547022
 * 
 */
public class Main {
	
	/**
	 * Show example respond data from SOAP service.
	 * 
	 * @param args
	 *            command-line arguments.
	 */
	public static void main(String[] args) {
		BookController bookController = new BookController();
		String title = "secret";
		String author = "k";
		List<Book> bookListExample1 = bookController.getBooksByTitle(title);
		System.out.println("Case1 (by title): "+title);
		for(Book book : bookListExample1){
			System.out.print(book.getTitle()+",");
		}
		System.out.println();
		System.out.println("Case2 (by author): "+author);
		List<Book> bookListExample2 = bookController.getBooksByAuthor(author);
		for(Book book : bookListExample2){
			System.out.print(book.getTitle()+",");
		}
		System.out.println();
		System.out.println("Case3 (by title and author): "+title+" and "+author);
		List<Book> bookListExample3 = bookController.getBooks(title,author);
		for(Book book : bookListExample3){
			System.out.print(book.getTitle()+",");
		}
	}
}
