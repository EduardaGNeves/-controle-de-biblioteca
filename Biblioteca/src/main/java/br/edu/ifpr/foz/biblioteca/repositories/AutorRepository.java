package br.edu.ifpr.foz.biblioteca.repositories;

import br.edu.ifpr.foz.biblioteca.ConnectionFactory;
import br.edu.ifpr.foz.biblioteca.exceptions.DatabaseException;
import br.edu.ifpr.foz.biblioteca.exceptions.DatabaseIntegrityException;
import br.edu.ifpr.foz.biblioteca.models.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {

    Connection connection;

    public AutorRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public List<Autor> getAll(){

        List<Autor> autores = new ArrayList<>();

        String sql = "SELECT * FROM Autor";

        try{
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()){

                Autor autor = new Autor();
                autor.setId(result.getInt("Id"));
                autor.setNome(result.getString("Nome"));
                autores.add(autor);
            }
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return autores;
    }

    public void delete(Integer id){
        String sql = "DELETE FROM Autor WHERE Id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0){
                System.out.println("Autor removido com sucesso!" + rowsDeleted);
            }
        }catch (Exception e){
            throw new DatabaseIntegrityException(e.getMessage());
        }
    }
}
