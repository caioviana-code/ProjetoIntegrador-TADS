package ifpr.pgua.eic.mylist.models.repositories;

import java.time.LocalDateTime;
import java.util.List;

import ifpr.pgua.eic.mylist.models.daos.EmprestimoDAO;
import ifpr.pgua.eic.mylist.models.daos.FerramentaDAO;
import ifpr.pgua.eic.mylist.models.daos.FuncionarioDAO;
import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.entities.ItemEmprestimo;
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

    public Result cadastrar(Funcionario funcionario, String cpfFuncionario, List<ItemEmprestimo> itens, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, Integer status) {

        if (funcionario == null) {
            return Result.fail("Funcionário invpalido");
        }

        if (dataEmprestimo.isAfter(LocalDateTime.now())) {
            return Result.fail("Data e hora inválida");
        }

        if (itens.size() == 0) {
            return Result.fail("Nenhum item selecionado");
        }

        for (ItemEmprestimo item : itens) {
            if (item.getQuantidade() > item.getFerramenta().getEstoque()) {
                return Result.fail("Não há a quantidade da ferramenta no estoque");
            }
        }

        Emprestimo emprestimo = new Emprestimo(funcionario, cpfFuncionario, itens, dataEmprestimo, dataDevolucao, status);

        Result result = emprestimoDAO.create(emprestimo);

        if (result instanceof SuccessResult) {

            for (ItemEmprestimo item : emprestimo.getItens()) {

                Ferramenta ferramenta = item.getFerramenta();
                int quantidade = ferramenta.getEstoque() - item.getQuantidade();
                Ferramenta novaFerramenta = new Ferramenta(ferramenta.getId(), ferramenta.getNome(), quantidade);

                ferramentaDAO.update(novaFerramenta);
            }
        }

        return result;
    }

    private Funcionario carregaFuncionarioEmprestimo(int id) {
        return funcionarioDAO.getFuncionarioFromEmprestimo(id);
    }

    private void carregarFerramentaItensEmprestimo(Emprestimo emprestimo) {
        for (ItemEmprestimo item : emprestimo.getItens()) {
            item.setFerramenta(ferramentaDAO.getFerramentaItem(item.getId()));
        }
    }

    public List<Emprestimo> listar() {

        List<Emprestimo> emprestimos = emprestimoDAO.listAll();

        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.setFuncionario(carregaFuncionarioEmprestimo(emprestimo.getId()));
            carregarFerramentaItensEmprestimo(emprestimo);
        }

        return emprestimos;
    }
}
