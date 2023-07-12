package cadastro.usuario.service;
import cadastro.usuario.db.ConexaoDatabase;
import cadastro.usuario.model.Usuarios;
import java.sql.*;
public class UsuarioService {
    private static ConexaoDatabase conexao = new ConexaoDatabase();

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

    // Validar o documento único
    public static boolean buscarUsuarioByUsuario(String usuario) {
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
