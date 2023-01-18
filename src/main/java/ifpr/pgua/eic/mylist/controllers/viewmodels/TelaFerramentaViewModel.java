package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaFerramentaViewModel {
    
    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty estoqueProperty = new SimpleStringProperty();

    private ObservableList<FerramentaRow> ferramentas = FXCollections.observableArrayList();

    private FerramentaRepository repository;

    public TelaFerramentaViewModel(FerramentaRepository repository) {
        this.repository = repository;
    }

    public StringProperty nomeProperty() {
        return nomeProperty;
    }

    public StringProperty estoqueProperty() {
        return estoqueProperty;
    }

    public void updateList() {
        ferramentas.clear();
        for (Ferramenta f: repository.getFerramentas()) {
            ferramentas.add(new FerramentaRow(f));
        }
    }

    public void cadastrar() {
        String nome = nomeProperty.getValue();
        String str_estoque = estoqueProperty.getValue();

        int estoque = Integer.parseInt(str_estoque);

        repository.adicionarFerramenta(nome, estoque);

        updateList();
        limpar();
    }

    public void limpar() {
        nomeProperty.setValue("");
        estoqueProperty.setValue("");
    }

    public ObservableList<FerramentaRow> getFerramentas() {
        return ferramentas;
    }
}
