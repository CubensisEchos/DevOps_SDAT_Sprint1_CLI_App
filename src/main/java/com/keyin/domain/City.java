package com.keyin.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a city in the aviation system
 * A city can have multiple airports and passengers
 */
public class City
{
    private Long id;
    private String name;
    private String state;
    private int population;

    private List<Airport> airports = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();

    public City()
    {

    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public int getPopulation()
    {
        return population;
    }

    public void setPopulation(int population)
    {
        this.population = population;
    }

    public List<Airport> getAirports()
    {
        return airports;
    }

    public void setAirports(List<Airport> airports)
    {
        this.airports = airports;
    }

    public List<Passenger> getPassengers()
    {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers)
    {
        this.passengers = passengers;
    }
}
