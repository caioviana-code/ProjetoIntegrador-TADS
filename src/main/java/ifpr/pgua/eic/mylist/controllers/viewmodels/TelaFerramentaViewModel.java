package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaFerramentaViewModel {
    
    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty estoqueProperty = new SimpleStringProperty();

    private ObservableList<FerramentaRow> ferramentas = FXCollections.observableArrayList();

    private StringProperty operacao = new SimpleStringProperty("Cadastrar");
    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private BooleanProperty desativado = new SimpleBooleanProperty(true);
    private boolean atualizar = false;

    private ObjectProperty<FerramentaRow> selecionado = new SimpleObjectProperty<>();

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

    public ObservableList<FerramentaRow> getFerramentas() {
        return ferramentas;
    }

    public StringProperty operacaoProperty() {
        return operacao;
    }

    public BooleanProperty podeEditarProperty() {
        return podeEditar;
    }

    public BooleanProperty desativadoProperty() {
        return desativado;
    }

    public ObjectProperty<FerramentaRow> selecionadoProperty() {
        return selecionado;
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

        if (atualizar) {
            repository.atualizarFerramenta(nome, estoque);
        } else {
            repository.adicionarFerramenta(nome, estoque);
        }

        updateList();
        limpar();
    }

    public void atualizar() {
        operacao.setValue("Atualizar");
        podeEditar.setValue(false);
        desativado.setValue(false);
        atualizar = true;
        Ferramenta ferramenta = selecionado.get().getFerramenta();
        nomeProperty.setValue(ferramenta.getNome());
        estoqueProperty.setValue(String.valueOf(ferramenta.getEstoque()));
    }

    public void excluir() {
        Ferramenta ferramenta = selecionado.get().getFerramenta();
        repository.excluirFerramenta(ferramenta);

        updateList();
        limpar();
    }

    public void limpar() {
        nomeProperty.setValue("");
        estoqueProperty.setValue("");
        podeEditar.setValue(true);
        desativado.setValue(true);
        atualizar = false;
        operacao.setValue("Cadastrar");
    }

}