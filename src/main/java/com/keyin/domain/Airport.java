package com.keyin.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an airport in the aviation system
 * An airport belongs to a city and can be related to multiple aircraft
 */
public class Airport
{
    private Long id;
    private String name;
    private String airportCode;

    private City city;

    private List<Aircraft> aircraftList = new ArrayList<>();

    public Airport()
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

    public String getAirportCode()
    {
        return airportCode;
    }

    public void setAirportCode(String airportCode)
    {
        this.airportCode = airportCode;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public List<Aircraft> getAircraftList()
    {
        return aircraftList;
    }

    public void setAircraftList(List<Aircraft> aircraftList)
    {
        this.aircraftList = aircraftList;
    }
}
