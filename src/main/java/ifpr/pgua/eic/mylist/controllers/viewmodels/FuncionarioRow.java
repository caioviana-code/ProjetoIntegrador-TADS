package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FuncionarioRow {
    
    private Funcionario funcionario;

    public FuncionarioRow(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public StringProperty nomeProperty() {
        return new SimpleStringProperty(funcionario.getNome());
    }

    public StringProperty cpfProperty() {
        return new SimpleStringProperty(funcionario.getCpf());
    }

    public StringProperty telefoneProperty() {
        return new SimpleStringProperty(funcionario.getTelefone());
    }
}
