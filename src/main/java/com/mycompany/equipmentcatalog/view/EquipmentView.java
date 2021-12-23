
package com.mycompany.equipmentcatalog.view;

import com.mycompany.equipmentcatalog.model.Unit;
import com.mycompany.equipmentcatalog.service.UnitService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import java.util.concurrent.ExecutionException;

public class EquipmentView extends JFrame implements ActionListener{
    
    private static final String C_ADD = "Add";
    private static final String C_UPDATE = "Update";
    private static final String C_DELETE = "Delete";
    private static final String C_REFRESH = "Refresh";
    private static final String C_COUNT = "Get count of records";

 
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static final String COUNT = "COUNT";
    private final  String   TITLE_confirm = "Окно подтверждения";
    
    private final UnitService unitService = new UnitService();
    private final JTable unitTable = new JTable();
    
    private JLabel statusLabel = new JLabel();
    
    public EquipmentView(){
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        unitTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Используем layout GridBagLayout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);
        
        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // устанавливаем у нее layout
        btnPanel.setLayout(gridbag);
        
        // Создаем кнопки и укзазываем их заголовок и ActionCommand
        btnPanel.add(createButton(gridbag, gbc, C_ADD, ADD));
        btnPanel.add(createButton(gridbag, gbc, C_UPDATE, EDIT));
        btnPanel.add(createButton(gridbag, gbc, C_DELETE, DELETE));
        btnPanel.add(createButton(gridbag, gbc, C_REFRESH, LOAD));
        
        btnPanel.add(createButton(gridbag, gbc, C_COUNT, COUNT));
        btnPanel.add(statusLabel);
        
        // Создаем панель для левой колонки с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);
        // Кладем панель со скроллингом, внутри которой находится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(unitTable), BorderLayout.CENTER);
 
        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // Загружаем список оборудования
        try {
            loadUnit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будет проверяться в обработчике и мы будем знать, какую 
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        try {
            switch (action) {
                // Перегрузка данных
                case LOAD -> loadUnit();
                // Добавление записи
                case ADD -> addUnitDialog();
                // Исправление записи
                case EDIT -> updateUnitDialog();
                // Удаление записи
                case DELETE -> deleteUnit();
                //фоновая задача - общее число записей в таблице
                case COUNT -> countUnits();
            }
             }catch(Exception ex){
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
        // Загрузить список оборудования
        private void loadUnit(){
            SwingWorker<List<Unit>,Void> worker;
            worker = new SwingWorker<List<Unit>,Void>(){
                @Override
                protected List<Unit> doInBackground() throws Exception {
                   return unitService.findAllUnits();
                }
                @Override
                protected void done(){
                List<Unit> status;
                try{
                    status = get();
                    // Создаем модель, которой передаем полученный список записей
                    UnitModel cm = new UnitModel(status);
                    // Передаем нашу модель таблице - и она может ее отображать
                    unitTable.setModel(cm);
                }catch(InterruptedException | ExecutionException e){
                    }
                }
            };
            worker.execute();
        }
        // Добавление оборудования
        private void addUnitDialog(){
        // Создаем диалог для ввода данных
        EditUnitDialog ecd = new EditUnitDialog();
        // Обрабатываем закрытие диалога
        loadUnit();
    }
        // Редактирование записи
        private void updateUnitDialog(){
        // Получаем выделенную строку
        int sr = unitTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
           if (sr != -1) {
            // Получаем ID записи
            Long id = Long.parseLong(unitTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные записи по ID
            Unit unit = unitService.findUnit(id);
            // Создаем диалог для ввода данных и передаем туда запись
            EditUnitDialog ecd = new EditUnitDialog(unit);
            // Обрабатываем закрытие диалога
            loadUnit();
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }
        
        // Удаление записи из БД
         private void deleteUnit() {
        // Получаем выделенную строку
        int sr = unitTable.getSelectedRow();
        if (sr != -1) {
            UIManager.put("OptionPane.yesButtonText"   , "Да"    );
            UIManager.put("OptionPane.noButtonText"    , "Нет"   );
            //подтверждение удаления
            int result = JOptionPane.showConfirmDialog(this,
                                   "Удалить выбранную запись?",
                                          TITLE_confirm,
                                          JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION){
            // Получаем ID записи для удаления
            Long id = Long.parseLong(unitTable.getModel().getValueAt(sr, 0).toString());
            Unit unit = unitService.findUnit(id);            
            // Удаляем запись
            unitService.deleteUnit(unit);
            }
            // перегружаем список оборудования
            loadUnit();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }
         //получение общего числа записей в БД
         private void countUnits(){
             SwingWorker<Long,Long> worker;
             worker = new SwingWorker<Long,Long>(){
            @Override
            protected Long doInBackground() throws Exception {
                return unitService.countUnits();
            }

            @Override
            protected void done(){
                long status;
                try{
                    status = get();
                    statusLabel.setText("Records found: "+status);
                }catch(InterruptedException | ExecutionException e){
                }
            }
        };
             worker.execute();
    }
}
