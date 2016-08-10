package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO{

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public Integer addBorrowerWithID(Borrower borrower) throws SQLException {
		return saveWithID("insert into tbl_borrower (name,address,phone) values (?,?,?)", new Object[] { borrower.getName(),borrower.getAddress(),borrower.getPhone() });
	}
	public void updateBorrower(Borrower borrower) throws SQLException {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(),borrower.getPhone(),borrower.getCardNo() });
	}
	
	public void deleteBorrower(Integer borrowerId) throws SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] {borrowerId});
	}

	/*
	public void addBookBorrower(Borrower borrower) throws SQLException {
		for(Book b: borrower.getBookLoans()){
			save("insert into tbl_book_loans (bookId, authorId) values (?, ?)", new Object[] { author.getAuthorID(), b.getBookId() });
		}
	}
	*/
	@Override
	public List<Borrower> extractData(ResultSet rs) {
		List<Borrower> borrower = new ArrayList<Borrower>();
		try {
			while (rs.next()) {
				Borrower a = new Borrower();
				a.setCardNo(rs.getInt("cardNo"));
				a.setName(rs.getString("name"));
				a.setAddress(rs.getString("address"));
				a.setPhone(rs.getString("phone"));
//				a.setBooks(bdao.readFirstLevel(
//						"select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?))",
//						new Object[] { a.getAuthorID()}));
				borrower.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;
	}

	@Override
	public <T> List<T> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Borrower> readAllBorrowers(int pageNo) throws SQLException {
		setPageNo(pageNo);
		return read("select * from tbl_borrower", null);
	}
	public Borrower readBorrowerByID(int cardNo) throws SQLException {
		List<Borrower> borrowers = read("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		if(borrowers!=null){
			return borrowers.get(0);
		}
		return null;
	}

	public void addBorrower(Borrower borrower) throws SQLException {
		save("insert into tbl_borrower (name,phone,address) values (?,?,?)", new Object[] { borrower.getName(),borrower.getPhone(),borrower.getAddress() });
		}

}
