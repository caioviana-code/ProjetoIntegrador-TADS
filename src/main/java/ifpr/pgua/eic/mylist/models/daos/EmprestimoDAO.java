package ifpr.pgua.eic.mylist.models.daos;

import java.util.List;

import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.results.Result;

public interface EmprestimoDAO {
    Result create(Emprestimo emprestimo);
    Result update(Emprestimo emprestimo);
    List<Emprestimo> listAll();
}
