import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * RESTApiClient: A simple Java application to consume a public REST API,
 * fetch data, and display it in a structured format.
 */
public class RESTApiClient {

    public static void main(String[] args) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=35.6895&longitude=139.6917&current_weather=true"; // Example: Weather data for Tokyo

        try {
            // Make an HTTP GET request
            String response = sendGetRequest(apiUrl);

            // Parse and display the JSON response
            parseAndDisplayResponse(response);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Sends an HTTP GET request to the specified API URL.
     *
     * @param apiUrl The API endpoint URL.
     * @return The response as a string.
     * @throws Exception if an error occurs during the HTTP request.
     */
    private static String sendGetRequest(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    /**
     * Parses the JSON response and displays the data in a structured format.
     *
     * @param response The JSON response string.
     */
    private static void parseAndDisplayResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);

        System.out.println("Weather Data:");
        if (jsonObject.has("current_weather")) {
            JSONObject currentWeather = jsonObject.getJSONObject("current_weather");
            System.out.println("Temperature: " + currentWeather.getDouble("temperature") + "°C");
            System.out.println("Wind Speed: " + currentWeather.getDouble("windspeed") + " km/h");
            System.out.println("Weather Code: " + currentWeather.getInt("weathercode"));
        } else {
            System.out.println("No current weather data available.");
        }
    }
}
