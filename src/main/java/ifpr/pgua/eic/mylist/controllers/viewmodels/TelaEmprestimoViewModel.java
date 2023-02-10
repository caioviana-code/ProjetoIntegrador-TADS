package ifpr.pgua.eic.mylist.controllers.viewmodels;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
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
    
    private StringProperty funcionarioProperty = new SimpleStringProperty();
    private StringProperty ferramentaProperty = new SimpleStringProperty();
    private StringProperty quantidadeProperty = new SimpleStringProperty();

    private ObservableList<EmprestimoRow> emprestimos = FXCollections.observableArrayList();

    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private BooleanProperty desativado = new SimpleBooleanProperty(true);

    private ObjectProperty<EmprestimoRow> selecionado = new SimpleObjectProperty<>();

    private EmprestimoRepository emprestimoRepository;
    private FuncionarioRepository funcionarioRepository;
    private FerramentaRepository ferramentaRepository;

    public TelaEmprestimoViewModel(EmprestimoRepository emprestimoRepository, FuncionarioRepository funcionarioRepository, FerramentaRepository ferramentaRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.ferramentaRepository = ferramentaRepository;
    }

    public StringProperty funcionarioProperty() {
        return funcionarioProperty;
    }

    public StringProperty ferramentaProperty() {
        return ferramentaProperty;
    }

    public StringProperty quantidadeProperty() {
        return quantidadeProperty;
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

    public ObjectProperty<EmprestimoRow> selecionadoProperty() {
        return selecionado;
    }

    public void cadastrar() {
        Funcionario funcionario = funcionarioRepository.getFuncionarioByCpf(funcionarioProperty.getValue());
        Ferramenta ferramenta = ferramentaRepository.getFerramentaByNome(ferramentaProperty.getValue());
        int quantidade = Integer.valueOf(quantidadeProperty.getValue());
        LocalDateTime dataEmprestimo = LocalDateTime.now();
        LocalDateTime dataDevolucao = null;
        int status = 1;

        emprestimoRepository.adicionarEmprestimo(funcionario, ferramenta, quantidade, dataEmprestimo, dataDevolucao, status);
    }
}
