package cadastro.usuario.service;
import cadastro.usuario.db.ConexaoDatabase;
import cadastro.usuario.model.Cadastros;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroService {
       private static ConexaoDatabase conexao = new ConexaoDatabase();
        public static List<Cadastros> carregarCadastro() {
            List<Cadastros> out = new ArrayList<>();
            try {
                Connection conn = conexao.getConexao();
                Statement sta = conn.createStatement();
                ResultSet rs = sta.executeQuery("SELECT * FROM cadastros");
                while (rs.next()) {
                    Cadastros cadastro = new Cadastros(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("id_usuario"));

                    out.add(cadastro);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return out;
        }

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


