package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/viewAuthors", "/addBook", "/editAuthor", "/deleteAuthor", "/pageAuthors","/editBorrower","/addBorrower","/addBranch","/editBranch","/deleteBranch","/deleteBorrower","/deleteBook","/checkoutBook","/editCopies","/addPublisher","/editPublisher","/deletePublisher","/overrideDue","/editBook" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "viewauthors.jsp";
		AdminService service = new AdminService();
		switch (reqUrl) {
		case "/viewAuthors":
			String search = request.getParameter("searchAuthors");
			if (search.equals("null")){
				Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
				List<Author> authors = new ArrayList<Author>();
				try {
					authors = service.viewAuthors(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("authors", authors);
			}
			else{
				Integer pageNo = 1;
				List<Author> authors = new ArrayList<Author>();
				try {
					authors = service.searchAuthors(search,pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("authors", authors);
			}
			break;
		case "/viewBooks":
			search = request.getParameter("searchBooks");
			if (search.equals("null")){
				Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
				List<Book> books = new ArrayList<Book>();
				try {
					books = service.viewBooks(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("books", books);
			}
			else{
				Integer pageNo = 1;
				List<Book> books = new ArrayList<Book>();
				try {
					books = service.searchBooks(search,pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("books", books);
			}
			break;
		case "/deletePublisher":
			System.out.println("deletePublisher");
			Integer publisherID = Integer.parseInt(request.getParameter("publisherId"));
			try {
				service.deletePublisher(publisherID);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			forwardPage = "viewpublishers.jsp";
			break;
		case "/editAuthor":
			Integer authorID = Integer.parseInt(request.getParameter("authorId"));
			Author author = null;
			try {
				author = service.viewAuthorByID(authorID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("author", author);
			forwardPage = "editauthor.jsp";
			break;
		case "/pageAuthors":
			search = request.getParameter("searchAuthors");
			if (search.equals("null")){
				System.out.println("null!!!!");
				Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
				List<Author> authors = new ArrayList<Author>();
				try {
					authors = service.viewAuthors(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("authors", authors);
			}
			else{
				Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
				List<Author> authors = new ArrayList<Author>();
				try {
					authors = service.searchAuthors(search,pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("authors", authors);
			}
			break;
		case "/deleteAuthor":
			System.out.println("deleteauthor");
			authorID = Integer.parseInt(request.getParameter("authorId"));
			try {
				service.deleteAuthor(authorID);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
		case "/deleteBranch":
			System.out.println("deleteBranch");
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			try {
				service.deleteBranch(branchId);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			forwardPage = "viewbranches.jsp";
			break;
		case "/deleteBorrower":
			System.out.println("deleteBorrower");
			Integer cardNo = Integer.parseInt(request.getParameter("borrowerId"));
			try {
				service.deleteBorrower(cardNo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage = "viewborrowers.jsp";
			break;
		case "/deleteBook":
			System.out.println("deleteBook");
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			try {
				service.deleteBook(bookId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage = "viewbooks.jsp?action=delete";
			break;
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "viewauthors.jsp";

		AdminService service = new AdminService();
		switch (reqUrl) {
		case "/addAuthor":
			String authorName = request.getParameter("authorName");
			Author author = new Author();
			author.setAuthorName(authorName);
			List<Book> books = new ArrayList<Book>();
			if(request.getParameterValues("bookId")!=null){
				for (int i=0;i<request.getParameterValues("bookId").length;i++){
					Integer bookId = Integer.parseInt(request.getParameterValues("bookId")[i].split(" ")[0]);
					String title = "";
					for(int j = 1;j<request.getParameterValues("bookId")[i].split(" ").length;j++){
						title+= request.getParameterValues("bookId")[i].split(" ")[j];
					}
//					System.out.println("id:"+authorId);
//					System.out.println("name:"+authorId);
					Book booklist = new Book();
					booklist.setBookId(bookId);
					booklist.setTitle(title);
					books.add(booklist);
				}
			}
			author.setBooks(books);
			try {
				service.addAuthor(author);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage = "viewauthors.jsp?action=add";
			break;
		case "/addBorrower":
			System.out.println("addBorrower");
			String borrowerName = request.getParameter("borrowerName");
			String borrowerAddress = request.getParameter("borrowerAddress");
			String borrowerPhone = request.getParameter("borrowerPhone");
			Borrower borrower = new Borrower();
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);
			try {
				service.addBorrower(borrower);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage = "viewborrowers.jsp";
			break;
		case "/addBranch":
			System.out.println("addBranch");
			String branchName = request.getParameter("branchName");
			String branchAddress = request.getParameter("branchAddress");
			Branch branch = new Branch();
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			try {
				service.addBranch(branch);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage = "viewbranches.jsp";
			break;
		case "/addPublisher":
			System.out.println("addPublisher");
			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");
			Publisher publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setPublisherPhone(publisherPhone);
			publisher.setPublisherAddress(publisherAddress);
			try {
				service.addPublisher(publisher);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			forwardPage = "viewpublishers.jsp";
			break;
		case "/addBook":
			String bookName = request.getParameter("booktitle");
			Book book = new Book();
			List<Author> authors = new ArrayList<Author>();
			List<Genre> genres = new ArrayList<Genre>();
			if(request.getParameterValues("authorId")!=null){
				for (int i=0;i<request.getParameterValues("authorId").length;i++){
					Integer authorId = Integer.parseInt(request.getParameterValues("authorId")[i].split(" ")[0]);
					String authorName1 = "";
					for(int j = 1;j<request.getParameterValues("authorId")[i].split(" ").length;j++){
						authorName1+= request.getParameterValues("authorId")[i].split(" ")[j];
					}
//					System.out.println("id:"+authorId);
//					System.out.println("name:"+authorId);
					Author authornew = new Author();
					authornew.setAuthorID(authorId);
					authornew.setAuthorName(authorName1);
					authors.add(authornew);
				}
			}
			if(request.getParameterValues("genreId")!=null){
				for (int i=0;i<request.getParameterValues("genreId").length;i++){
	
					Integer genreId = Integer.parseInt(request.getParameterValues("genreId")[i].split(" ")[0]);
					String genreName = "";
					for(int j = 1;j<request.getParameterValues("genreId")[i].split(" ").length;j++){
						genreName+= request.getParameterValues("genreId")[i].split(" ")[j];
					}
					System.out.println("id:"+genreId);
					System.out.println("name:"+genreId);
					Genre genre = new Genre();
					genre.setGenreId(genreId);
					genre.setGenreName(genreName);
					genres.add(genre);
				}
			}
//			System.out.println("authorsize"+authors.size());
//			System.out.println("genresize"+genres.size());
			book.setAuthors(authors);
			book.setGenres(genres);
			book.setTitle(bookName);
			book.setPid(Character.getNumericValue(request.getParameter("publisherId").charAt(0)));
			try {
				service.addBook(book);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forwardPage = "viewbooks.jsp?action=add";
			break;
		case "/editAuthor":
			authorName = request.getParameter("authorName");
			Integer authorID = Integer.parseInt(request.getParameter("authorId"));
			author = new Author();
			author.setAuthorName(authorName);
			author.setAuthorID(authorID);

			books = new ArrayList<Book>();
			if(request.getParameterValues("bookId")!=null){
				for (int i=0;i<request.getParameterValues("bookId").length;i++){

					Integer bookId = Integer.parseInt(request.getParameterValues("bookId")[i].split(" ")[0]);
					String title1="";
					for(int j = 1;j<request.getParameterValues("bookId")[i].split(" ").length;j++){
						title1+= request.getParameterValues("bookId")[i].split(" ")[j];
					}
					System.out.println("id:"+bookId);
					System.out.println("name:"+bookId);
					Book newbook = new Book();
					newbook.setBookId(bookId);
					newbook.setTitle(title1);
					books.add(newbook);
				}
			}
			author.setBooks(books);
			try {
				service.editAuthor(author);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
		case "/editBook":
			bookName = request.getParameter("title");
			book = new Book();
			authors = new ArrayList<Author>();
			genres = new ArrayList<Genre>();
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			if(request.getParameterValues("authorId")!=null){
				for (int i=0;i<request.getParameterValues("authorId").length;i++){
	
					Integer authorId = Integer.parseInt(request.getParameterValues("authorId")[i].split(" ")[0]);
					String authorName1 = "";
					for(int j = 1;j<request.getParameterValues("authorId")[i].split(" ").length;j++){
						authorName1+= request.getParameterValues("authorId")[i].split(" ")[j];
					}
					System.out.println("id:"+authorId);
					System.out.println("name:"+authorId);
					Author authornew = new Author();
					authornew.setAuthorID(authorId);
					authornew.setAuthorName(authorName1);
					authors.add(authornew);
				}
			}
			if(request.getParameterValues("genreId")!=null){
				for (int i=0;i<request.getParameterValues("genreId").length;i++){
	
					Integer genreId = Integer.parseInt(request.getParameterValues("genreId")[i].split(" ")[0]);
					String genreName = "";
					for(int j = 1;j<request.getParameterValues("genreId")[i].split(" ").length;j++){
						genreName+= request.getParameterValues("genreId")[i].split(" ")[j];
					}
					System.out.println("id:"+genreId);
					System.out.println("name:"+genreId);
					Genre genre = new Genre();
					genre.setGenreId(genreId);
					genre.setGenreName(genreName);
					genres.add(genre);
				}
			}
//			System.out.println("authorsize"+authors.size());
//			System.out.println("genresize"+genres.size());
			book.setBookId(bookId);
			book.setAuthors(authors);
			book.setGenres(genres);
			book.setTitle(bookName);
			System.out.println(bookName);
			book.setPid(Character.getNumericValue(request.getParameter("publisherId").charAt(0)));
			try {
				service.editBook(book);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forwardPage = "viewbooks.jsp?action=edit";
			break;
		case "/deleteAuthor":
			System.out.println("deleteauthor");
			authorID = Integer.parseInt(request.getParameter("authorId"));
			try {
				service.deleteAuthor(authorID);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;

		case "/editBorrower":
			System.out.println("editBorrower");
			borrowerName = request.getParameter("borrowerName");
			Integer borrowerID = Integer.parseInt(request.getParameter("borrowerId"));
			borrowerAddress =request.getParameter("borrowerAddress");
			String phone =request.getParameter("borrowerPhone");
			borrower = new Borrower();
			borrower.setCardNo(borrowerID);
			borrower.setPhone(phone);
			borrower.setAddress(borrowerAddress);
			borrower.setName(borrowerName);
			//System.out.println("editborrower");
			try {
				service.editBorrower(borrower);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			forwardPage = "viewborrowers.jsp";
			break;
		case "/editPublisher":
			System.out.println("editPublisher");
			publisherName = request.getParameter("publisherName");
			Integer publisherID = Integer.parseInt(request.getParameter("publisherId"));
			publisherAddress =request.getParameter("publisherAddress");
			publisherPhone =request.getParameter("publisherPhone");
			publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setPublisherId(publisherID);
			publisher.setPublisherPhone(publisherPhone);
			publisher.setPublisherAddress(publisherAddress);
			//System.out.println("editpublisher");
			try {
				service.editPublisher(publisher);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			forwardPage = "viewpublishers.jsp";
			break;
		case "/editBranch":
			System.out.println("editBranch");
			branchName = request.getParameter("branchName");
			Integer branchID = Integer.parseInt(request.getParameter("branchId"));
			branchAddress =request.getParameter("branchAddress");
			branch = new Branch();
			branch.setBranchAddress(branchAddress);
			branch.setBranchName(branchName);
			branch.setBranchId(branchID);
			//System.out.println("editbranch");
			try {
				service.editBranch(branch);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			forwardPage = "librarianmain.jsp"+"?branchId="+branchID;
			break;
		case "/checkoutBook":
			System.out.println("checkoutBook");
			bookId = Integer.parseInt(request.getParameter("bookId"));
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			forwardPage = "viewbranches.jsp";
			break;
		case "/editCopies":
			System.out.println("editCopies");
			Integer noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));
			bookId = Integer.parseInt(request.getParameter("bookId"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(bookId);
			bookCopies.setNoOfCopies(noOfCopies);
			bookCopies.setBranchId(branchId);
			AdminService adminService = new AdminService();
			try {
				adminService.updateCopies(bookCopies);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forwardPage = "librarianmain.jsp?branchId="+branchId;
			System.out.println(forwardPage);
			break;
		case "/overrideDue":
			System.out.println("overrideDue");
			String date = request.getParameter("datepicker");
			branchId = Integer.parseInt(request.getParameter("branchId"));
			bookId = Integer.parseInt(request.getParameter("bookId"));
			cardNo = Integer.parseInt(request.getParameter("cardNo"));
			String month = date.substring(0, 2);
			String perdate = date.substring(3, 5);
			String year = date.substring(6, 10);
			String duedate = year+"-"+month+"-"+perdate+" 00:00:00";
			System.out.println(duedate);					
			adminService = new AdminService();
			try {
				adminService.updateDueDate(duedate,cardNo,branchId,bookId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forwardPage = "overrideduedate.jsp";
			break;

		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}

}
