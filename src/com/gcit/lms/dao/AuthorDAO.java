package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

@SuppressWarnings("unchecked")
public class AuthorDAO extends BaseDAO {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws SQLException {
		Integer authorId = saveWithID("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
		int i =0;
		while(i<author.getBooks().size()){
			save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] { author.getBooks().get(i).getBookId(),authorId});
			i++;
		}
	}
	
	public Integer addAuthorWithID(Author author) throws SQLException {
		return saveWithID("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}
	
	public void addBookAuthor(Author author) throws SQLException {
		for(Book b: author.getBooks()){
			save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] { author.getAuthorID(), b.getBookId() });
		}
	}

	public void updateAuthor(Author author) throws SQLException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorID() });
		
		save("delete from tbl_book_authors where authorId = ?", new Object[] {author.getAuthorID()});
		int i =0;
		while(i<author.getBooks().size()){
			save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] {author.getBooks().get(i).getBookId(),author.getAuthorID()});
			i++;
		}
	}

	public void deleteAuthor(Integer authorId) throws SQLException {
		save("delete from tbl_author where authorId = ?", new Object[] {authorId});
	}

	public List<Author> readAllAuthors(int pageNo) throws SQLException {
		setPageNo(pageNo);
		return read("select * from tbl_author", null);
	}

	@Override
	public List<Author> extractData(ResultSet rs) {
		List<Author> authors = new ArrayList<Author>();
		BookDAO bdao = new BookDAO(conn);
		try {
			while (rs.next()) {
				Author a = new Author();
				a.setAuthorID(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				a.setBooks(bdao.readFirstLevel(
						"select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)",
						new Object[] { a.getAuthorID()}));
				authors.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) {
		List<Author> authors = new ArrayList<Author>();
		try {
			while (rs.next()) {
				Author a = new Author();
				a.setAuthorID(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				authors.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authors;
	}

	public Author readByID(Integer authorID) throws SQLException {
		
		List<Author> authors = read("select * from tbl_author where authorId = ?", new Object[] {authorID});
		if(authors!=null){
			return authors.get(0);
		}
		return null;
	}

	public Integer getAuthorCount() throws SQLException {
		return getCount("select count(*) from tbl_author", null);
	}
	public Integer getSearchCount(String authorName) throws SQLException {
		return getCount("select count(*) from tbl_author where tbl_author.authorName like ?", new Object[] {"%"+authorName+"%"});
	}
	public List<Author> searchAuthors(String authorName, int pageNo) throws SQLException {
		setPageNo(pageNo);
		List<Author> authors =read("select * from tbl_author where tbl_author.authorName like ?", new Object[] {"%"+authorName+"%"});
		return authors;
	}
}
