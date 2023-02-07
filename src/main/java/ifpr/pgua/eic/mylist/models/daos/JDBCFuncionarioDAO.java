package ifpr.pgua.eic.mylist.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.results.Result;

public class JDBCFuncionarioDAO implements FuncionarioDAO{

    private static final String SQL_INSERT = "INSERT INTO pi_funcionarios(nome,cpf,telefone) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE pi_funcionarios SET telefone=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM pi_funcionarios WHERE id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_funcionarios";

    FabricaConexoes fabricaConexoes;

    public JDBCFuncionarioDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result create(Funcionario funcionario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_INSERT);

            prep_Statement.setString(1, funcionario.getNome());
            prep_Statement.setString(2, funcionario.getCpf());
            prep_Statement.setString(3, funcionario.getTelefone());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Funcionario cadastrado com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    @Override
    public Result update(Funcionario funcionario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_UPDATE);

            prep_Statement.setString(1, funcionario.getTelefone());
            prep_Statement.setInt(2, funcionario.getId());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Funcionario atualizado com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    @Override
    public Result delete(Funcionario funcionario) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_DELETE);

            prep_Statement.setInt(1, funcionario.getId());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Funcionario excluido com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    private Funcionario buildFrom(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        String telefone = result.getString("telefone");

        Funcionario funcionario = new Funcionario(id, nome, cpf, telefone);

        return funcionario;
    }

    @Override
    public List<Funcionario> ListAll() {

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_statement = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet result = prep_statement.executeQuery();

            while (result.next()) {
                Funcionario funcionario = buildFrom(result);
                funcionarios.add(funcionario);   
            }

            result.close();
            prep_statement.close();
            con.close();

            return funcionarios;

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }

    @Override
    public Funcionario getById(int id) {
        
        Funcionario funcionario = null;

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement("SELECT * FROM pi_funcionarios WHERE id=?");

            prep_Statement.setInt(1, id);

            ResultSet result = prep_Statement.executeQuery();

            while (result.next()) {
                funcionario = buildFrom(result);
            }

            result.close();
            prep_Statement.close();
            con.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }

        return funcionario;
    }

    @Override
    public Funcionario getFuncionarioFromEmprestimo(int idEmprestimo) {
        
        Funcionario funcionario = null;

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement("SELECT idFuncionario FROM pi_emprestimo WHERE id=?");

            prep_Statement.setInt(1, idEmprestimo);

            ResultSet result = prep_Statement.executeQuery();
            result.next();

            int idFuncionario = result.getInt("idFuncionario");

            funcionario = getById(idFuncionario);

            result.close();
            prep_Statement.close();
            con.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        return funcionario;
    }

}