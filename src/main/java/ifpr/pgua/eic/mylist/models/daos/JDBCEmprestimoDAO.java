package ifpr.pgua.eic.mylist.models.daos;

import java.rmi.StubNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.results.Result;

public class JDBCEmprestimoDAO implements EmprestimoDAO {

    private static final String SQL_INSERT = "INSERT INTO pi_emprestimos(idFuncionario,idFerramenta,quantidade,dataEmprestimo,dataDevolucao,status) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE pi_emprestimos SET status=?, dataDevolucao=? WHERE id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_emprestimos";

    private FabricaConexoes fabricaConexoes;

    public JDBCEmprestimoDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result create(Emprestimo emprestimo) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_INSERT);

            prep_Statement.setInt(1, emprestimo.getFuncionario().getId());
            prep_Statement.setInt(2, emprestimo.getFerramenta().getId());
            prep_Statement.setInt(3, emprestimo.getQuantidade());
            prep_Statement.setTimestamp(4, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            prep_Statement.setTimestamp(5, null);
            prep_Statement.setInt(6, emprestimo.getStatus());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Empréstimo bem sucedido");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    @Override
    public Result update(Emprestimo emprestimo) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_UPDATE);

            prep_Statement.setInt(1, 2);
            prep_Statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            prep_Statement.setInt(3, emprestimo.getId());

            prep_Statement.execute();

            prep_Statement.close();
            con.close();

            return Result.success("Empréstimo devolvido com sucesso");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Result.fail(err.getMessage());
        }
    }

    private Emprestimo buildFrom(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int quantidade = result.getInt("quantidade");
        LocalDateTime dataEmprestimo = result.getTimestamp("dataEmprestimo").toLocalDateTime();
        LocalDateTime dataDevolucao = null;
        try {
            dataDevolucao = result.getTimestamp("dataDevolucao").toLocalDateTime();
        } catch (NullPointerException err) {

        }
        int status = result.getInt("status");

        Emprestimo emprestimo = new Emprestimo(id, quantidade, dataEmprestimo, dataDevolucao, status);

        return emprestimo;
    }

    @Override
    public List<Emprestimo> listAll() {
        List<Emprestimo> lista = new ArrayList<>();

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_Statement = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet result = prep_Statement.executeQuery();

            while (result.next()) {
                Emprestimo emprestimo = buildFrom(result);
                if (emprestimo.getStatus() == 1) {
                    lista.add(emprestimo);
                }
            }

            result.close();
            prep_Statement.close();
            con.close();

            return lista;

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Collections.emptyList();
        }
    }

}