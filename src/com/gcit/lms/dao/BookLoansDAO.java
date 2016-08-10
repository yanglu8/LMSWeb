package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

public class BookLoansDAO extends BaseDAO {

	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addBookLoan(Book book,Borrower borrower, Branch branch) throws SQLException{
		save("INSERT INTO tbl_book_loans VALUES (?,?,?,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 7 DAY ),NULL)", new Object[] {book.getTitle(), book.getPid()});
	}
	public void checkoutBook(Integer cardNo, Integer bookID, Integer branchId) throws SQLException {
		System.out.println(cardNo+" "+bookID+" "+branchId);
		save("INSERT INTO tbl_book_loans VALUES (?,?,?,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 7 DAY ),NULL)", new Object[] {bookID,branchId,cardNo});
	}
	public List<BookLoans> readLoansOnlyCardNo(Integer cardNo) throws SQLException {
		return read("select tbl_book_loans.cardNo,tbl_book.bookId, tbl_book.title, tbl_book_loans.dueDate, tbl_library_branch.branchName,tbl_library_branch.branchId from tbl_book, tbl_book_loans,tbl_library_branch where tbl_book_loans.dateIn is null and tbl_book_loans.cardNo=? and tbl_book.bookId= tbl_book_loans.bookId and tbl_book_loans.branchId= tbl_library_branch.branchId",new Object[] {cardNo});
	}

	@Override
	public List<BookLoans> extractData(ResultSet rs) {
		List<BookLoans> bookLoans = new ArrayList<BookLoans>();
		try {
			while(rs.next()){
				BookLoans b = new BookLoans();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				b.setDueDate(rs.getString("dueDate"));
				b.setBranchName(rs.getString("branchName"));
				b.setCardNo(rs.getInt("cardNo"));
				b.setBranchId(rs.getInt("branchId"));
				bookLoans.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoans;
	}

	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	public void returnBook(Integer cardNo, Integer bookID, Integer branchId) throws SQLException {
		save("update tbl_book_loans set dateIn =CURDATE() where cardNo = ? and bookId = ? and branchId=?",
				new Object[] {cardNo,bookID,branchId});
	}
	public List<BookLoans> readAllLoans() throws SQLException {
		return read("select tbl_book_loans.cardNo,tbl_book.bookId, tbl_book.title, tbl_book_loans.dueDate, tbl_library_branch.branchName,tbl_library_branch.branchId from tbl_book, tbl_book_loans,tbl_library_branch where tbl_book.bookId= tbl_book_loans.bookId and tbl_book_loans.branchId= tbl_library_branch.branchId",null);
	}
	public void overrideDue(String duedate, Integer cardNo, Integer branchId,
			Integer bookId) throws SQLException {
		save("update tbl_book_loans set dueDate =? where cardNo = ? and bookId = ? and branchId=?",
				new Object[] {duedate,cardNo,bookId,branchId});
	}

}
