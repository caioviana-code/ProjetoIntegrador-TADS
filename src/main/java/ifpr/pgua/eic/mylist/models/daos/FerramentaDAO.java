package ifpr.pgua.eic.mylist.models.daos;

import java.util.List;

import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.results.Result;

public interface FerramentaDAO {
    Result create(Ferramenta ferramenta);
    Result update(Ferramenta ferramenta);
    Result delete(Ferramenta ferramenta);
    List<Ferramenta> ListAll(); 
    Ferramenta getById(int id);
    Ferramenta getFerramentaFromEmprestimo(int idFerramenta);
}
