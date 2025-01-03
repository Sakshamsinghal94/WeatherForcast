package WeatherForecast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages the storage and retrieval of location data in a file.
 * This class allows saving locations to a file and retrieving saved locations.
 */
public class LocationManager {

    private final Path filePath;

    /**
     * Constructor that initializes the LocationManager with a file path.
     * If the specified file does not exist, it will be created.
     *
     * @param fileName the name of the file to store the locations
     */
    public LocationManager(String fileName) {
        this.filePath = Path.of(fileName);
        try {
            // If the file does not exist, create a new one
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error initializing location file: " + e.getMessage());
        }
    }

    /**
     * Saves a location to the file.
     * The location is appended to the file on a new line.
     *
     * @param location the location to be saved (e.g., city name)
     */
    public void saveLocation(String location) {
        try {
            // Append the location to the file with a newline separator
            Files.writeString(filePath, location + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error saving location: " + e.getMessage());
        }
    }

    /**
     * Retrieves all saved locations from the file.
     * Each location is returned as a string in a list.
     *
     * @return a list of saved locations
     */
    public List<String> getLocations() {
        try {
            // Read all lines from the file and return them as a list
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Error reading locations: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
