package http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.cli.CliApplication;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CliApplicationTest
{
    @Mock
    private RESTClient mockRESTClient;

    private String captureOutput(Runnable method)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        method.run();

        System.setOut(original);
        return out.toString();
    }

    @Test
    public void testAirportsInSpecificCityReport()
    {
        CliApplication cliApp = new CliApplication();
        cliApp.setRestClient(mockRESTClient);

        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Metro Airport");
        airport.setAirportCode("MIA");

        City city = new City();
        city.setName("Metro City");
        city.setState("MC");

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);
        city.setAirports(airports);

        List<City> cities = new ArrayList<>();
        cities.add(city);

        Mockito.when(mockRESTClient.getAllCities()).thenReturn(cities);
        String output = captureOutput(cliApp::airportsInSpecificCityReport);

        Assertions.assertFalse(output.isBlank());
        Assertions.assertTrue(output.contains("Metro Airport"));
        Assertions.assertTrue(output.contains("MIA"));
        Assertions.assertTrue(output.contains("Metro City"));
        Assertions.assertTrue(output.contains("MC"));
    }

    @Test
    public void testAirportsInSpecificCityReportWithError()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Mockito.when(mockRESTClient.getAllCities()).thenReturn(new ArrayList<>());
        String output = captureOutput(app::airportsInSpecificCityReport);

        Assertions.assertNotNull(output);
    }

    @Test
    public void testPassengersFlownOnSpecificAircraftReport()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        passenger.setLastName("Smith");

        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 737");
        aircraft.setAirlineName("Airline");

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        aircraft.setPassengers(passengers);

        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(aircraftList);
        String output = captureOutput(app::passengersFlownOnSpecificAircraftReport);

        Assertions.assertFalse(output.isBlank());
        Assertions.assertTrue(output.contains("John"));
        Assertions.assertTrue(output.contains("Boeing 737"));
        Assertions.assertTrue(output.contains("Airline"));
        Assertions.assertFalse(output.contains("Null"));
    }

    @Test
    public void testPassengersFlownOnSpecificAircraftReportWithError()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(new ArrayList<>());
        String output = captureOutput(app::passengersFlownOnSpecificAircraftReport);

        Assertions.assertNotNull(output);
    }

    @Test
    public void testAirportsUsedBySpecificAircraftReport()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Airport airport = new Airport();
        airport.setName("Metro Airport");
        airport.setAirportCode("MIA");

        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 737");

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);
        aircraft.setAirports(airports);

        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(aircraftList);
        String output = captureOutput(app::airportsUsedBySpecificAircraftReport);

        Assertions.assertFalse(output.isBlank());
        Assertions.assertTrue(output.contains("Boeing 737"));
        Assertions.assertTrue(output.contains("Metro Airport"));
        Assertions.assertTrue(output.contains("MIA"));
    }

    @Test
    public void testAirportsUsedBySpecificAircraftReportWithError()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(new ArrayList<>());
        String output = captureOutput(app::airportsUsedBySpecificAircraftReport);

        Assertions.assertNotNull(output);
    }

    @Test
    public void testPassengersTravelledFromSpecificAirportReport()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Smith");

        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 737");

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        aircraft.setPassengers(passengers);

        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger);

        Mockito.when(mockRESTClient.getAllPassengers()).thenReturn(passengerList);
        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(aircraftList);
        String output = captureOutput(app::passengersTravelledFromSpecificAirportReport);

        Assertions.assertFalse(output.isBlank());
        Assertions.assertTrue(output.contains("John"));
        Assertions.assertTrue(output.contains("Smith"));
        Assertions.assertTrue(output.contains("Boeing 737"));
    }

    @Test
    public void testPassengersTravelledFromSpecificAirportReportWithError()
    {
        CliApplication app = new CliApplication();
        app.setRestClient(mockRESTClient);

        Mockito.when(mockRESTClient.getAllPassengers()).thenReturn(new ArrayList<>());
        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(new ArrayList<>());
        String output = captureOutput(app::passengersTravelledFromSpecificAirportReport);

        Assertions.assertNotNull(output);
    }
}
