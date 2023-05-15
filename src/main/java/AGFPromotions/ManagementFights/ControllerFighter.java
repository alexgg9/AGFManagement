package AGFPromotions.ManagementFights;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.domain.Peleador;
import model.enums.Genero;
import model.enums.Background;
import model.DAO.PeleadorDAO;

public class ControllerFighter {
	
	@FXML
    private TextField tfDni,tfNombre, tfApellidos, tfEdad,tfPeso,tfPais,tfRecord,tfAltura;
	@FXML
	private TableView tvFighters;
	
	@FXML
	ComboBox<Genero> cbGenero = new ComboBox<>();
	@FXML
	ComboBox<Background> cbBackground = new ComboBox<>();
	
	PeleadorDAO peleadorDAO = new PeleadorDAO();
	
	
	@FXML
	private void initialize() {
		cbBackground.setItems(FXCollections.observableArrayList(Background.values()));
	    cbGenero.setItems(FXCollections.observableArrayList(Genero.values()));

	}
	
	@FXML
	public void btRegistrar() {
		addFighter();
	}
	
	@FXML
	public void btEliminar() {
		try {
			deleteFighter();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void addFighter() {
		App a = new App();
		String dni = tfDni.getText();
		String nombre = tfNombre.getText();
		String apellidos = tfApellidos.getText();
		int edad = Integer.parseInt(tfEdad.getText());
		int peso = Integer.parseInt(tfPeso.getText());
		int altura = Integer.parseInt(tfAltura.getText());
		String record = tfRecord.getText();
		String pais = tfPais.getText();
		Genero genero = cbGenero.getValue();
		Background background = cbBackground.getValue();
		
		Peleador nPeleador = new Peleador(dni,nombre,apellidos,edad,genero,peso,altura,record,pais,background);
		try {
			peleadorDAO.save(nPeleador);
			Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Registro de Peleador");
		    alerta.setHeaderText("Registro exitoso");
		    alerta.setContentText("Se ha registrado el peleador correctamente.");
		    alerta.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	@FXML
	private void deleteFighter() throws SQLException{
		
		App a = new App();
		String dni = tfDni.getText();
		Peleador p = peleadorDAO.findByDni(dni);

		try {
			peleadorDAO.delete(p);
			Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Eliminar Peleador");
		    alerta.setHeaderText("Eliminado exitosamente");
		    alerta.setContentText("Se ha registrado el peleador correctamente.");
		    alerta.showAndWait();
		} catch (Exception e) {
			
		}	
			
		
		
		
		
	}
	
	
}