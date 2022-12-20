import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SnowFallingAppController implements Initializable {
    @FXML
    private AnchorPane rootAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SnowFallingEffect.getSeasonalDecorations(rootAnchorPane);
//        SnowFallingEffect.stopSeasonalAnimation();
    }
}
