package business.implementations;

import business.interfaces.ExcursionInterface;
import dao.implementationsMySQL.ExcursionDaoImplMySQL;
import entities.Excursion;

import java.util.List;

public class ExcursionImpl implements ExcursionInterface {

    ExcursionDaoImplMySQL excursionDaoImplMySQL = new ExcursionDaoImplMySQL();

    @Override
    public List<Excursion> getExcursionsByCountryID(int id) {
        return excursionDaoImplMySQL.getExcursionsByCountry(id);
    }
}
