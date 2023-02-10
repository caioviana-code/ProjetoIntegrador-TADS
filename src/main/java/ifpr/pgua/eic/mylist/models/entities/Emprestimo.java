package ifpr.pgua.eic.mylist.models.entities;

import java.time.LocalDateTime;

public class Emprestimo {
    
    private Integer id;
    private Funcionario funcionario;
    private Ferramenta ferramenta;
    private Integer quantidade;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private Integer status;
    
    public Emprestimo(Integer id, Funcionario funcionario, Ferramenta ferramenta,
            Integer quantidade, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, Integer status) {
        this.id = id;
        this.funcionario = funcionario;
        this.ferramenta = ferramenta;
        this.quantidade = quantidade;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Emprestimo(Funcionario funcionario, Ferramenta ferramenta, Integer quantidade,
            LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, Integer status) {
        this.funcionario = funcionario;
        this.ferramenta = ferramenta;
        this.quantidade = quantidade;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
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

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", funcionario=" + funcionario + ", ferramenta=" + ferramenta + ", quantidade="
                + quantidade + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + ", status="
                + status + "]";
    }

}