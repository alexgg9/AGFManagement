package AGFPromotions.ManagementFights;


import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.domain.Matchmaker;
import utils.Utils;
import model.DAO.MatchmakerDAO;

public class ControllerMatchmaker {

    @FXML
    private TextField tfDni,tfNombre, tfApellidos, tfPromotora, tfUsuario;
    @FXML
    private PasswordField tfPass;
    @FXML
    private TableView tableview;
    
    @FXML
    private TableColumn<Matchmaker, String> colDni;

    @FXML
    private TableColumn<Matchmaker, String> colNombre;

    @FXML
    private TableColumn<Matchmaker, String> colApellidos;

    @FXML
    private TableColumn<Matchmaker, String> colPromotora;

    @FXML
    private TableColumn<Matchmaker, String> colUsuario;
    
    MatchmakerDAO mDAO = new MatchmakerDAO();
    
    private ObservableList<Matchmaker> matchmakers;
    
    @FXML
	public void btUpdate() {
		update();
	}
    @FXML
	public void btAll() throws SQLException {
		getAll();
	}
    
    @FXML
    private void update() {
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
			mDAO.save(nMatchmaker);
			Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Actualización");
		    alerta.setHeaderText("Actualización exitosa");
		    alerta.setContentText("Se ha actualizado el Matchmaker correctamente.");
		    alerta.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
    }
    
    @FXML
    private void getAll() throws SQLException {
    	List<Matchmaker> matchmakers = mDAO.findAll();
    	if(tableview.getItems().isEmpty()) {
    		
        	colDni.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDni()));
        	
   
        	colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        	
        	colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        	

        	colPromotora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPromotora()));
        	

        	colUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario()));
        	
        	tableview.getColumns().addAll(colNombre, colNombre,colPromotora,colUsuario);
    	}else {
    		tableview.getItems().clear();
    	}
    	
    }
}