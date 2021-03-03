package dao.interfaces;

import entities.Excursion;

import java.util.List;

public interface ExcursionDaoInterface extends DaoCrud<Excursion, Integer> {
    List<Excursion> getExcursionsByCountry(int id);
}
