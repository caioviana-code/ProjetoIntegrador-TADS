package ifpr.pgua.eic.mylist.controllers.viewmodels;

import java.util.List;

import ifpr.pgua.eic.mylist.App;
import ifpr.pgua.eic.mylist.models.entities.Usuario;
import ifpr.pgua.eic.mylist.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelaCadastroViewModel {
    
    private StringProperty loginProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();
    private StringProperty emailProperty = new SimpleStringProperty();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private UsuarioRepository repository;

    public TelaCadastroViewModel(UsuarioRepository repository) {
        this.repository = repository;
    }

    public StringProperty loginProperty() {
        return loginProperty;
    }

    public StringProperty senhaProperty() {
        return senhaProperty;
    }

    public StringProperty emailProperty() {
        return emailProperty;
    }

    public ObjectProperty<Result> alertProperty() {
        return alertProperty;
    }

    public Result cadastro() {

        List<Usuario> usuarios = repository.getUsuarios();

        String login = loginProperty.getValue();
        String senha = senhaProperty.getValue();
        String email = emailProperty.getValue();

        if (login == null || senha == null || email == null) {
            alertProperty.setValue(Result.fail("Preencha os campos corretamente!"));
            return null;
        }

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) || u.getEmail().equals(email)) {
                alertProperty.setValue(Result.success("Usuário já cadastrado"));
                return null;
            }
        }

        repository.adicionarUsuario(login, senha, email);
        alertProperty.set(Result.success("Usuário cadastrado!"));
        App.pushScreen("LOGIN");
        return null;
    }

    public void voltar() {
        App.pushScreen("LOGIN");
    }
}
