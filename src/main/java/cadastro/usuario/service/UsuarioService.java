package cadastro.usuario.service;
import cadastro.usuario.db.ConexaoDatabase;
import cadastro.usuario.model.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UsuarioService {
    private static ConexaoDatabase conexao = new ConexaoDatabase();
    public static List<Usuarios> carregarUsuario() {
        List<Usuarios> out = new ArrayList<>();
        try {
            Connection conn = conexao.getConexao();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuarios usuario = new Usuarios(
                        rs.getInt("id"),
                        rs.getString("usuario"),
                        rs.getString("senha"));

                out.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    // Inserir Usuario (INSERT)
    public static void inserirUsuario(Usuarios usuario) {
        try {
            Connection conn = conexao.getConexao();
            String sql = "INSERT INTO usuarios (usuario, senha) VALUES ( ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, usuario.nomeUsuario);
            pre.setString(2, usuario.senha);

            pre.execute();
            pre.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Atualizar Usuario (UPDATE)
    public static boolean atualizarUsuario(int idUsuario, Usuarios usuario) {
        try {
            Connection conn = conexao.getConexao();
            String updateSql = "UPDATE usuarios " +
                    "SET usuario = ?, senha = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setString(1, usuario.nomeUsuario);
            ps.setString(2, usuario.senha);
            ps.setInt(3, idUsuario); // Não funciona dessa forma usuario.getId();

            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Excluir Usuario (DELETE)
    public static boolean deletarUsuario(int idUsuario) {
        try {
            Connection conn = conexao.getConexao();
            String deleteSql = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idUsuario);

            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Validar o documento único
    public static boolean buscarUsuarioByDocumento(String usuario) {
        try {
            Connection conn = conexao.getConexao();
            String selectSql = "SELECT id FROM usuarios WHERE usuario = '" + usuario + "'"; // precisa colocar entre aspas simples
            Statement sta = conn.createStatement();
            ResultSet resultSet = sta.executeQuery(selectSql);
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
