package ifpr.pgua.eic.mylist.models.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Emprestimo {
    
    private Integer id;
    private Funcionario funcionario;
    private List<ItemEmprestimo> itens;
    private LocalDateTime dataHora;
    
    public Emprestimo(Integer id, Funcionario funcionario, List<ItemEmprestimo> itens, LocalDateTime dataHora) {
        this.id = id;
        this.funcionario = funcionario;
        this.itens = itens;
        this.dataHora = dataHora;
    }

    public Emprestimo(Funcionario funcionario, List<ItemEmprestimo> itens, LocalDateTime dataHora) {
        this.funcionario = funcionario;
        this.itens = itens;
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemEmprestimo> getItens() {
        return itens;
    }

    public void setItens(List<ItemEmprestimo> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", funcionario=" + funcionario + ", itens=" + itens + ", dataHora=" + dataHora
                + "]";
    }
    
}