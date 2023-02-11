package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.App;
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

    public void entrar() {

        alertProperty.setValue(Result.success("Login efetuado com sucesso!"));
        App.pushScreen("PRINCIPAL");
    }

    public void cadastro() {
        App.pushScreen("CADASTRO");
    }
}