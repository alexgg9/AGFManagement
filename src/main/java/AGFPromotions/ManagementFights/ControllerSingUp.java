package AGFPromotions.ManagementFights;


import java.io.IOException;
import java.sql.SQLException;

import AGFPromotions.ManagementFights.model.DAO.MatchmakerDAO;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;
import AGFPromotions.ManagementFights.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Campos vacíos");
	        alert.setContentText("Por favor, complete todos los campos.");
	        alert.showAndWait();
	        return; 
	    }
		
		
		try {
			if(matchmakerDAO.findByDni(dni)!=null) {
			    Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Error");
			    alert.setHeaderText("Dni existente");
			    alert.setContentText("El dni del matchmaker ya está en uso.\nPor favor, elija otro.");
			    alert.showAndWait();
			    return; 
			}
			
		} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Error");
			    alert.setHeaderText("Error en la consulta");
			    alert.setContentText("Ocurrió un error al consultar la existencia del dni.");
			    alert.showAndWait();
			    e.printStackTrace();
			    return;
		}
		
		try {
			if(matchmakerDAO.findByUsername(usuario)!=null) {
			    Alert alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Error");
			    alert.setHeaderText("Usuario existente");
			    alert.setContentText("El nombre de usuario ya está siendo utilizado.\nPor favor, elija otro.");
			    alert.showAndWait();
			    return; 
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Error en la consulta");
		    alert.setContentText("Ocurrió un error al consultar la existencia del usuario.");
		    alert.showAndWait();
		    e.printStackTrace();
		    return;
		}
		
		Matchmaker nMatchmaker = new Matchmaker(dni,nombre,apellidos,promotora,usuario,contraseña);
		
		try {
			matchmakerDAO.save(nMatchmaker);
			Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Registro de Matchmaker");
		    alerta.setHeaderText("Registro exitoso");
		    alerta.setContentText("Se ha registrado el Matchmaker correctamente.");
		    alerta.showAndWait();
		} catch (SQLException e) {
			Alert alerta = new Alert(AlertType.ERROR);
		    alerta.setTitle("Registro de Matchmaker");
		    alerta.setHeaderText("Registro erroneo");
		    alerta.setContentText("No se ha registrado el Matchmaker correctamente.");
		    alerta.showAndWait();
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