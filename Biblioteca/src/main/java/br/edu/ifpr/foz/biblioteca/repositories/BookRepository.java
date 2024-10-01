package br.edu.ifpr.foz.biblioteca.repositories;


import br.edu.ifpr.foz.biblioteca.ConnectionFactory;
import br.edu.ifpr.foz.biblioteca.exceptions.DatabaseException;
import br.edu.ifpr.foz.biblioteca.models.Autor;
import br.edu.ifpr.foz.biblioteca.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private Connection connection;

    public BookRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT book.*, autor.Nome AS AutorNome " +
                    "FROM book " +
                    "JOIN autor " +
                    "ON book.AutorId = autor.Id ");

            while (result.next()){
                Autor autor = instantiateAutor(result);
                Book book = instantiateBook(result, autor);
                books.add(book);
            }
            result.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return books;
    }

    public Book insert(Book book){

        String sql = "INSERT INTO book (Nome, DataCriacao, AutorId, Status) " +
                "VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, book.getNome());
            statement.setDate(2, Date.valueOf(book.getDataCriacao()));
            statement.setInt(3, book.getAutor().getId());
            statement.setString(4, book.getStatus());

            Integer rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0){
                ResultSet id = statement.getGeneratedKeys();

                id.next();

                Integer bookId = id.getInt(1);

                System.out.println("Rows inserted: " + rowsInserted);
                System.out.println("Id: " + bookId);

                book.setId(bookId);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return book;
    }

    public void update(Book book){
        String sql = "UPDATE book SET nome = ?, dataCriacao = ?, autorId = ?, status = ? WHERE (book.id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getNome());
            statement.setDate(2, Date.valueOf(book.getDataCriacao()));
            statement.setInt(3, book.getAutor().getId());
            statement.setString(4, book.getStatus());
            statement.setInt(5, book.getId());

            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated > 0){
                System.out.println("Rows updated: " + rowsUpdated);
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public void delete(Integer id){
        String sql = "DELETE FROM book WHERE Id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            Integer rowsDeleted = statement.executeUpdate();

            if(rowsDeleted > 0){
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
    }

    public Book getById(Integer id){

        Book book;
        Autor autor;

        String sql = "SELECT book.*,autor.Nome AS AutorNome " +
                "FROM book " +
                "INNER JOIN autor " +
                "ON book.AutorId = autor.Id " +
                "WHERE book.Id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                autor = this.instantiateAutor(resultSet);
                book = this.instantiateBook(resultSet, autor);
            }else{
                throw new DatabaseException("Livro nao encontrado");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return book;
    }

    private Book instantiateBook(ResultSet resultSet, Autor autor) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("Id"));
        book.setNome(resultSet.getString("Nome"));
        book.setDataCriacao(resultSet.getDate("DataCriacao").toLocalDate());
        book.setAutor(autor);
        book.setStatus(resultSet.getString("Status"));
        return book;
    }

    public Autor instantiateAutor(ResultSet resultSet) throws SQLException {
        Autor autor = new Autor();

        autor.setId(resultSet.getInt("AutorId"));
        autor.setNome(resultSet.getString("Nome"));
        return autor;
    }
}






















