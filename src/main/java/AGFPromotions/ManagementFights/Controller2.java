package AGFPromotions.ManagementFights;

import java.io.IOException;
import javafx.fxml.FXML;

public class Controller2 {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }
}