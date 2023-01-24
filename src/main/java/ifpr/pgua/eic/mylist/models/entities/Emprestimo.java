package ifpr.pgua.eic.mylist.models.entities;

import java.time.LocalDateTime;

public class Emprestimo {
    
    private Integer id;
    private Funcionario funcionario;
    private Ferramenta ferramenta;
    private Integer quantidade;
    private LocalDateTime dataHora;
    
    public Emprestimo(Integer id, Funcionario funcionario, Ferramenta ferramenta, Integer quantidade,
            LocalDateTime dataHora) {
        this.id = id;
        this.funcionario = funcionario;
        this.ferramenta = ferramenta;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
    }

    public Emprestimo(Funcionario funcionario, Ferramenta ferramenta, Integer quantidade, LocalDateTime dataHora) {
        this.funcionario = funcionario;
        this.ferramenta = ferramenta;
        this.quantidade = quantidade;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", funcionario=" + funcionario + ", ferramenta=" + ferramenta + ", quantidade="
                + quantidade + ", dataHora=" + dataHora + "]";
    }

}
