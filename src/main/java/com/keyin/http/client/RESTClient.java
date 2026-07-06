package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * RESR client responsible for communicating with the API
 * Sends HTTP requests and turns JSON responses into domain objects
 */
public class RESTClient
{
    private String serverURL;
    private HttpClient client;

    /**
     * Retrieves all cities from the REST API
     * Converts JSON response into a list of city objects
     *
     * @return list of cities
     */
    public List<City> getAllCities()
    {
        List<City> cities = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL + "/cities")).build();

        try
        {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                cities = buildCityListFromResponse(response.body());
            }
            else
            {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

        return cities;
    }

    /**
     * Retrieves all passengers from the REST API
     * Converts JSON response into a list of passenger objects
     *
     * @return list of passengers
     */
    public List<Passenger> getAllPassengers()
    {
        List<Passenger> passengers =  new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL + "/passengers")).build();

        try
        {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                passengers = buildPassengerListFromResponse(response.body());
            }
            else
            {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

        return passengers;
    }

    /**
     * Retrieves all aircraft from the REST API
     * Converts JSON response into a list of aircraft objects
     *
     * @return list of aircraft
     */
    public List<Aircraft> getAllAircraft()
    {
        List<Aircraft> aircraft = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL + "/aircraft")).build();

        try
        {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                aircraft = buildAircraftListFromResponse(response.body());
            }
            else
            {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

        return aircraft;
    }

    /**
     * Converts JSON string into a list of city objects
     *
     * @param response JSON response form API
     * @return list of city objects
     * @throws JsonProcessingException if JSON parsing fails
     */
    public List<City> buildCityListFromResponse(String response) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(response, new TypeReference<List<City>>() {});
    }

    /**
     * Converts JSON string into a list of passenger objects
     *
     * @param response JSON response form API
     * @return list of passenger objects
     * @throws JsonProcessingException if JSON parsing fails
     */
    public List<Passenger> buildPassengerListFromResponse(String response) throws JsonProcessingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readTree(response).get("content").traverse(mapper).readValueAs(new TypeReference<List<Passenger>>() {});
    }

    /**
     * Converts JSON string into a list of aircraft objects
     *
     * @param response JSON response form API
     * @return list of aircraft objects
     * @throws JsonProcessingException if JSON parsing fails
     */
    public List<Aircraft> buildAircraftListFromResponse(String response) throws JsonProcessingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readTree(response).get("content").traverse(mapper).readValueAs(new TypeReference<List<Aircraft>>() {});
    }

    /**
     * Converts JSON string into a list of airport objects
     *
     * @param response JSON response form API
     * @return list of airport objects
     * @throws JsonProcessingException if JSON parsing fails
     */
    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(response, new TypeReference<List<Airport>>() {});
    }


    public void setServerURL(String serverURL)
    {
        this.serverURL = serverURL;
    }

    public HttpClient getClient()
    {
        if (client == null)
        {
            client = HttpClient.newHttpClient();
        }

        return client;
    }
}
