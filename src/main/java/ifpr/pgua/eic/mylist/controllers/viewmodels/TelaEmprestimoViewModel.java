package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.repositories.EmprestimoRepository;
import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
import ifpr.pgua.eic.mylist.models.repositories.FuncionarioRepository;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaEmprestimoViewModel {
    
    private StringProperty cpfFuncionarioProperty = new SimpleStringProperty();
    private StringProperty nomeFerramentaProperty = new SimpleStringProperty();
    private StringProperty quantidadeFerramentaProperty = new SimpleStringProperty();

    private  ObservableList<EmprestimoRow> emprestimos = FXCollections.observableArrayList();

    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private BooleanProperty desativado = new SimpleBooleanProperty(true);

    private ObjectProperty<EmprestimoRow> selecionado = new SimpleObjectProperty<>();

    private FerramentaRepository ferramentaRepository;
    private FuncionarioRepository funcionarioRepository;
    private EmprestimoRepository emprestimoRepository;

    public TelaEmprestimoViewModel(FerramentaRepository ferramentaRepository, FuncionarioRepository funcionarioRepository, EmprestimoRepository emprestimoRepository) {
        this.ferramentaRepository = ferramentaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public StringProperty cpfFuncionarioProperty() {
        return cpfFuncionarioProperty;
    }

    public StringProperty nomeFerramentaProperty() {
        return nomeFerramentaProperty;
    }

    public StringProperty quantidadeFerramentaProperty() {
        return quantidadeFerramentaProperty;
    }

    public ObservableList<EmprestimoRow> getEmprestimos() {
        return emprestimos;
    }

    public BooleanProperty podeEditarProperty() {
        return podeEditar;
    }

    public BooleanProperty desativadoProperty() {
        return desativado;
    }

    public ObjectProperty<EmprestimoRow> selecionProperty() {
        return selecionado;
    }

    public void updateList() {
        emprestimos.clear();
        for (Emprestimo e : emprestimoRepository.listar()) {
            emprestimos.add(new EmprestimoRow(e));
        }
    }

    public void limpar() {
        cpfFuncionarioProperty.setValue("");
        nomeFerramentaProperty.setValue("");
        quantidadeFerramentaProperty.setValue("");
        podeEditar.setValue(true);
        desativado.setValue(true);
    }
}
