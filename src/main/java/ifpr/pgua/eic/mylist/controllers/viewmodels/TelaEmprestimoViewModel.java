package ifpr.pgua.eic.mylist.controllers.viewmodels;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import ifpr.pgua.eic.mylist.models.entities.Funcionario;
import ifpr.pgua.eic.mylist.models.repositories.EmprestimoRepository;
import ifpr.pgua.eic.mylist.models.repositories.FerramentaRepository;
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

public class TelaEmprestimoViewModel {
    
    private StringProperty funcionarioProperty = new SimpleStringProperty();
    private StringProperty ferramentaProperty = new SimpleStringProperty();
    private StringProperty quantidadeProperty = new SimpleStringProperty();

    private ObservableList<EmprestimoRow> emprestimos = FXCollections.observableArrayList();

    private BooleanProperty desativado = new SimpleBooleanProperty(true);

    private ObjectProperty<EmprestimoRow> selecionado = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

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

    public BooleanProperty desativadoProperty() {
        return desativado;
    }

    public ObjectProperty<EmprestimoRow> selecionadoProperty() {
        return selecionado;
    }

    public ObjectProperty<Result> alertProperty() {
        return alertProperty;
    }

    public void updateList() {
        emprestimos.clear();
        for (Emprestimo e : emprestimoRepository.getEmprestimos()) {
            emprestimos.add(new EmprestimoRow(e));
        }
    }

    public Result cadastrar() {

        Funcionario funcionario = funcionarioRepository.getFuncionarioByCpf(funcionarioProperty.getValue());
        Ferramenta ferramenta = ferramentaRepository.getFerramentaByNome(ferramentaProperty.getValue());
        int quantidade = Integer.valueOf(quantidadeProperty.getValue());
        LocalDateTime dataEmprestimo = LocalDateTime.now();
        LocalDateTime dataDevolucao = null;
        int status = 1;

        if (funcionario == null) {
            alertProperty.setValue(Result.fail("Funcionário não encontrado"));
            return null;
        }
        if (ferramenta == null) {
            alertProperty.setValue(Result.fail("Ferramenta não encontrada"));
            return null;
        }
        if (quantidade > ferramenta.getEstoque()) {
            alertProperty.setValue(Result.fail("Não há a quantidade da ferramenta no estoque!"));
            return null;
        }

        emprestimoRepository.adicionarEmprestimo(funcionario, ferramenta, quantidade, dataEmprestimo, dataDevolucao, status);

        updateList(); 
        limpar();

        alertProperty.setValue(Result.success("Empréstimo realizado!"));
        return null;
    } 

    public Result devolver() {
        try {
            Emprestimo emprestimo = selecionado.get().getEmprestimo();
            emprestimoRepository.devolverEmprestimo(emprestimo);
            updateList();
            limpar();
            alertProperty.setValue(Result.success("Empréstimo devolvido!"));
            return null;
        } catch (NullPointerException err) {
            alertProperty.setValue(Result.success("Nenhum empréstimo selecionado"));
            return null;
        }

    }

    public void atualizar() {
        desativado.setValue(false);
    }

    public void limpar() {
        funcionarioProperty.setValue("");
        ferramentaProperty.setValue("");
        quantidadeProperty.set("");
        desativado.setValue(true);
    }

}
