import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {

    private static final String API_KEY = "cebe3d52646d9ead8e3d980f";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient httpClient;
    private final Gson gson;

    public ExchangeRateService() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public double convert(String from, String to, double amount) {
        try {
            String url = BASE_URL + API_KEY + "/latest/" + from;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ExchangeRateResponse rateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

            if ("success".equals(rateResponse.result) && rateResponse.conversion_rates.containsKey(to)) {
                double rate = rateResponse.conversion_rates.get(to);
                return rate * amount;
            } else {
                System.out.println("Error: Tasa de cambio no disponible.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la tasa: " + e.getMessage());
            return -1;
        }
    }
}
