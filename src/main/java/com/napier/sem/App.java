package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class App {
    /**
     * Connection to MySQL database.*/
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Create a new country in the word object
        CountryMethod cw = new CountryMethod();
        CountryOutput coutput = new CountryOutput();

        // Array Countries, Region, Continents with the population largest to smallest
        // Extract country in the world from a class
        ArrayList<country> countries = cw.getCountry(a.con);

        // Array Countries, Region, Continents with the population largest to smallest
        // Extract country in the world from a class

        ArrayList<country> continents = cw.getCountriesByContinent(a.con, "Asia");



        // Printing data
        System.out.println("For World");
        coutput.printPopulation(countries);

        // Printing data
        System.out.println("For Continent");
        coutput.printPopulation(continents);


        // Disconnect from database
        a.disconnect();
    }

    /**
     * Gets all the current employees and salaries.
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<country> getCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<country> countries = new ArrayList<country>();
            while (rset.next())
            {
                country cou = new country();
                cou.setCountry_code(rset.getString("country.Code"));
                cou.setCountry_name(rset.getString("country.Name"));
                cou.setContinent(rset.getString("country.Continent"));
                cou.setRegion(rset.getString("country.Region"));
                cou.setPopulation(rset.getInt("country.Population"));
                cou.setCity_name(rset.getString("city.Name"));
                countries.add(cou);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Prints a list of employees.
     * @param countries The list of employees to print.
     */
    public void printPopulation(ArrayList<country> countries)
    {
        // Print top border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Print header
        System.out.println(String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Print header-bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Loop over all employees in the list
        for (country count : countries)
        {
            // Format population with commas and three decimal places
            DecimalFormat numformat = new DecimalFormat("#,###,###");
            String formattedPopulation = numformat.format(count.getPopulation());

            String count_string =
                    String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |",
                            count.getCountry_code(), count.getCountry_name(), count.getContinent(), count.getRegion(), formattedPopulation, count.getCity_name());
            System.out.println(count_string);
        }
        // Print bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

    }

}