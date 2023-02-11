package ifpr.pgua.eic.mylist.models.daos;

import java.util.List;

import ifpr.pgua.eic.mylist.models.entities.Usuario;
import ifpr.pgua.eic.mylist.models.results.Result;

public interface UsuarioDAO {
    Result create(Usuario usuario);
    List<Usuario> ListAll(); 
}
