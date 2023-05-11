package AGFPromotions.ManagementFights;


import java.io.IOException;
import java.sql.SQLException;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import model.DAO.MatchmakerDAO;
import model.domain.Matchmaker;
import utils.Utils;


public class ControllerMatchmaker {
	
	
	@FXML
	private TextField tfDni,tfNombre, tfApellidos, tfPromotora, tfUsuario;
	@FXML
	private PasswordField tfPass;

	
	
	MatchmakerDAO matchmakerDAO = new MatchmakerDAO();
	
	@FXML
	public void btRegistrar() {
		addMatchmaker();
	}
	
	@FXML
	private void addMatchmaker() {
		App a = new App();
		String dni = tfDni.getText();
		String nombre = tfNombre.getText();
		String apellidos = tfApellidos.getText();
		String promotora = tfPromotora.getText();
		String usuario = tfUsuario.getText();
		String contraseña = tfPass.getText();
		contraseña = Utils.encryptSHA256(contraseña);
		
		Matchmaker nMatchmaker = new Matchmaker(dni,nombre,apellidos,promotora,usuario,contraseña);
		try {
			matchmakerDAO.save(nMatchmaker);
			Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Registro de Matchmaker");
		    alerta.setHeaderText("Registro exitoso");
		    alerta.setContentText("Se ha registrado el Matchmaker correctamente.");
		    alerta.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }
}