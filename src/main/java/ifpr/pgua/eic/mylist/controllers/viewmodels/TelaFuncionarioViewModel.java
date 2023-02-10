package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.repositories.FuncionarioRepository;
import ifpr.pgua.eic.mylist.models.results.Result;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaFuncionarioViewModel {
    
    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty cpfProperty = new SimpleStringProperty();
    private StringProperty telefoneProperty = new SimpleStringProperty();

    private ObservableList<FuncionarioRow> funcionarios = FXCollections.observableArrayList();

    private StringProperty operacao = new SimpleStringProperty("Cadastrar");
    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private BooleanProperty desativado = new SimpleBooleanProperty(true);
    private boolean atualizar = false;

    private ObjectProperty<FuncionarioRow> selecionado = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private FuncionarioRepository repository;

    public TelaFuncionarioViewModel(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public StringProperty nomeProperty() {
        return nomeProperty;
    }

    public StringProperty cpfProperty() {
        return cpfProperty;
    }

    public StringProperty telefoneProperty() {
        return telefoneProperty;
    }

    public ObservableList<FuncionarioRow> getFuncionarios() {
        return funcionarios;
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

    public ObjectProperty<FuncionarioRow> selecionadoProperty() {
        return selecionado;
    }

    public ObjectProperty<Result> alertProperty() {
        return alertProperty;
    }

    public void updateList() {
        funcionarios.clear();
        for (Funcionario f: repository.getFuncionarios()) {
            funcionarios.add(new FuncionarioRow(f));
        }
    }

    public void cadastrar() {
        String nome = nomeProperty.getValue();
        String cpf = cpfProperty.getValue();
        String telefone = telefoneProperty.getValue();

        if (atualizar) {
            repository.atualizarFuncionario(cpf, telefone);
            alertProperty.setValue(Result.success("Funcionário atualizado com sucesso"));
        } else {
            repository.adicionarFuncionario(nome, cpf, telefone);
            alertProperty.setValue(Result.success("Funcionário cadastrado com sucesso"));
        }

        updateList();
        limpar();
    }

    public void atualizar() {
        operacao.setValue("Atualizar");
        podeEditar.setValue(false);
        desativado.setValue(false);
        atualizar = true;
        Funcionario funcionario = selecionado.get().getFuncionario();
        nomeProperty.setValue(funcionario.getNome());
        cpfProperty.setValue(funcionario.getCpf());
        telefoneProperty.setValue(funcionario.getTelefone());
    }

    public void excluir() {
        Funcionario funcionario = selecionado.get().getFuncionario();
        repository.excluirFuncionario(funcionario);

        updateList();
        limpar();

        alertProperty.setValue(Result.success("Funcionário excluido com sucesso"));
    }

    public void limpar() {
        nomeProperty.setValue("");
        cpfProperty.setValue("");
        telefoneProperty.setValue("");
        podeEditar.setValue(true);
        desativado.setValue(true);
        atualizar = false;
        operacao.setValue("Cadastrar");
    }
}