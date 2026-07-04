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


public class RESTClient
{
    private String serverURL;
    private HttpClient client;

    public String getResponseFromHTTPRequest()
    {
        String responseBody = "";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try
        {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200)
            {
                System.out.println("Status Code " + response.statusCode());
            }

            responseBody = response.body();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        return responseBody;
    }

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

    public List<Airport> getAllAirports()
    {
        List<Airport> airports = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL + "/airports")).build();

        try
        {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                airports = buildAirportListFromResponse(response.body());
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

        return airports;
    }

    public List<City> buildCityListFromResponse(String response) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(response, new TypeReference<List<City>>() {});
    }

    public List<Passenger> buildPassengerListFromResponse(String response) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(response, new TypeReference<List<Passenger>>() {});
    }

    public List<Aircraft> buildAircraftListFromResponse(String response) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(response, new TypeReference<List<Aircraft>>() {});
    }

    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(response, new TypeReference<List<Airport>>() {});
    }

    public String getServerURL()
    {
        return serverURL;
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
