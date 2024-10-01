package biblioteca.connection;

import br.edu.ifpr.foz.biblioteca.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionFactoryTest {

    @Test
    public void deveRealizarConexaoSemExcecao(){
        Connection connection = ConnectionFactory.getConnection();
    }

}
