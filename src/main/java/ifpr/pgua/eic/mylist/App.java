package ifpr.pgua.eic.mylist;

import ifpr.pgua.eic.mylist.controllers.TelaPrincipal;
import ifpr.pgua.eic.mylist.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.mylist.utils.Navigator.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public String getHome() {
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        return "MyList";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o)->new TelaPrincipal()));
    }   
}