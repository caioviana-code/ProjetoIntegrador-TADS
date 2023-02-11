package ifpr.pgua.eic.mylist.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.entities.Usuario;
import ifpr.pgua.eic.mylist.models.results.Result;

public class JDBCUsuarioDAO implements UsuarioDAO { 

    private static final String SQL_INSERT = "INSERT INTO pi_usuarios(login,senha,email) VALUES (?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_usuarios";

    FabricaConexoes fabricaConexoes;

    public JDBCUsuarioDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result create(Usuario usuario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_INSERT);

            prep_Statement.setString(1, usuario.getLogin());
            prep_Statement.setString(2, usuario.getSenha());
            prep_Statement.setString(3, usuario.getEmail());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Usuário cadastrado com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    private Usuario buildFrom(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String login = result.getString("login");
        String senha = result.getString("senha");
        String email = result.getString("email");

        Usuario usuario = new Usuario(id, login, senha, email);

        return usuario;
    }

    @Override
    public List<Usuario> ListAll() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet result = prep_Statement.executeQuery();

            while (result.next()) {
                Usuario usuario = buildFrom(result);
                usuarios.add(usuario);
            }

            result.close();
            prep_Statement.close();
            con.close();

            return usuarios;

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
    
}
