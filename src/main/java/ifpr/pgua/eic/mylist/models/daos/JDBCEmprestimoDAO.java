package ifpr.pgua.eic.mylist.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.entities.ItemEmprestimo;
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.print.Collation;

public class JDBCEmprestimoDAO implements EmprestimoDAO {

    private static final String SQL_INSERT = "INSERT INTO pi_emprestimos(idFuncionario,cpfFuncionario,dataEmprestimo,dataDevolucao,status) VALUES (?,?,?,?,?)";
    private static final String SQL_INSERT_ITEM = "INSERT INTO pi_itensemprestimo(idEmprestimo,idFerramenta,quantidade) VALUES (?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_emprestimos";
    private static final String SQL_GET_ITENS_ID = "SELECT * FROM pi_itensemprestimo WHERE idEmprestimo=?";

    private FabricaConexoes fabricaConexoes;


    public JDBCEmprestimoDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }


    @Override
    public Result create(Emprestimo emprestimo) {
        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_statement = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            prep_statement.setInt(1, emprestimo.getFuncionario().getId());
            prep_statement.setString(2, emprestimo.getFuncionario().getCpf());
            prep_statement.setTimestamp(3, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            prep_statement.setTimestamp(4, null);
            prep_statement.setInt(5, emprestimo.getStatus());

            prep_statement.execute();

            ResultSet resultSet = prep_statement.getGeneratedKeys();
            resultSet.next();
            int idEmprestimo = resultSet.getInt(1);

            PreparedStatement prep_statement_item = con.prepareStatement(SQL_INSERT_ITEM);

            for (ItemEmprestimo item : emprestimo.getItens()) {
                prep_statement_item.setInt(1, idEmprestimo);
                prep_statement_item.setInt(2, item.getFerramenta().getId());
                prep_statement_item.setInt(3, item.getQuantidade());

                prep_statement_item.execute();
            }

            prep_statement_item.close();
            prep_statement.close();
            con.close();

            return Result.success("Empréstimo realizado com sucesso");
            
        } catch (SQLException err) {
            return Result.fail(err.getMessage());
        }
    }

    private Emprestimo buildFrom(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String cpfFuncionario = resultSet.getString("cpfFuncionario");
        LocalDateTime dataEmprestimo = resultSet.getTimestamp("dataEmprestimo").toLocalDateTime();
        LocalDateTime dataDevolucao = resultSet.getTimestamp("dataDevolucao").toLocalDateTime();
        int stauts = resultSet.getInt("status");

        Emprestimo emprestimo = new Emprestimo(id, cpfFuncionario, dataEmprestimo, dataDevolucao, stauts);

        return emprestimo;
    }

    private List<ItemEmprestimo> loadItens(int idEmprestimo) throws SQLException {
        List<ItemEmprestimo> itens = new ArrayList<>();

        Connection con = fabricaConexoes.getConnection();
        PreparedStatement prep_statement = con.prepareStatement(SQL_GET_ITENS_ID);

        prep_statement.setInt(1, idEmprestimo);

        ResultSet result = prep_statement.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            int quantidade = result.getInt("quantidade");

            ItemEmprestimo item = new ItemEmprestimo();
            item.setId(id);
            item.setQuantidade(quantidade);

            itens.add(item);
        }

        result.close();
        prep_statement.close();
        con.close();

        return itens;
    }

    @Override
    public List<Emprestimo> listAll() {
        List<Emprestimo> lista = new ArrayList<>();

        try {
            Connection con = fabricaConexoes.getConnection();
            PreparedStatement prep_statament = con.prepareStatement(SQL_SELECT_ALL);

            ResultSet result = prep_statament.executeQuery();

            while (result.next()) {
                Emprestimo emprestimo = buildFrom(result);
                emprestimo.setItens(loadItens(emprestimo.getId()));
                lista.add(emprestimo);
            }

            result.close();
            prep_statament.close();
            con.close();

            return lista;

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return Collections.emptyList();
        }
    }
    
}