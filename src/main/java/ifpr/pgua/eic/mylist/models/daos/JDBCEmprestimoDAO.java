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
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.print.Collation;

public class JDBCEmprestimoDAO implements EmprestimoDAO {

    private static final String SQL_INSERT = "INSERT INTO pi_emprestimos(idFuncionario,cpfFuncionario,dataEmprestimo,dataDevolucao,status) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pi_emprestimos";

    private FabricaConexoes fabricaConexoes;

    public JDBCEmprestimoDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }


    @Override
    public Result create(Emprestimo emprestimo) {
        return null;
    }

    @Override
    public List<Emprestimo> listAll() {
        return null;
    }
    
}