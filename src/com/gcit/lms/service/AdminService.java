package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class AdminService {
	public void addBook(Book book) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO bdao = new BookDAO(conn);
			bdao.addBook(book);
			// author.setAuthorID(authorID);
			//List<Author>authorList =book.getAuthors();
			//bdao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		
	}
	public void deleteBook(Integer bookId) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(bookId);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Book> viewBooks(int pageNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	
	public void editBook(Book book) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO bdao = new BookDAO(conn);
			bdao.updateBook(book);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void addAuthor(Author author) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthor(author);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			PublisherDAO adao = new PublisherDAO(conn);
			adao.addPublisher(publisher);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}	}
	public void deleteAuthor(Integer authorId) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(authorId);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void deleteBranch(Integer branchId) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BranchDAO adao = new BranchDAO(conn);
			adao.deleteBranch(branchId);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Author> viewAuthors(int pageNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthors(pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public List<Genre> viewGenres(Integer pageNo) throws SQLException{
		Connection conn = ConnectionUtil.getConnection();
		try {
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenres(pageNo);
		} finally {
			conn.close();
		}
	}
	public List<Author> searchAuthors(String authorName, int pageNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.searchAuthors(authorName, pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public List<Book> searchBooks(String title, Integer pageNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO adao = new BookDAO(conn);
			return adao.searchBooks(title, pageNo);
		} finally {
			conn.close();
		}
	}
	public List<Branch> viewBranches(int pageNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BranchDAO bdao = new BranchDAO(conn);

			return bdao.readAllBranches(pageNo);
		} finally {
			conn.close();
		}
	}
	public List<BookCopies> viewBranchById(int branchId) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BranchDAO bdao = new BranchDAO(conn);

			return bdao.readBranchById(branchId);
		} finally {
			conn.close();
		}
	}
	public List<Publisher> viewPublishers(int pageNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			PublisherDAO pdao = new PublisherDAO(conn);

			return pdao.readAllPublishers();
		} finally {
			conn.close();
		}
	}
	public Author viewAuthorByID(Integer authorID) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);

			return adao.readByID(authorID);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public Borrower viewBorrowerByID(Integer cardNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BorrowerDAO adao = new BorrowerDAO(conn);

			return adao.readBorrowerByID(cardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public Book viewBookByID(Integer bookId) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO adao = new BookDAO(conn);

			return adao.readBookByID(bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public Branch viewBranchByID(Integer branchId) throws SQLException{
		Connection conn = ConnectionUtil.getConnection();
		try {
			BranchDAO adao = new BranchDAO(conn);

			return adao.readBorrowerByID(branchId);
		} finally {
			conn.close();
		}
	}
	public void editAuthor(Author author) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void editBook(Integer bookId,String title) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO adao = new BookDAO(conn);
			adao.updateBook(bookId,title);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void editBorrower(Borrower borrower) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BorrowerDAO adao = new BorrowerDAO(conn);
			adao.updateBorrower(borrower);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public Integer getAuthorCount() throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorCount();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public Integer getSearchCount(String authorName) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getSearchCount(authorName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public Integer getBookCount() throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookDAO bdao = new BookDAO(conn);
			return bdao.getBookCount();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();

		}
		return null;
	}
	
	public List<Borrower> viewBorrowers(Integer pageNo) throws SQLException{
		Connection conn = ConnectionUtil.getConnection();
		try {
			BorrowerDAO adao = new BorrowerDAO(conn);
			return adao.readAllBorrowers(pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BorrowerDAO adao = new BorrowerDAO(conn);
			adao.addBorrower(borrower);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void addBranch(Branch branch) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BranchDAO adao = new BranchDAO(conn);
			adao.addBranch(branch);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		
	}
	public void editBranch(Branch branch) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BranchDAO adao = new BranchDAO(conn);
			adao.updateBranch(branch);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		
	}
	public void deleteBorrower(Integer cardNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BorrowerDAO adao = new BorrowerDAO(conn);
			adao.deleteBorrower(cardNo);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}	
	}
	public void updateCopies(BookCopies bookCopies)throws SQLException{
		Connection conn = ConnectionUtil.getConnection();
		try {
			System.out.println("addCOpies");
			BookCopiesDAO adao = new BookCopiesDAO(conn);
			adao.addBookCopies(bookCopies);
		} catch (SQLException e) {
			System.out.println("update!!!!!!!!");
			e.printStackTrace();
			BookCopiesDAO adao = new BookCopiesDAO(conn);
			adao.updateBookCopies(bookCopies);
		} finally {
			conn.commit();
			conn.close();
		}
	}
	public void editPublisher(Publisher publisher) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			PublisherDAO adao = new PublisherDAO(conn);
			adao.updatePublisher(publisher);
			// author.setAuthorID(authorID);
			// adao.addBookAuthor(author);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void deletePublisher(Integer publisherID) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			PublisherDAO adao = new PublisherDAO(conn);
			adao.deletePublisher(publisherID);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}	
	}
	public void updateDueDate(String duedate, Integer cardNo, Integer branchId,
			Integer bookId) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			System.out.println("addCOpies");
			BookLoansDAO adao = new BookLoansDAO(conn);
			adao.overrideDue(duedate,cardNo, branchId,bookId);
			conn.commit();
		} finally {
			
			conn.close();
		}
	}


}
