package com.mycompany.equipmentcatalog.view;

import com.mycompany.equipmentcatalog.model.Unit;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UnitModel extends AbstractTableModel{
    
    // Список имен для колонок в таблице
    private static final String[] HEADERS = {
        "id", "type", "model", "serialNumber", "customer"
    };
    
    // Список приборов, которые будем отображать в таблице
    private final List<Unit> units;

    public UnitModel(List<Unit> units) {
        this.units = units;
    }
// Получить количество строк в таблице - у нас это размер коллекции
    @Override
    public int getRowCount() {
        return units.size();
        
    }
// Получить количество столбцов - столько же, сколько полей
    @Override
    public int getColumnCount() {
        return 5;
    }
    
// Вернуть заголовок колонки - мы его берем из массива headers
    @Override
    public String getColumnName(int columnIndex) {
        return HEADERS[columnIndex];
    }
    
    // Получить объект для отображения в конкретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Unit unit = units.get(rowIndex);
        // В зависимости от номера колонки возвращаем то или иное поле записи
        return switch (columnIndex) {
            case 0 -> unit.getUnitId().toString();
            case 1 -> unit.getUnitType();
            case 2 -> unit.getUnitModel();
            case 3 -> unit.getSerialNumber();
            case 4 -> unit.getCustomer();
            default -> "";
        };
    }
}
