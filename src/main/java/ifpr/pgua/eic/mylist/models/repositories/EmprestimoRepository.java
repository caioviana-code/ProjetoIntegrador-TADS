package ifpr.pgua.eic.mylist.models.repositories;

import java.time.LocalDateTime;
import java.util.Collections;
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

    private List<Emprestimo> emprestimos;
    
    private EmprestimoDAO emprestimoDAO;
    private FuncionarioDAO funcionarioDAO;
    private FerramentaDAO ferramentaDAO;

    public EmprestimoRepository(EmprestimoDAO emprestimoDAO, FuncionarioDAO funcionarioDAO, FerramentaDAO ferramentaDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.ferramentaDAO = ferramentaDAO;
    }

    public Result adicionarEmprestimo(Funcionario funcionario, Ferramenta ferramenta, Integer quantidade, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, int status)  {
        Emprestimo emprestimo = new Emprestimo(funcionario, ferramenta, quantidade, dataEmprestimo, dataDevolucao, status);
        return emprestimoDAO.create(emprestimo);
    }    

    private Funcionario carregaFuncionarioEmprestimo(int id) {
        return funcionarioDAO.getFuncionarioFromEmprestimo(id);
    }

    private Ferramenta carregaFerramentaEmprestimo(int id) {
        return ferramentaDAO.getFerramentaFromEmprestimo(id);
    }

    public List<Emprestimo> getEmprestimos() {
        emprestimos = emprestimoDAO.listAll();

        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.setFuncionario(carregaFuncionarioEmprestimo(emprestimo.getId()));
            emprestimo.setFerramenta(carregaFerramentaEmprestimo(emprestimo.getId()));
        }

        return Collections.unmodifiableList(emprestimos);
    }
}
