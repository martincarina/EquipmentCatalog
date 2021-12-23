package com.mycompany.equipmentcatalog.dao;

import com.mycompany.equipmentcatalog.model.Unit;
import java.util.List;



public interface UnitDAO{
    public Unit findById(Long id);
 
    public void add(Unit unit);
 
    public void update(Unit unit);

    public void delete(Unit unit);

    public List<Unit> findAll();

    public Long countUnits();
}