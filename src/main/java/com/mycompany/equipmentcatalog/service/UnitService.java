package com.mycompany.equipmentcatalog.service;

import com.mycompany.equipmentcatalog.dao.UnitDAO;
import com.mycompany.equipmentcatalog.dao.UnitDAOImpl;
import com.mycompany.equipmentcatalog.model.Unit;
import java.util.List;


public class UnitService {
    private final UnitDAO unitsDao = new UnitDAOImpl();

    public UnitService() {
    }

    public UnitValidation validateUnit(Unit unit) {
        UnitValidation unitValidation = new UnitValidation();
        unitValidation.setValid(true);
        if (unit.getUnitType().isEmpty()) {
            unitValidation.setValid(false);
            unitValidation.setError("Поле Type должно быть заполнено.");
            return unitValidation;
        }

        if (unit.getUnitModel().isEmpty()) {
            unitValidation.setValid(false);
            unitValidation.setError("Поле Model должно быть заполнено.");
            return unitValidation;
        }

        if (unit.getSerialNumber().isEmpty()) {
            unitValidation.setValid(false);
            unitValidation.setError("Поле Serial Number должно быть заполнено.");
            return unitValidation;
        }

        if (unit.getCustomer().isEmpty()) {
            unitValidation.setValid(false);
            unitValidation.setError("Поле Customer должно быть заполнено.");
            return unitValidation;
        }
        return unitValidation;
    }
    
    public Unit findUnit(Long id) {
        return unitsDao.findById(id);
    }

        public void addUnit(Unit unit) {
 //       UnitValidation unitValidation = validateUnit(unit);
 //       if (unitValidation.isValid()) {
            unitsDao.add(unit);
 //       }
 //       return unitValidation;
    }

    public void deleteUnit(Unit unit) {
        unitsDao.delete(unit);
    }

    public void updateUnit(Unit unit) {
 //       UnitValidation unitValidation = validateUnit(unit);
 //        if (unitValidation.isValid()) {
        unitsDao.update(unit);
  //       }
    }

    public List<Unit> findAllUnits() {
        return unitsDao.findAll();
    }
    
    public Long countUnits() {
        return unitsDao.countUnits();
    }
}
