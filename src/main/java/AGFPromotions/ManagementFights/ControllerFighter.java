package AGFPromotions.ManagementFights;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import AGFPromotions.ManagementFights.model.DAO.PeleadorDAO;
import AGFPromotions.ManagementFights.model.domain.Matchmaker;
import AGFPromotions.ManagementFights.model.domain.Peleador;
import AGFPromotions.ManagementFights.model.enums.Background;
import AGFPromotions.ManagementFights.model.enums.Genero;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerFighter {
	
	@FXML
    private TextField tfDni,tfNombre, tfApellidos, tfEdad,tfPeso,tfPais,tfRecord,tfAltura;
	@FXML
	private TableView<Peleador> tvFighters;
	@FXML
    private TableColumn<Peleador, String> colDni;
	@FXML
    private TableColumn<Peleador, String> colNombre;
	@FXML
    private TableColumn<Peleador, String> colApellidos;
	@FXML
    private TableColumn<Peleador, String> colRecord;
	@FXML
    private TableColumn<Peleador, Genero> colGenero;
	@FXML
    private TableColumn<Peleador, Integer> colPeso;
	@FXML
    private TableColumn<Peleador, Integer> colAltura;
	@FXML
    private TableColumn<Peleador, Background> colBackground;
	@FXML
	ComboBox<Genero> cbGenero = new ComboBox<>();
	@FXML
	ComboBox<Background> cbBackground = new ComboBox<>();
	
	private ObservableList<Peleador> peleadores;
	
	PeleadorDAO peleadorDAO = new PeleadorDAO();
	
	
	
	
	 @FXML
	public void btAll() throws SQLException {
		getAll();
	}
	

	@FXML
	public void btUpdate() {
		save();
	}		
	 
	@FXML
	public void btRegistrar() {
		save();
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
	private void save() {

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
		
		if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || pais.isEmpty() || record.isEmpty()) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Campos vacíos");
	        alert.setContentText("Por favor, complete todos los campos.");
	        alert.showAndWait();
	        return;
		}
		
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
	private void getAll() throws SQLException {
		List<Peleador> peleadores = peleadorDAO.findAll();	
		
		if(tvFighters.getItems().isEmpty()) {
		
		colDni.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDni()));
    	
    	colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
    	
    	colApellidos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
    
    	colGenero.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGenero()));
    	
    	colPeso.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPeso()));
    	
    	colAltura.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getAltura()));
    	
    	colRecord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRecord()));
    	
    	colBackground.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBackground()));
    	
    	tvFighters.getItems().addAll(peleadores);
		}else {
			tvFighters.getItems().clear();
		}
	}

	 @FXML
	    private void deleteSelected() {
	        Peleador selectedF = (Peleador) tvFighters.getSelectionModel().getSelectedItem();
	        
	        if (selectedF != null) {
	        	tvFighters.getItems().remove(selectedF);

	            try {
					peleadorDAO.delete(selectedF);
					Alert alerta = new Alert(AlertType.INFORMATION);
				    alerta.setTitle("Eliminar");
				    alerta.setHeaderText("Peleador borrado");
				    alerta.setContentText("Se ha eliminado el peleador correctamente.");
				    alerta.showAndWait();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	            
	        }
	    }    
	
	@FXML
    private void initialize() {
		
		cbBackground.setItems(FXCollections.observableArrayList(Background.values()));
	    cbGenero.setItems(FXCollections.observableArrayList(Genero.values()));
	    
		tvFighters.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Peleador selectedF = (Peleador) newSelection;

                tfDni.setText(selectedF.getDni());
                tfNombre.setText(selectedF.getNombre());
                tfApellidos.setText(selectedF.getApellidos());
                tfEdad.setText(Integer.toString(selectedF.getEdad()));
                tfPeso.setText(Integer.toString(selectedF.getPeso()));
                tfAltura.setText(Integer.toString(selectedF.getAltura()));
                tfPais.setText(selectedF.getPais());
                cbGenero.setValue(selectedF.getGenero());
                tfRecord.setText(selectedF.getRecord());
                cbBackground.setValue(selectedF.getBackground());
                tvFighters.getSelectionModel().clearSelection();
                
                int rowIndex = tvFighters.getItems().indexOf(selectedF);
                if (rowIndex >= 0) {
                	tvFighters.getSelectionModel().clearAndSelect(rowIndex);
                }
            }
        });
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
	
	@FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
	
}