package com.napier.sem;

/** testing country
 * to represents country data
 */
public class country {
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    /**
     * Country Code
     */
    private String country_code;

    /**
     * Country Name
     */
    private String country_name;

    /**
     * Continent
     */
    private String continent;

    /**
     * Region
     */
    private String region;

    /**
     * Population
     */
    private int population;

    /**
     * Capital
     */
    private int capital;

    private String city_name;
}

