package currentWeather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class WeatherFromWeb {
    BufferedReader br;
    String line;
    StringBuilder sb;
    final Double KELVIN = 273.15;

    JSONObject obj;

    {
        try {
            obj = new JSONObject(weatherInJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: returns the entire JSON file in a string
    private String weatherInJSON() {
        try {
            String apikey = "&APPID=5300feba6621be0264b8d1c9c8bac3f0";
            String vancouverWeatherQuery = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,can";

            String theURL = vancouverWeatherQuery + apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));


            sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public String getLocation() throws JSONException {
        return obj.getString("name");
    }

    //EFFECTS: returns current temperature
    public int weatherHighestTemp() throws JSONException {
        Double maxTempKelvin = obj.getJSONObject("main").getDouble("temp_max");
        return kelvinToCelsius(maxTempKelvin).intValue();
    }

    //EFFECTS: returns minimum temperature
    public int weatherLowestTemp() throws JSONException {
        Double minTempKelvin = obj.getJSONObject("main").getDouble("temp_min");
        return kelvinToCelsius(minTempKelvin).intValue();
    }

    //EFFECTS: returns maximum temperature
    public int weatherCurrentTemp() throws JSONException {
        Double curTempKelvin = obj.getJSONObject("main").getDouble("temp");
        return kelvinToCelsius(curTempKelvin).intValue();
    }

    private Double kelvinToCelsius(Double temp) throws JSONException {
        return temp - KELVIN;
    }

    public String weatherDescription() throws JSONException {
        String description = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        return description;
    }
//public String getLocation() {
//        return "Vancouver";
//    }
//
//    //EFFECTS: returns current temperature
//    public int weatherHighestTemp() throws JSONException {
//        return 1;
//    }
//
//    //EFFECTS: returns minimum temperature
//    public int weatherLowestTemp() {
//        return 1;
//    }
//
//    //EFFECTS: returns maximum temperature
//    public int weatherCurrentTemp() {
//        return 1;
//    }

}