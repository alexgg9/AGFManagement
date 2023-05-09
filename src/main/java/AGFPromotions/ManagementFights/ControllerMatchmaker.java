package AGFPromotions.ManagementFights;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.domain.Matchmaker;

public class ControllerMatchmaker {
	
	private Matchmaker matchmaker;
	@FXML
	private TextField tfDni,tfNombre, tfApellidos, tfPromotora, tfUsuario;
	private PasswordField tfPass;
	
	
	
	
	
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }
}