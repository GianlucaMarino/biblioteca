package Biblioteca.biblioteca;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {
    NamedParameterJdbcTemplate template;

    public BookDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }


    @Override
    public List<Book> findAll() {
        return template.query("select * from biblioteca", new BookRowMapper());
    }

    public List<Book> findBook(String titolo) {
        final String sql ="select * from biblioteca where bookID=:bookID";
        SqlParameterSource param = new MapSqlParameterSource().addValue("titolo", titolo);
        return template.query(sql, param, rs -> {
            List<Book> tempList = new ArrayList<Book>();
            while(rs.next()){
                // Utente usr = new Utente(rs.getString("userId"),rs.getString("nome"),
                Book book = new Book(rs.getString("titolo"), rs.getString("autore"),
                        rs.getString("annoPB"),rs.getString("link"));
                tempList.add(book);
                break;
            }
            return tempList;
        });
    }



    @Override
    public void insertBook(Book book) {
        final String sql = "insert into users(titolo , autore,bookID,annoPB, link) values(:titolo,:autore, bookID,:annoPB,:link)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                //.addValue("userId", usr.getUserId())
                .addValue("titolo",book.getTitolo() )
                .addValue("autore",book.getAutore() )
              //  .addValue("bookID",book.getBookID() )
                .addValue("annoPB",book.getAnnoPB())
                .addValue("link",book.getLink());
        template.update(sql,param, holder);
    }

    @Override
    public void updateBook(Book book) {
        final String sql = "update biblioteca set bookID=:bookID, titolo=:titolo,autore:=autore,annoPB = annoPB, link=:link, where bookID=bookID";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                //.addValue("userId", usr.getUserId())
                .addValue("titolo",book.getTitolo() )
                .addValue("autore",book.getAutore() )
             //   .addValue("bookID",book.getBookID() )
                .addValue("annoPB",book.getAnnoPB())
                .addValue("link",book.getLink());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateBook(Book book) {
        final String sql = "update biblioteca set titolo=:titolo, autore=:autore, annoPB = annoPB, link=:link, where bookID=bookID";
        Map<String, Object> map = new HashMap<String, Object>();
        //.addValue("userId", usr.getUserId())
        map.put("titolo", book.getTitolo());
        map.put("autore", book.getAutore());
      //  map.put("bookID", book.getBookID());
        map.put("annoPB", book.getAnnoPB());
        map.put("link", book.getLink());


        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

            @Override
            public void deleteBook(Book book) {
                final String sql = "delete from biblioteca where titolo=:titolo";

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("titolo", book.getTitolo());
                template.execute(sql, map, new PreparedStatementCallback<Object>() {

                    @Override
                    public Object doInPreparedStatement(PreparedStatement ps)
                            throws SQLException, DataAccessException {
                        return ps.executeUpdate();
                    }
                });
            }
        }