package AGFPromotions.ManagementFights;


import java.io.IOException;
import java.sql.SQLException;

import AGFPromotions.ManagementFights.model.DAO.MatchmakerDAO;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;
import AGFPromotions.ManagementFights.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ControllerSingUp {
	
	
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
		
		if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || promotora.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
			
	        Utils.showPopUp("Error", "Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
	    }
		
		
		try {
			if(matchmakerDAO.findByDni(dni)!=null) {
			    
			    Utils.showPopUp("Error", "Dni existente", "El dni del matchmaker ya está en uso.\nPor favor, elija otro.", Alert.AlertType.ERROR);
			}
			
		} catch (SQLException e) {
			
			    Utils.showPopUp("Error", "Error en la consulta", "Ocurrió un error al consultar la existencia del dni.", Alert.AlertType.ERROR);
			    e.printStackTrace();

		}
		
		try {
			if(matchmakerDAO.findByUsername(usuario)!=null) {

			    Utils.showPopUp("Error", "Usuario existente", "El nombre de usuario ya está siendo utilizado.\nPor favor, elija otro.", Alert.AlertType.ERROR);
			}
		} catch (SQLException e) {
			
		    Utils.showPopUp("Error", "Error en la consulta", "Ocurrió un error al consultar la existencia del usuario.", Alert.AlertType.ERROR);
		    e.printStackTrace();
		}
		
		Matchmaker nMatchmaker = new Matchmaker(dni,nombre,apellidos,promotora,usuario,contraseña);
		
		try {
			matchmakerDAO.save(nMatchmaker);
		    Utils.showPopUp("Registro de Matchmaker", "Registro exitoso", "Se ha registrado el Matchmaker correctamente.", Alert.AlertType.INFORMATION);

		} catch (SQLException e) {
		    Utils.showPopUp("Error", "Registro erroneo", "No se ha registrado el Matchmaker correctamente.", Alert.AlertType.ERROR);
			e.printStackTrace();
			
		}
		
		
	}
	
	@FXML
	private void closeSesion() {
	    try {
	        App.setRoot("login");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}