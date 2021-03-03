package dao.interfaces;

import entities.Country;
import entities.Season;

import java.util.List;

public interface CountryDaoInterface extends DaoCrud <Country, Integer> {
    List<Country> getAllCountries();
    List<Country> getCountriesBySeason(Season season);
}
