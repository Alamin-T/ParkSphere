package full.stack.parkspring.config;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Custom FXMLLoader to integrate Spring's dependency injection into JavaFX controllers.
 */
@Component
public class SpringFXMLLoader {

    private final ApplicationContext context;

    @Autowired
    public SpringFXMLLoader(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Loads an FXML file and sets its controller to be Spring-managed.
     *
     * @param fxmlPath the path to the FXML file.
     * @return the FXMLLoader instance.
     */
    public FXMLLoader load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        try (InputStream fxmlStream = getClass().getResourceAsStream(fxmlPath)) {
            loader.load(fxmlStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file: " + fxmlPath, e);
        }
        return loader;
    }
}
