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

    private static final String SQL_INSERT = "INSERT INTO pi_ferramentas(nome,estoque) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE pi_ferramentas SET nome=?, estoque=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM pi_ferramentas WHERE id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_ferramentas";

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

            return Result.success("Ferramenta cadastrado com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    @Override
    public Result update(Ferramenta ferramenta) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_UPDATE);

            prep_Statement.setString(1, ferramenta.getNome());
            prep_Statement.setInt(2, ferramenta.getEstoque());
            prep_Statement.setInt(3, ferramenta.getId());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Ferramenta atualizada com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    @Override
    public Result delete(Ferramenta ferramenta) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_DELETE);

            prep_Statement.setInt(1, ferramenta.getId());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Ferramente excluida com sucesso");

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

    @Override
    public Ferramenta getById(int id) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_statement = con.prepareStatement("SELECT * FROM pi_ferramentas WHERE id=?");

            prep_statement.setInt(1, id);

            ResultSet result = prep_statement.executeQuery();
            Ferramenta ferramenta = null;

            while (result.next()) {
                ferramenta = buildFrom(result);
            }

            result.close();
            prep_statement.close();
            con.close();

            return ferramenta;

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }

    @Override
    public Ferramenta getFerramentaItem(int idFerramenta) {
        Ferramenta ferramenta = null;

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement("SELECT idFerramenta FROM pi_itensemprestimo WHERE id=?");

            prep_Statement.setInt(1, idFerramenta);

            ResultSet result = prep_Statement.executeQuery();
            result.next();

            int idItem = result.getInt("idFerramenta");

            result.close();
            prep_Statement.close();
            con.close();

            ferramenta = getById(idItem);

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        return ferramenta;
    }

}
