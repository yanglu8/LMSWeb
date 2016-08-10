package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Publisher;

public class BorrowerService {
	public void checkBook(Book book,Branch branch){		
	}
	/*
	public boolean checkCardNo(int cardNo) throws SQLException{
		Connection conn = ConnectionUtil.getConnection();
		boolean valid = false;
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(bdao.readBorrowerByID(cardNo) != null)
				valid = true;
			
		}catch (Exception e){
			conn.rollback();
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return valid;
	}*/
	public void returnBook(Integer cardNo,Integer bookID,Integer branchId) throws SQLException{
		System.out.println("service return");
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.returnBook(cardNo,bookID,branchId);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	public boolean varifyCard(Integer cardNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		boolean valid = false;
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(bdao.readBorrowerByID(cardNo)!=null){
				valid = true;
				System.out.println("service"+cardNo);
			}
			else {
				System.out.println("servicefail"+cardNo);
			}
		}catch (Exception e){
			conn.rollback();
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return valid;
	}
	public List<BookCopies> viewBooks(Integer cardNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			return bdao.readBookOnlyCardNo(cardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	public List<BookLoans> viewBookLoans(Integer cardNo) throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.readLoansOnlyCardNo(cardNo);
		} finally {
			conn.close();
		}
	}
	public List<BookLoans> viewAllLoans() throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.readAllLoans();
		} finally {
			conn.close();
		}
	}
	public void checkoutBook(Integer cardNo,Integer bookID,Integer branchId) throws SQLException {
		System.out.println("service borrow");
		Connection conn = ConnectionUtil.getConnection();
		try {
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.checkoutBook(cardNo,bookID,branchId);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	public void updateCopies(BookCopies bookCopies){}
}
