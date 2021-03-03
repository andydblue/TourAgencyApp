package business.interfaces;

import entities.Excursion;

import java.util.List;

public interface ExcursionInterface {
    List<Excursion> getExcursionsByCountryID(int id);
}
