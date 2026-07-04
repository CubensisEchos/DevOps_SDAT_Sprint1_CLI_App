package com.keyin.domain;

import java.util.ArrayList;
import java.util.List;

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

    public Airport(String name, String airportCode, City city)
    {
        this.name = name;
        this.airportCode = airportCode;
        this.city = city;
    }

    public Airport(Long id, String name, String airportCode, City city, List<Aircraft> aircraftList)
    {
        this.id = id;
        this.name = name;
        this.airportCode = airportCode;
        this.city = city;
        this.aircraftList = aircraftList;
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
