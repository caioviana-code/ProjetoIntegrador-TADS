package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CadastroFerramentaViewModel {
    
    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty estoqueProperty = new SimpleStringProperty();

    private FerramentaRepository repository;

    public CadastroFerramentaViewModel(FerramentaRepository repository) {
        this.repository = repository;
    }

    public StringProperty nomeProperty() {
        return nomeProperty;
    }

    public StringProperty estoqueProperty() {
        return estoqueProperty;
    }

    public void cadastrar() {
        String nome = nomeProperty.getValue();
        String str_estoque = estoqueProperty.getValue();

        int estoque = Integer.parseInt(str_estoque);

        repository.adicionarFerramenta(nome, estoque);

        limpar();
    }

    public void limpar() {
        nomeProperty.setValue("");
        estoqueProperty.setValue("");
    }
}
