package ifpr.pgua.eic.mylist.models.repositories;

import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.mylist.models.daos.UsuarioDAO;
import ifpr.pgua.eic.mylist.models.entities.Usuario;
import ifpr.pgua.eic.mylist.models.results.Result;

public class UsuarioRepository {
    
    private List<Usuario> usuarios;
    private UsuarioDAO dao;

    public UsuarioRepository(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Result adicionarUsuario(String login, String senha, String email) {

        if (login == null || senha == null || email == null) {
            return Result.fail("Dados inválidos!");
        }
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) || u.getEmail().equals(email)) {
                return Result.fail("Usuário já cadastrado");
            }
        }

        Usuario usuario = new Usuario(login, senha, email);
        return dao.create(usuario);
    }

    public List<Usuario> getUsuarios() {
        usuarios = dao.ListAll();
        return Collections.unmodifiableList(usuarios);
    }
}
