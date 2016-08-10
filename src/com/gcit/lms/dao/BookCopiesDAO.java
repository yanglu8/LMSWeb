package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;

public class BookCopiesDAO extends BaseDAO {
	
	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addCopies(BookCopies bookcopies) throws SQLException{
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)", new Object[] {bookcopies.getBookId(), bookcopies.getBranchId(),bookcopies.getNoOfCopies()});
	}
	public List<BookCopies> readAllCopies() throws SQLException{
		return read("select tbl_book_copies.bookId, tbl_book.title, tbl_library_branch.branchName, tbl_book_copies.noOfCopies,tbl_book_copies.branchId from tbl_book_copies,tbl_book,tbl_library_branch where tbl_book_copies.bookId= tbl_book.bookId and tbl_book_copies.branchId= tbl_library_branch.branchId", null);
	}
	public List<BookCopies> readBookOnlyCardNo(Integer cardNo) throws SQLException{
		return read("select tbl_book_copies.bookId, title, tbl_book_copies.branchId, tbl_library_branch.branchName, tbl_book_copies.noOfCopies  from tbl_book,tbl_book_copies,tbl_library_branch where tbl_book_copies.bookId not in (select tbl_book_loans.bookId from tbl_book_loans where tbl_book_loans.cardNo= ?) and tbl_book.bookId= tbl_book_copies.bookId and tbl_book_copies.branchId= tbl_library_branch.branchId", new Object[] {cardNo});
	}
	public void editCopies(BookCopies bookCopies){}
	public void deleteCopies(){}
	@Override
	public List<BookCopies> extractData(ResultSet rs) {
		List<BookCopies> bookCopies = new ArrayList<BookCopies>();
		try {
			while(rs.next()){
				BookCopies b = new BookCopies();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("title"));
				b.setBranchId(rs.getInt("branchId"));
				b.setBranchName(rs.getString("branchName"));
				b.setNoOfCopies(rs.getInt("noOfCopies"));
				bookCopies.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopies;
	}
	
	
	
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) {
		List<BookCopies> bookCopies = new ArrayList<BookCopies>();
		try {
			while(rs.next()){
				BookCopies b = new BookCopies();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("title"));
				b.setBranchId(rs.getInt("branchId"));
				//b.setBranchName(rs.getString("branchName"));
				b.setNoOfCopies(rs.getInt("noOfCopies"));
				bookCopies.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopies;
	}
	
	
	
	
	
	public void checkoutBook(Integer cardNo, Integer bookID, Integer branchId) throws SQLException {
		System.out.println(cardNo+" "+bookID+" "+branchId);
		save("INSERT INTO tbl_book_loans VALUES (?,?,?,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 7 DAY ),NULL)", new Object[] {bookID,branchId,cardNo});
	}


	public void updateBookCopies(BookCopies bookCopies) throws SQLException {
		save("update tbl_book_copies set noOfCopies=? where bookId = ? and branchId =?",
				new Object[] { bookCopies.getNoOfCopies(), bookCopies.getBookId(),bookCopies.getBranchId() });		
	}
	public void addBookCopies(BookCopies bookCopies) throws SQLException {
		System.out.println(bookCopies.getBookId()+" "+bookCopies.getBranchId()+" "+bookCopies.getNoOfCopies());
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?, ?,?)",
				new Object[] { bookCopies.getBookId(),bookCopies.getBranchId(), bookCopies.getNoOfCopies()});		
	}
}
