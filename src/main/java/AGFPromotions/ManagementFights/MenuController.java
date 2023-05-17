package AGFPromotions.ManagementFights;

import java.io.IOException;

import javafx.fxml.FXML;

public class MenuController {
	
	@FXML
    private void switchToFighterPage() throws IOException {
        App.setRoot("fighterPage");
    }
	
	@FXML
    private void switchToMatchmakerPage() throws IOException {
        App.setRoot("matchmakerPage");
    }
	
	@FXML
    private void switchToEventPage() throws IOException {
        App.setRoot("eventPage");
    }
	
	@FXML
    private void signOut() throws IOException {
        App.setRoot("login");
    }
	
}
