package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;

@SuppressWarnings("unchecked")
public class BookDAO extends BaseDAO{
	
	public BookDAO(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws SQLException{
		Integer bookId= saveWithID("insert into tbl_book (title, pubId) values (?, ?)", new Object[] {book.getTitle(), book.getPid()});
		int i =0;
		while(i<book.getAuthors().size()){
			save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] {bookId, book.getAuthors().get(i).getAuthorID()});
			i++;
		}
		i=0;
		while(i<book.getGenres().size()){
			save("insert into tbl_book_genres (genre_id, bookId) values (?, ?)", new Object[] {book.getGenres().get(i).getGenreId(),bookId} );
			i++;
		}
	}
	public void updateBook(Book book) throws SQLException{
		save("update tbl_book set title = ?,pubId=? where bookId = ?", new Object[] {book.getTitle(),book.getPid(), book.getBookId()});
		save("delete from tbl_book_authors where bookId = ?", new Object[] {book.getBookId()});
		int i =0;
		while(i<book.getAuthors().size()){
			save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] {book.getBookId(), book.getAuthors().get(i).getAuthorID()});
			i++;
		}
		save("delete from tbl_book_genres where bookId = ?", new Object[] {book.getBookId()});

		i=0;
		while(i<book.getGenres().size()){
			save("insert into tbl_book_genres (genre_id, bookId) values (?, ?)", new Object[] {book.getGenres().get(i).getGenreId(),book.getBookId()} );
			i++;
		}
	}
	
	public void deleteBook(Integer bookId) throws SQLException{
		save("delete from tbl_book where bookId = ?", new Object[] {bookId});
	}
	
	public List<Book> readAllBooks() throws SQLException{
		return read("select * from tbl_book", null);
	}
	public List<Book> readBookExceptCardNo(Integer cardNo) throws SQLException{
		return read("select tbl_book_copies.bookId, title, tbl_book_copies.branchId, tbl_library_branch.branchName from tbl_book,tbl_book_copies,tbl_library_branch where tbl_book_copies.bookId not in (select tbl_book_loans.bookId from tbl_book_loans where tbl_book_loans.cardNo= ?) and tbl_book.bookId= tbl_book_copies.bookId and tbl_book_copies.branchId= tbl_library_branch.branchId", new Object[] {cardNo});
	}
	public List<Book> readBookOnlyCardNo(Integer cardNo) throws SQLException{
		return read("select distinct tbl_book_copies.bookId,title, tbl_book_copies.branchId, tbl_library_branch.branchName from tbl_book,tbl_book_copies,tbl_library_branch where tbl_book_copies.bookId in (select tbl_book_loans.bookId from tbl_book_loans where tbl_book_loans.cardNo= ?) and tbl_book.bookId= tbl_book_copies.bookId and tbl_book_copies.branchId= tbl_library_branch.branchId", new Object[] {cardNo});
	}
	public Integer getBookCount() throws SQLException {
		return getCount("select count(*) from tbl_book", null);
	}
	@Override
	public List<Book> extractData(ResultSet rs){
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		List<Book> books = new ArrayList<Book>();
		try {
			while(rs.next()){
				Book b = new Book();
				Genre genre = new Genre();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				b.setPid(rs.getInt("pubId"));
				b.setAuthors(adao.readFirstLevel(
						"select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)",
						new Object[] { b.getBookId()}));
				b.setGenres(gdao.read(
						"select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId = ?)",
						new Object[] { b.getBookId()}));
				books.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) {
		List<Book> books = new ArrayList<Book>();
		try {
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				books.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	public Book readBookByID(int bookId) throws SQLException {
		List<Book> books = read("select * from tbl_book where bookId = ?", new Object[] {bookId});
		if(books!=null){
			return books.get(0);
		}
		return null;
	}

	public List<Book> searchBooks(String title, Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateBook(Integer bookId, String title) throws SQLException {
		save("update tbl_book set title = ? where bookId = ?", new Object[] {title,bookId});
		
	}
}
