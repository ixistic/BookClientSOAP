package ku.book.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.WebServiceException;

import ku.book.controller.BookController;
import ku.book.model.Book;


/**
 * Initiate UI component show the user interface.
 * 
 * @author Veerapat Threeravipark 5510547022
 * 
 */
public class BookClientUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private BookController bookController;

	private JPanel panel;
	private JTextField title_input;
	private JTextField author_input;
	private JLabel header_label;
	private JLabel credit_label;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton submit_btn;
	private JLabel process_label;
	private JProgressBar progressBar;
	private JLabel author_label;
	private JLabel title_label;
	private DefaultTableModel tableModel;

	private BookWorker bw;
	private List<Book> bookList;
	private Timer timer;
	private Timer timer2;

	/**
	 * Constructor of this class. Naming Jframe. Init user interface.
	 */
	public BookClientUI() {
		super("Book Search Engine");
		bookList = new ArrayList<Book>();
		timer = null;
		initComponents();
	}

	/**
	 * Init user interface.
	 */
	private void initComponents() {
		setSize(400, 600);
		setLocation(50, 50);
		setResizable(false);
		panel = new JPanel();
		panel.setLayout(null);

		title_input = new JTextField();
		title_input.setBounds(93, 90, 292, 28);
		panel.add(title_input);
		title_input.setColumns(10);

		title_label = new JLabel("Title");
		title_label.setBounds(20, 96, 61, 16);
		panel.add(title_label);

		author_input = new JTextField();
		author_input.setColumns(10);
		author_input.setBounds(93, 130, 292, 28);
		panel.add(author_input);

		author_label = new JLabel("Author");
		author_label.setBounds(20, 136, 61, 16);
		panel.add(author_label);

		header_label = new JLabel("Book Search Engine");
		header_label.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		header_label.setBounds(20, 6, 335, 72);
		panel.add(header_label);

		credit_label = new JLabel("By Veerapat Threeravipark");
		credit_label.setBounds(209, 65, 165, 16);
		panel.add(credit_label);

		progressBar = new JProgressBar();
		progressBar.setBounds(93, 170, 165, 29);
		panel.add(progressBar);

		process_label = new JLabel("Process :");
		process_label.setBounds(20, 174, 61, 16);
		panel.add(process_label);

		submit_btn = new JButton("Search");
		submit_btn.setBounds(268, 170, 117, 29);
		submit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionButton();
			}
		});

		getRootPane().setDefaultButton(submit_btn);
		panel.add(submit_btn);

		String col[] = { "Title", "Acthor" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 218, 360, 336);
		panel.add(scrollPane);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// timeout check 5 seconds
		timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				if (bw != null)
					bw.cancel(true);
				showRetryExit("Please check your network connections");
			}
		});
		
		// timeout check 3 seconds
		timer2 = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				progressBar.setValue(0);
			}
		});
	}

	/**
	 * Action of button. That invokes the web service.
	 */
	public void actionButton() {
			setProgressBar(0);
			clearOldData();
			// Search button
			if (submit_btn.getText().equals("Search")) {
				setProgressBar(10);
				submit_btn.setText("Cancel");
				bw = new BookWorker();
				bw.execute();
			}
			// Cancel button
			else {
				bw.cancel(true);
				submit_btn.setText("Search");
			}
//		}
	}

	/**
	 * Fetch data from title or author.
	 */
	public List<Book> fetchData() {
		String title = title_input.getText();
		String author = author_input.getText();
		setProgressBar(30);
		List<Book> books = null;
		try {
			books = bookController.getBooks(title, author);
		} catch (WebServiceException wse) {
			showRetryExit("Please check your network connections");
		}
		return books;
	}

	/**
	 * Update user interface from result.
	 */
	public void updateData() {
		if (bookList != null && !bookList.isEmpty()) {
			setProgressBar(75);
			for (Book book : bookList) {
				String row[] = { book.getTitle(), book.getAuthor() };
				tableModel.addRow(row);
			}
			setProgressBar(100);
			submit_btn.setText("Search");
		} else {
			setProgressBar(100);
			showMessage("No result");
		}
	}

	/**
	 * Clear old data.
	 */
	private void clearOldData() {
		if (tableModel.getRowCount() > 0)
			tableModel.setNumRows(0);
		bookList = new ArrayList<Book>();
	}

	/**
	 * Set value of progress bar.
	 * 
	 * @param value
	 *            percent of progress bar
	 */
	public void setProgressBar(int value) {
		timer2.stop();
		progressBar.setValue(value);
		if(value == 100){
			timer2.start();
		}
		
		
	}

	/**
	 * Notify message by JOptionPane.
	 * 
	 * @param message
	 *            text that want to notify
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
		setProgressBar(0);
		clearOldData();
		submit_btn.setText("Search");
	}

	/**
	 * Notify message by JOptionPane. Provide 2 button Retry and Exit
	 * 
	 * @param message
	 *            text that want to notify
	 * @return list of book
	 */
	public void showRetryExit(String message) {
		String[] options = { "Exit", "Retry" };
		int choosen = JOptionPane.showOptionDialog(null, message, "Error",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null,
				options, options[1]);
		switch (choosen) {
		case 0:
			// Exit selected
			System.exit(0);
			break;
		case 1:
			actionButton();
			break;

		case JOptionPane.CLOSED_OPTION:
			System.exit(0);
			break;
		}

	}

	/**
	 * To perform lengthy GUI-interaction tasks in a background thread.
	 * 
	 * @author Veerapat Threeravipark 5510547022
	 * 
	 */
	class BookWorker extends SwingWorker<List<Book>, String> {

		@Override
		protected List<Book> doInBackground() throws Exception {
			if (bookController == null) {
				// check timeout 5 seconds
				
				timer.start();
				try {
					bookController = new BookController();
				} catch (WebServiceException wse) {
					timer.stop();
					showRetryExit("Please check your network connections");
				}
				timer.stop();

			}
			if(bookController != null) {
				bookList = fetchData();
			}
			return bookList;
		}

		@Override
		protected void done() {
			if (!bw.isCancelled()) {
				updateData();
			}
		}

	}
}
