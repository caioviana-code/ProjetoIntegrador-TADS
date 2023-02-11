package ifpr.pgua.eic.mylist.controllers.viewmodels;

import java.util.List;

import ifpr.pgua.eic.mylist.App;
import ifpr.pgua.eic.mylist.models.entities.Usuario;
import ifpr.pgua.eic.mylist.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.mylist.models.results.Result;
import ifpr.pgua.eic.mylist.utils.Navigator.BorderPaneRegion;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelaLoginViewModel {
    
    private StringProperty loginProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private UsuarioRepository repository;

    public TelaLoginViewModel(UsuarioRepository repository) {
        this.repository = repository;
    }

    public StringProperty loginProperty() {
        return loginProperty;
    }

    public StringProperty senhaProperty() {
        return senhaProperty;
    }

    public ObjectProperty<Result> alertProperty() {
        return alertProperty;
    }

    public Result entrar() {

        List<Usuario> usuarios = repository.getUsuarios();

        String login = loginProperty.getValue();
        String senha = senhaProperty.getValue();

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                alertProperty.setValue(Result.success("Login efetuado com sucesso!"));
                App.pushScreen("PRINCIPAL");
                return null;
            }
        }

        alertProperty.setValue(Result.success("Usuário não encontrado!"));
        return null;
    }

    public void cadastro() {
        App.pushScreen("CADASTRO");
    }
}
