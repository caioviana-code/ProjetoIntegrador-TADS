package ifpr.pgua.eic.mylist.models.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.mylist.models.daos.FuncionarioDAO;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.results.Result;

public class FuncionarioRepository {
    
    private List<Funcionario> funcionarios;
    private FuncionarioDAO dao;

    public FuncionarioRepository(FuncionarioDAO dao) {
        this.dao = dao;
    }

    public Result adicionarFuncionario(String nome, String cpf, String telefone) {
        Funcionario funcionario = new Funcionario(nome, cpf, telefone);
        return dao.create(funcionario);
    }

    public Result atualizarFuncionario(String cpf, String novoTelefone) {

        Optional<Funcionario> busca = funcionarios.stream().filter((c)->c.getCpf().equals(cpf)).findFirst();

        if (busca.isPresent()) {
            Funcionario funcionario = busca.get();
            funcionario.setTelefone(novoTelefone);
            return dao.update(funcionario);
        }
        return Result.fail("Funcionario não encontrado");
    }

    public Result excluirFuncionario(Funcionario funcionario) {
        return dao.delete(funcionario);
    }

    public List<Funcionario> getFuncionarios() {
        funcionarios = dao.ListAll();
        return Collections.unmodifiableList(funcionarios);
    }
}
