package cadastro.usuario.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexaoDatabase {
    private Connection conexao = null;
    public synchronized Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastro-usuarios",
                    "postgres", "postgres");
        }
        return conexao;
    }
}
