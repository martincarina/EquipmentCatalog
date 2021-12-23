
package com.mycompany.equipmentcatalog.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *   Класс для хранения данных прибора
 */
@Entity
@Table(name = "unit")
public class Unit implements Serializable {
    // Идентификатор прибора
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;
    // Тип
    @Column
    private String unitType;
    // Модель
    @Column
    private String unitModel;
    // Серийный номер
    @Column
    private String serialNumber;
    // организация-покупатель
    @Column
    private String customer;

    public Unit() {
    }

    public Unit(String unitType, String unitModel, String serialNumber, String customer) {
        this.unitType = unitType;
        this.unitModel = unitModel;
        this.serialNumber = serialNumber;
        this.customer = customer;
    }
    
    public Unit(Long unitId, String unitType, String unitModel, String serialNumber, String customer) {
        this.unitId = unitId;
        this.unitType = unitType;
        this.unitModel = unitModel;
        this.serialNumber = serialNumber;
        this.customer = customer;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitModel() {
        return unitModel;
    }

    public void setUnitModel(String unitModel) {
        this.unitModel = unitModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Unit{" + "unitId=" + unitId + 
                ", unitType=" + unitType + 
                ", unitModel=" + unitModel + 
                ", serialNumber=" + serialNumber + 
                ", customer=" + customer + '}';
    }
}
