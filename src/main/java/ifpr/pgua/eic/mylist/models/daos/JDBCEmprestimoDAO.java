package ifpr.pgua.eic.mylist.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import ifpr.pgua.eic.mylist.models.FabricaConexoes;
import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.results.Result;

public class JDBCEmprestimoDAO implements EmprestimoDAO {

    private static final String SQL_INSERT = "INSERT INTO pi_emprestimos(idFuncionario,idFerramenta,quantidade,dataEmprestimo,dataDevolucao,status) VALUES (?,?,?,?,?,?)";
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
            prep_Statement.setTimestamp(5, Timestamp.valueOf(emprestimo.getDataDevolucao()));
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
    public List<Emprestimo> listAll() {
        return null;
    }
    
}