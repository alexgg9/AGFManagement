package AGFPromotions.ManagementFights;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import AGFPromotions.ManagementFights.model.DAO.MatchmakerDAO;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;
import AGFPromotions.ManagementFights.utils.Utils;
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

public class ControllerMatchmaker {

    @FXML
    private TextField tfDni,tfNombre, tfApellidos, tfPromotora, tfUsuario;
    @FXML
    private PasswordField tfPass;
    @FXML
    private TableView<Matchmaker> tableview;
    
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
	public void btAdd() {
		save();
	}
    
    @FXML
	public void btUpdate() {
		save();
	}
    @FXML
	public void btAll() throws SQLException {
		getAll();
	}
    
    @FXML
    private void save() {
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
		
		Matchmaker nMatchmaker = new Matchmaker(dni,nombre,apellidos,promotora,usuario,contraseña);
		try {
			mDAO.save(nMatchmaker);
			Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Matchmaker");
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
        	
        	colApellidos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        
        	colPromotora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPromotora()));
        	
        	colUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario()));
        	
        	
        	
        	tableview.getItems().addAll(matchmakers);
    	}else {
    		tableview.getItems().clear();
    	}
    	
    }
    
    @FXML
    private void deleteSelected() {
        Matchmaker selectedMatchmaker = (Matchmaker) tableview.getSelectionModel().getSelectedItem();
        
        if (selectedMatchmaker != null) {
            tableview.getItems().remove(selectedMatchmaker);

            try {
				mDAO.delete(selectedMatchmaker);
				Alert alerta = new Alert(AlertType.INFORMATION);
			    alerta.setTitle("Eliminar");
			    alerta.setHeaderText("Matchmaker borrado");
			    alerta.setContentText("Se ha eliminado el Matchmaker correctamente.");
			    alerta.showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
        }
    }    
    
    @FXML
    private void initialize() {

        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Matchmaker selectedMatchmaker = (Matchmaker) newSelection;

                tfDni.setText(selectedMatchmaker.getDni());
                tfNombre.setText(selectedMatchmaker.getNombre());
                tfApellidos.setText(selectedMatchmaker.getApellidos());
                tfPromotora.setText(selectedMatchmaker.getPromotora());
                tfUsuario.setText(selectedMatchmaker.getUsuario());
                tableview.getSelectionModel().clearSelection();
                
                int rowIndex = tableview.getItems().indexOf(selectedMatchmaker);
                if (rowIndex >= 0) {
                    tableview.getSelectionModel().clearAndSelect(rowIndex);
                }
            }
        });
    }
 
    @FXML
    private void refresh() {
    	
    	tableview.refresh();
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}