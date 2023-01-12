package ifpr.pgua.eic.mylist.models.repositories;

import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.mylist.models.daos.FerramentaDAO;
import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.results.Result;

public class FerramentaRepository {
    
    private List<Ferramenta> ferramentas;
    private FerramentaDAO dao;

    public FerramentaRepository(FerramentaDAO dao) {
        this.dao = dao;
    }

    public Result adicionarFerramenta(String nome, int estoque) {
        Ferramenta ferramenta = new Ferramenta(nome, estoque);
        return dao.create(ferramenta);
    }

    public List<Ferramenta> getFerramentas() {
        ferramentas = dao.ListAll();
        return Collections.unmodifiableList(ferramentas);
    }
}
