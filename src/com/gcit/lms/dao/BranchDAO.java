package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

public class BranchDAO extends BaseDAO {

	public BranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public Integer addBranchWithID(Branch branch) throws SQLException{
		return saveWithID("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[] { branch.getBranchName(),branch.getBranchAddress() });		
	}
	public void updateBranch(Branch branch) throws SQLException{
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchAddress(),branch.getBranchId() });
	}
	public void deleteAuthor(Branch branch) throws SQLException {
		save("delete from tbl_library_branch where branchId = ?", new Object[] { branch.getBranchId()});
	}/*
	public Map<Book,Integer> readAllLoans() throws SQLException {
		//return read("select * from tbl_book_copies", null);
	}
*/

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> extractData(ResultSet rs) {
		List<Branch> branches = new ArrayList<Branch>();
		BookDAO bdao = new BookDAO(conn);
		try {
			while (rs.next()) {
				Branch b = new Branch();
				BookCopiesDAO bookCopiesDAO= new BookCopiesDAO(conn);
				b.setBranchId(rs.getInt("branchId"));
				b.setBranchName(rs.getString("branchName"));
				b.setBranchAddress(rs.getString("branchAddress"));
				b.setCopyList(bookCopiesDAO.readFirstLevel(
								"select * from tbl_book, tbl_book_copies where tbl_book.bookId IN (select tbl_book_copies.bookId from tbl_book_copies where branchId = ?) and tbl_book.bookId= tbl_book_copies.bookId",
								new Object[] { b.getBranchId()}));
				branches.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<T>) branches;
	}

	@Override
	public <T> List<T> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Branch> readAllBranches(int pageNo) throws SQLException {
		setPageNo(pageNo);
		return read("select * from tbl_library_branch", null);
	}
	public void addBranch(Branch branch) throws SQLException {
		save("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[] { branch.getBranchName(),branch.getBranchAddress() });
	}
	public Branch readBorrowerByID(Integer branchId) throws SQLException {
		List<Branch> branches = read("select * from tbl_library_branch where branchId = ?", new Object[] {branchId});
		if(branches!=null){
			return branches.get(0);
		}
		return null;
	}
	public void deleteBranch(Integer branchId) throws SQLException {
		System.out.println("delete: "+branchId);
		save("delete from tbl_library_branch where branchId = ?", new Object[] {branchId});
	}
	public List<BookCopies> readBranchById(int branchId) throws SQLException {
		BookCopiesDAO bookCopiesDAO= new BookCopiesDAO(conn);
		return bookCopiesDAO.readFirstLevel("select * from tbl_book, tbl_book_copies where tbl_book.bookId IN (select tbl_book_copies.bookId from tbl_book_copies where branchId = ?) and tbl_book.bookId= tbl_book_copies.bookId",new Object[] {branchId});
	}

}
