package ifpr.pgua.eic.mylist.controllers.viewmodels;

import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.mylist.models.entities.Emprestimo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmprestimoRow {
    
    private Emprestimo emprestimo;

    public EmprestimoRow(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public StringProperty funcionarioProperty() {
        return new SimpleStringProperty(emprestimo.getFuncionario().getNome());
    }

    public StringProperty ferramentaProperty() {
        return new SimpleStringProperty(emprestimo.getFerramenta().getNome());
    }

    public StringProperty quantidadeProperty() {
        return new SimpleStringProperty(String.valueOf(emprestimo.getQuantidade()));
    }

    public StringProperty dataEmprestimoProperty() {
        return new SimpleStringProperty(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()));
    }

    public StringProperty dataDevolucaoProperty() {
        return new SimpleStringProperty(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(emprestimo.getDataDevolucao()));
    }
}
