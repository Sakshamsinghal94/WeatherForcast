package WeatherForecast;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * A class responsible for fetching weather data from the OpenWeatherMap API.
 * This class uses the OpenWeatherMap API to retrieve weather details, such as
 * weather description and temperature, for a given location.
 */
public class WeatherFetcher {

    private final String apiKey;

    /**
     * Constructor to initialize the WeatherFetcher with an API key.
     *
     * @param apiKey the OpenWeatherMap API key used to authenticate API requests
     */
    public WeatherFetcher(String apiKey) {
        this.apiKey = apiKey;
     }

     /**
      * Fetches the current weather data for a given location.
      * This method sends an HTTP GET request to the OpenWeatherMap API and retrieves
      * the weather information for the specified location.
      * If the request is successful, it returns a string containing the weather description
      * and the current temperature. If the request fails, it returns an error message.
      *
      * @param location the name of the location (city or town) to fetch weather for
      * @return a string containing the weather description and temperature, or an error message
      * @throws Exception if there is an issue with sending the request or processing the response
      */
    public String getWeather(String location) throws Exception {
        // Construct the API URL with the location and API key
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";

        // Create a new HTTP client and build the GET request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))  // Set the URI for the request
                .GET()               // Specify the GET method
                .build();

         // Send the request and get the response as a string
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response status code is OK (200)
        if (response.statusCode() == 200) {
            // Parse the JSON response to extract weather data
            JSONObject jsonResponse = new JSONObject(response.body());
            String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");

            // Return a formatted string with weather details
            return "Weather: " + weatherDescription + ", Temperature: " + temperature + "°C";
        } else {
            // Return an error message if the API request was not successful
            return "Error fetching weather data. Please check the location name.";
        }
    }
}