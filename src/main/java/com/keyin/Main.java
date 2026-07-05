package com.keyin;

import com.keyin.http.cli.CliApplication;
import com.keyin.http.client.RESTClient;

import java.util.Scanner;

/**
 * Entry point for the Aviation CLI application
 *Initializes the REST client and displays the menu to a scanner
 */
public class Main
{
    public static void main(String[] args)
    {
        CliApplication cliApplication = new CliApplication();

        String serverURL = args[0];

        if (serverURL != null && !serverURL.isEmpty())
        {
            RESTClient restClient = new RESTClient();
            restClient.setServerURL(serverURL);

            cliApplication.setRestClient(restClient);

            Scanner scanner = new Scanner(System.in);

            while (true)
            {


                System.out.println("Select a report:");
                System.out.println("1. What airports are in each city?");
                System.out.println("2. What aircraft has each passenger flown on?");
                System.out.println("3. What airports does each aircraft operate from?");
                System.out.println("4. What airports have passengers used?");
                System.out.println("5. Exit");
                System.out.print("Choice: ");

                int choice = scanner.nextInt();

                switch (choice)
                {
                    case 1:
                        cliApplication.airportsInSpecificCityReport();
                        break;
                    case 2:
                        cliApplication.passengersFlownOnSpecificAircraftReport();
                        break;
                    case 3:
                        cliApplication.airportsUsedBySpecificAircraftReport();
                        break;
                    case 4:
                        cliApplication.passengersTravelledFromSpecificAirportReport();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}
