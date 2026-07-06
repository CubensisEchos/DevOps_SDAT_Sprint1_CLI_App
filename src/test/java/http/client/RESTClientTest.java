package http.client;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@SuppressWarnings({"SequencedCollectionMethodCanBeUsed", "TextBlockMigration"})
public class RESTClientTest
{
    @Test
    public void testBuildAirportListFromResponse() throws Exception
    {
        String jsonResponse =
                "[\n" + "{\n" + "\"id\": 1,\n" + "\"name\": \"Metro International Airport\",\n" + "\"airportCode\": \"MIA\"\n" +
                "},\n" + "{\n" + "\"id\": 2,\n" + "\"name\": \"Regional City Airport\",\n" + "\"airportCode\": \"RCA\"\n" + "}\n" + "]";

        RESTClient restClientUnderTest = new RESTClient();

        List<Airport> airports = restClientUnderTest.buildAirportListFromResponse(jsonResponse);

        Assertions.assertEquals(2, airports.size());
        Assertions.assertTrue(airports.stream().anyMatch(a -> "MIA".equals(a.getAirportCode())));
        Assertions.assertTrue(airports.stream().anyMatch(a -> "RCA".equals(a.getAirportCode())));
        Assertions.assertEquals(1, airports.get(0).getId());
        Assertions.assertEquals(2, airports.get(1).getId());
        Assertions.assertEquals("Metro International Airport", airports.get(0).getName());
        Assertions.assertEquals("Regional City Airport", airports.get(1).getName());
        Assertions.assertNotNull(airports);
        Assertions.assertFalse(airports.isEmpty());
    }

    @Test
    public void testBuildCityListFromResponse() throws Exception
    {
        String jsonResponse =
                "[\n" + "{\n" + "\"id\": 1,\n" + "\"name\": \"New York\",\n" + "\"state\": \"NY\",\n" +
                "\"population\": 8000000,\n" + "\"airports\": []\n" + "}\n" + "]";

        RESTClient restClientUnderTest = new RESTClient();

        List<City> cities = restClientUnderTest.buildCityListFromResponse(jsonResponse);

        Assertions.assertEquals(1, cities.size());
        Assertions.assertEquals(1, cities.get(0).getId());
        Assertions.assertEquals("New York", cities.get(0).getName());
        Assertions.assertEquals("NY", cities.get(0).getState());
        Assertions.assertEquals(8000000, cities.get(0).getPopulation());
        Assertions.assertNotNull(cities);
        Assertions.assertFalse(cities.isEmpty());
    }


    @Test
    public void testBuildPassengerListFromResponse() throws Exception
    {
        String jsonResponse =
                "{\n" + "\"content\": [\n" + "{\n" + "\"id\": 1,\n" + "\"firstName\": \"John\",\n" + "\"lastName\": \"Smith\",\n" +
                "\"phoneNumber\": \"555-1234\"\n" + "}\n" + "]\n" + "}";

        RESTClient restClientUnderTest = new RESTClient();

        List<Passenger> passengers = restClientUnderTest.buildPassengerListFromResponse(jsonResponse);

        Assertions.assertEquals(1, passengers.size());
        //noinspection SequencedCollectionMethodCanBeUsed
        Assertions.assertEquals(1, passengers.get(0).getId());
        Assertions.assertEquals("John", passengers.get(0).getFirstName());
        Assertions.assertEquals("Smith", passengers.get(0).getLastName());
        Assertions.assertEquals("555-1234", passengers.get(0).getPhoneNumber());
        Assertions.assertNotNull(passengers);
        Assertions.assertFalse(passengers.isEmpty());
    }

    @Test
    public void testBuildAircraftListFromResponse() throws Exception
    {
        String jsonResponse =
                "{\n" + "\"content\": [\n" + "{\n" + "\"id\": 1,\n" + "\"type\": \"Boeing 737\",\n" +
                "\"airlineName\": \"Example Airlines\",\n" + "\"numberOfPassengers\": 180,\n" + "\"passengers\": []\n" + "}\n" + "]\n" + "}";

        RESTClient restClientUnderTest = new RESTClient();

        List<Aircraft> aircraft = restClientUnderTest.buildAircraftListFromResponse(jsonResponse);

        Assertions.assertEquals(1, aircraft.size());
        Assertions.assertEquals(1, aircraft.get(0).getId());
        Assertions.assertEquals("Boeing 737", aircraft.get(0).getType());
        Assertions.assertEquals("Example Airlines", aircraft.get(0).getAirlineName());
        Assertions.assertEquals(180, aircraft.get(0).getNumberOfPassengers());
        Assertions.assertTrue(aircraft.get(0).getPassengers().isEmpty());
        Assertions.assertNotNull(aircraft);
        Assertions.assertFalse(aircraft.isEmpty());
    }

}
