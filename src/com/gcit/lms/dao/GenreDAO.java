package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO {
	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addGenre(Genre genre) throws SQLException{
		save("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}
	public Integer addGenreWithID(Genre genre) throws SQLException {
		return saveWithID("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}
	
	@Override
	public List<Genre> extractData(ResultSet rs) {
		List<Genre> genres = new ArrayList<Genre>();
		//GenreDAO gdao = new GenreDAO(conn);
		try {
			while (rs.next()) {
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_id"));
				g.setGenreName(rs.getString("genre_name"));
//				a.setBooks(bdao.readFirstLevel(
//						"select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?))",
//						new Object[] { a.getGenreID()}));
				genres.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;
	}

	@Override
	public <T> List<T> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Genre> readAllGenres(int pageNo) throws SQLException {
		setPageNo(pageNo);
		return read("select * from tbl_genre", null);
	}
	
	
}
