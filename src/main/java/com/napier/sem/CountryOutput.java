package com.napier.sem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CountryOutput {
    /**
     * Prints a list of World sorted by population.
     * @param dataoutput The list of countries to print.
     */
    public void printPopulation(ArrayList<country> dataoutput)
    {

        // Print top border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Print header
        System.out.println(String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Print header-bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Loop over all employees in the list
        for (country count : dataoutput)
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

