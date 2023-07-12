package cadastro.usuario.service;
import cadastro.usuario.db.ConexaoDatabase;
import cadastro.usuario.model.Cadastros;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroService {
       private static ConexaoDatabase conexao = new ConexaoDatabase();

        // Inserir Usuario (INSERT)
        public static void inserirCadastro(Cadastros cadastro) {
            try {
                Connection conn = conexao.getConexao();
                String sql = "INSERT INTO cadastros (id_usuario, nome, cpf, endereco, telefone) " +
                        "VALUES ( ?, ?, ?, ?, ?)";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, Integer.parseInt(cadastro.idUsuario));
                pre.setString(2, cadastro.nome);
                pre.setString(3, cadastro.cpf);
                pre.setString(4, cadastro.endereco);
                pre.setString(5, cadastro.telefone);

                pre.execute();
                pre.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}


