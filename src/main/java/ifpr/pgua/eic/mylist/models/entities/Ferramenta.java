package ifpr.pgua.eic.mylist.models.entities;

public class Ferramenta {
    
    private Integer id;
    private String nome;
    private Integer estoque;

    public Ferramenta(Integer id, String nome, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
    }

    public Ferramenta(String nome, Integer estoque) {
        this.nome = nome;
        this.estoque = estoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Ferramenta [id=" + id + ", nome=" + nome + ", estoque=" + estoque + "]";
    }

}
