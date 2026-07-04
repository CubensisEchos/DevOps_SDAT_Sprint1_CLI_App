package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.client.RESTClient;

import java.util.List;

public class CliApplication
{
    private RESTClient restClient;

    public void airportsInSpecificCityReport()
    {
        List<City> cities = getRestClient().getAllCities();

        StringBuilder report = new StringBuilder();

        for (City city : cities) {
            report.append("City: ");
            report.append(city.getName());
            report.append(", ");
            report.append(city.getState());
            report.append("\n");

            appendAirports(report, city.getAirports());

            report.append("\n");
        }

        System.out.println(report.toString());
    }

    public void passengersFlownOnSpecificAircraftReport ()
    {
        List<Aircraft> aircraftList = getRestClient().getAllAircraft();

        StringBuilder report = new StringBuilder();

        for (Aircraft aircraft : aircraftList)
        {
            report.append("Aircraft: ");
            report.append(aircraft.getType());
            report.append(" (");
            report.append(aircraft.getAirlineName());
            report.append(")\n");

            if (aircraft.getPassengers() != null && !aircraft.getPassengers().isEmpty())
            {
                for (Passenger passenger : aircraft.getPassengers())
                {
                    report.append("  - ");
                    report.append(passenger.getFirstName());
                    report.append(" ");
                    report.append(passenger.getLastName());
                    report.append("\n");
                }
            }
            else
            {
                report.append(" No passengers found\n");
            }
            report.append("\n");
        }

        System.out.println(report.toString());
    }

    public void airportsUsedBySpecificAircraftReport ()
    {
        List<Aircraft> aircraftList = getRestClient().getAllAircraft();

        StringBuilder report = new StringBuilder();

        for (Aircraft aircraft : aircraftList)
        {
            report.append("Aircraft: ");
            report.append(aircraft.getType());
            report.append(" (");
            report.append(aircraft.getAirlineName());
            report.append(")\n");

            appendAirports(report, aircraft.getAirports());

            report.append("\n");
        }

        System.out.println(report.toString());

    }

    public void passengersTravelledFromSpecificAirportReport ()
    {
        List<Passenger> passengers = getRestClient().getAllPassengers();
        List<Aircraft> aircraftList = getRestClient().getAllAircraft();

        StringBuilder report = new StringBuilder();

        for (Passenger passenger : passengers)
        {
            report.append("Passenger: ");
            report.append(passenger.getFirstName());
            report.append(" ");
            report.append(passenger.getLastName());
            report.append("\n");

            report.append("Aircraft: \n");

            for (Aircraft aircraft : aircraftList)
            {
                if (aircraft.getPassengers() != null && aircraft.getPassengers().stream().anyMatch(aircraftPassenger -> aircraftPassenger.getId().equals(passenger.getId())))
                {
                    report.append("    ");
                    report.append(aircraft.getType());
                    report.append("\n");

                    report.append("  Airports Used:\n");

                    if (aircraft.getAirports() != null && !aircraft.getAirports().isEmpty())
                    {
                        for (Airport airport : aircraft.getAirports())
                        {
                            report.append("    - ");
                            report.append(airport.getName());
                            report.append(" (");
                            report.append(airport.getAirportCode());
                            report.append(")\n");
                        }
                    }
                    else
                    {
                        report.append(" No airports found\n");
                    }
                }
            }

            report.append("\n");
        }

        System.out.println(report.toString());
    }

    private void appendAirports(StringBuilder report, List<Airport> airports)
    {
        if (airports != null && !airports.isEmpty())
        {
            for (Airport airport : airports)
            {
                report.append("  - ");
                report.append(airport.getName());
                report.append(" (");
                report.append(airport.getAirportCode());
                report.append(")\n");
            }
        }
        else
        {
            report.append(" No airports found\n");
        }
    }

    public RESTClient getRestClient ()
    {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }


    public void setRestClient (RESTClient restClient)
    {
        this.restClient = restClient;
    }

}