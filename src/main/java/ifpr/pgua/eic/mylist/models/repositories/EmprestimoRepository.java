package ifpr.pgua.eic.mylist.models.repositories;

import java.time.LocalDateTime;
import java.util.List;

import ifpr.pgua.eic.mylist.models.daos.EmprestimoDAO;
import ifpr.pgua.eic.mylist.models.daos.FerramentaDAO;
import ifpr.pgua.eic.mylist.models.daos.FuncionarioDAO;
import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.results.Result;
import ifpr.pgua.eic.mylist.models.results.SuccessResult;

public class EmprestimoRepository {
    
    private EmprestimoDAO emprestimoDAO;
    private FuncionarioDAO funcionarioDAO;
    private FerramentaDAO ferramentaDAO;

    public EmprestimoRepository(EmprestimoDAO emprestimoDAO, FuncionarioDAO funcionarioDAO, FerramentaDAO ferramentaDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.ferramentaDAO = ferramentaDAO;
    }
}
