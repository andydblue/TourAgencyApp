package business.interfaces;

import entities.Country;
import entities.Season;

import java.util.List;

public interface CountryInterface {
    List<Country> getAllCountries();
    List<Country> getCountriesBySeason(Season season);
}
