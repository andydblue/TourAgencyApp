package business.implementations;

import business.interfaces.CountryInterface;
import dao.implementationsMySQL.CountryDaoImplMySQL;
import entities.Country;
import entities.Season;

import java.util.List;

public class CountryImpl implements CountryInterface {

    CountryDaoImplMySQL countryDaoImplMySQL = new CountryDaoImplMySQL();

    @Override
    public List<Country> getAllCountries() {
        return countryDaoImplMySQL.getAllCountries();
    }

    @Override
    public List<Country> getCountriesBySeason(Season season) {
        return countryDaoImplMySQL.getCountriesBySeason(season);
    }
}
