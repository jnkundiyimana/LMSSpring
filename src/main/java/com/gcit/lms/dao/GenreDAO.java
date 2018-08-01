package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> implements ResultSetExtractor<List<Genre>>{
	
	
	public void createGenre(Genre genre)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("INSERT INTO tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("update tbl_genre set genre_name =? where genre_id = ?", new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("delete from tbl_genre where genreId = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_genre", this);
	}

	public List<Genre> readAllGenresByName(String searchString) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_genre where genreName = ?", new Object[] { searchString }, this);
	}
	
	public List<Genre> readAllGenresByBook(Integer genreId) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_genre where genreId IN (select genreId from tbl_book_genres where genreId = ?", new Object[] { genreId }, this);
	}

	public Genre readGenrerByPK(Integer primaryKey) throws ClassNotFoundException, SQLException {
		List<Genre> genres = template.query("select * from tbl_genre where genreId = ?", new Object[] { primaryKey }, this);
		if (!genres.isEmpty()) {
			return genres.get(0);
		}
		return null;
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
