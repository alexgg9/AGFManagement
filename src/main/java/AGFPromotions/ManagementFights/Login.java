package AGFPromotions.ManagementFights;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import model.DAO.MatchmakerDAO;
import utils.Utils;

public class Login {
	
	
	@FXML
	private TextField tfUser;
	@FXML
	private PasswordField tfPass;
	
	
	@FXML
	private void login() throws SQLException, IOException {
		
		String usuario = tfUser.getText().trim();
		String contraseña = tfPass.getText().trim();
		contraseña = Utils.encryptSHA256(contraseña);
		
		if(usuario.equals("") || contraseña.equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Falta algun campo");
			alert.showAndWait();
		}else {
			MatchmakerDAO mDAO = new MatchmakerDAO();
			if(mDAO.checkLogin(usuario, contraseña)) {
				Alert alerta = new Alert(AlertType.INFORMATION);
			    alerta.setTitle("Login");
			    alerta.setHeaderText("Login exitoso");
			    alerta.setContentText("Se ha logeago el Matchmaker correctamente.");
			    alerta.showAndWait();
			    switchToUserPage();
			}else {
				Alert alerta = new Alert(AlertType.INFORMATION);
			    alerta.setTitle("Login");
			    alerta.setHeaderText("No se ha podido logear");
			    alerta.setContentText("Intentelo de nuevo.");
			    alerta.showAndWait();
			}
			
		}
		
	}
	
	
	@FXML
    private void switchToUserPage() throws IOException {
        App.setRoot("matchmakerPage");
    }
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
}
