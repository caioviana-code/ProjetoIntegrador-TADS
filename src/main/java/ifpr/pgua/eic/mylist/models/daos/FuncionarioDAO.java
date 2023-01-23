package ifpr.pgua.eic.mylist.models.daos;

import java.util.List;

import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.results.Result;

public interface FuncionarioDAO {
    Result create(Funcionario funcionario);
    Result update(Funcionario funcionario);
    Result delete(Funcionario funcionario);
    List<Funcionario> ListAll(); 
}
