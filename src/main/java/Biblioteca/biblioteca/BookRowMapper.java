package Biblioteca.biblioteca;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int arg1) throws SQLException {
        Book book = new Book(rs.getString("titolo"), rs.getString("autore"), rs.getString("annoPB"), rs.getString("link"));
        book.setTitolo(rs.getString("titolo"));
        book.setAutore(rs.getString("autore"));
        book.setAnnoPB(rs.getString("annoPB"));
        book.setLink(rs.getString("link"));

        return book;
    }
}
