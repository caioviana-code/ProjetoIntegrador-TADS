package ifpr.pgua.eic.mylist.models.daos;

import java.util.List;

import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.results.Result;

public interface FerramentaDAO {
    Result create(Ferramenta ferramenta);
    List<Ferramenta> ListAll(); 
}