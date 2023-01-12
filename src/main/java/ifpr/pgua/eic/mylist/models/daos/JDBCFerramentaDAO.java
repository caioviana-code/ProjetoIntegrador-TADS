package ifpr.pgua.eic.mylist.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.results.Result;

public class JDBCFerramentaDAO implements FerramentaDAO {

    private static final String SQL_INSERT = "INSERT INTO pi_ferramenta(nome,estoque) VALUES (?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_ferramenta";

    FabricaConexoes fabricaConexoes;

    public JDBCFerramentaDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result create(Ferramenta ferramenta) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_statement = con.prepareStatement(SQL_INSERT);

            prep_statement.setString(1, ferramenta.getNome());
            prep_statement.setInt(2, ferramenta.getEstoque());

            prep_statement.execute();

            prep_statement.close();
            con.close();

            return Result.success("Livro cadastrado com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    private Ferramenta buildFrom(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        int estoque = result.getInt("estoque");

        Ferramenta ferramenta = new Ferramenta(id, nome, estoque);

        return ferramenta;
    }

    @Override
    public List<Ferramenta> ListAll() {

        ArrayList<Ferramenta> ferramentas = new ArrayList<>();

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_statement = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet result = prep_statement.executeQuery();

            while (result.next()) {
                Ferramenta ferramenta = buildFrom(result);
                ferramentas.add(ferramenta);   
            }

            result.close();
            prep_statement.close();
            con.close();

            return ferramentas;

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
    
}