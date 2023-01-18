package ifpr.pgua.eic.mylist.controllers.viewmodels;

import ifpr.pgua.eic.mylist.models.entities.Ferramenta;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FerramentaRow {
    
    private Ferramenta ferramenta;

    public FerramentaRow(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }

    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    public StringProperty nomeProperty() {
        return new SimpleStringProperty(ferramenta.getNome());
    }

    public StringProperty estoqueProperty() {
        return new SimpleStringProperty(String.valueOf(ferramenta.getEstoque()));
    }
}
