package WeatherForecast;

import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Weather Forecast Application.
 * This class provides a command-line interface for managing weather data for multiple locations.
 * The user can fetch and save weather information for different locations and view saved weather data.
 */
public class WeatherForecastApp {

    /**
     * Main method to run the Weather Forecast Application.
     * Provides a menu-driven interface where users can add locations, view weather data for saved locations, and exit the application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "437f62e0802675d23a62bd3229b0ede0";// your API key
        WeatherFetcher weatherFetcher = new WeatherFetcher(apiKey); // Weather data fetcher
        LocationManager locationManager = new LocationManager("favorite_locations.txt"); // Manages saved locations

        // Main loop for the menu interface
        while (true) {
            System.out.println("\nWeather Application");
            System.out.println("1. Add Location and Fetch Weather");
            System.out.println("2. View Weather for Saved Locations");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Switch case to perform corresponding actions based on user input
            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    String location = scanner.nextLine();
                    try {
                        String weather = weatherFetcher.getWeather(location);
                        System.out.println("Weather Data: " + weather);
                        System.out.print("Do you want to save this location? (yes/no): ");
                        String saveChoice = scanner.nextLine();
                        if (saveChoice.equalsIgnoreCase("yes")) {
                            locationManager.saveLocation(location);
                            System.out.println("Location saved.");
                        }
                      }
                    catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<String> locations = locationManager.getLocations();
                    if (locations.isEmpty()) {
                        System.out.println("No saved locations found.");
                        }
                    else {
                        System.out.println("Saved Locations and Weather Data:");
                         for (String savedLocation : locations) {
                            try {
                                String weather = weatherFetcher.getWeather(savedLocation);
                                System.out.println(savedLocation + ": " + weather);
                                 }
                            catch (Exception e) {
                                System.out.println("Error fetching weather for " + savedLocation + ": " + e.getMessage());
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

