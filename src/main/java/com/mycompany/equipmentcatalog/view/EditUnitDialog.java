package com.mycompany.equipmentcatalog.view;

import com.mycompany.equipmentcatalog.model.Unit;
import com.mycompany.equipmentcatalog.service.UnitService;
import com.mycompany.equipmentcatalog.service.UnitValidation;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class EditUnitDialog extends JDialog implements ActionListener{

    private static final String C_TYPE = "type";
    private static final String C_MODEL = "model";
    private static final String C_NUMBER = "serial number";
    private static final String C_CUSTOMER = "customer";
    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";
    
    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;
    
    // Поле для ввода типа прибора
    private final JTextPane txtType = new JTextPane();
    // Поле для ввода модели прибора
    private final JTextPane txtModel = new JTextPane();
    // Поле для ввода серийного номера
    private final JTextPane txtSerialNumber = new JTextPane();
    // Поле для ввода организации-покупателя
    private final JTextPane txtCustomer = new JTextPane();

    // Поле для хранения ID прибора, если мы собираемся редактировать
    // Если это новый прибор - unitId == null
    private Long unitId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;
    
    public EditUnitDialog() {
        this(null);
    }
    public EditUnitDialog(Unit unit) {
        
        // Убираем layout - будем использовать абсолютные координаты
        setLayout(null);

        // Выстраиваем метки и поля для ввода
        buildFields();
        // Если нам передали запись - заполняем поля формы
        initFields(unit);
        // выстариваем кнопки
        buildButtons();

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 450, 220);
        // Делаем форму видимой
        setVisible(true);
    }
    // Размещаем метки и поля ввода на форме
    private void buildFields() {
        // Набор метки и поля для типа
        JLabel lblType = new JLabel(C_TYPE + ":");
        // Выравнивание текста с правой стороны
        lblType.setHorizontalAlignment(SwingConstants.RIGHT);
        // Выставляем координаты метки
        lblType.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        // Кладем метку на форму
        add(lblType);
        // Выставляем координаты поля
        txtType.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // Делаем "бордюр" для поля
        txtType.setBorder(BorderFactory.createEtchedBorder());
        // Кладем поле на форму
        add(txtType);

        // Набор метки и поля для модели
        JLabel lblModel = new JLabel( C_MODEL + ":");
        lblModel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblModel.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblModel);
        txtModel.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtModel.setBorder(BorderFactory.createEtchedBorder());
        add(txtModel);

        // Набор метки и поля для серийного номера
        JLabel lblNumber = new JLabel(C_NUMBER + ":");
        lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNumber.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblNumber);
        txtSerialNumber.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtSerialNumber.setBorder(BorderFactory.createEtchedBorder());
        add(txtSerialNumber);

        // Набор метки и поля для покупателя
        JLabel lblCustomer = new JLabel(C_CUSTOMER + ":");
        lblCustomer.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCustomer.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblCustomer);
        txtCustomer.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtCustomer.setBorder(BorderFactory.createEtchedBorder());
        add(txtCustomer);
    }
    
    // Если передали запись - заполняем поля из записи
    private void initFields(Unit unit) {
        if (unit != null) {
            unitId = unit.getUnitId();
            txtType.setText(unit.getUnitType());
            txtModel.setText(unit.getUnitModel());
            txtSerialNumber.setText(unit.getSerialNumber());
            txtCustomer.setText(unit.getCustomer());
        }
    }
    
    // Размещаем кнопки на форме
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }
    
    // Обработка нажатий кнопок
    @Override
    public void actionPerformed(ActionEvent ae) {
    String action = ae.getActionCommand();
        // Если нажали кнопку SAVE - сохраняем изменения
        save = SAVE.equals(action);
        if (this.isSave()) {
            UnitService unitService = new UnitService();
            Unit unit = this.getUnit();
            //проверка введенных данных
            UnitValidation unitValidation = unitService.validateUnit(unit);
            if(!unitValidation.isValid()){
                    JOptionPane.showMessageDialog(this,unitValidation.getError());
                }
            else{
                if (unit.getUnitId() != null) {
                 unitService.updateUnit(unit);
             }else{
                    unitService.addUnit(unit);
                }
                
                // Если добавление удалось, закрываем форму
            setVisible(false);
            }
        }
            // Закрываем форму, если Cancel
        else{
            setVisible(false);
        }
    }
        
    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }
    
    // Создаем запись о приборе из заполненных полей, которую можно будет записать
    public Unit getUnit() {
        Unit unit = new Unit(unitId, txtType.getText().trim(),
                txtModel.getText().trim(), txtSerialNumber.getText().trim(), txtCustomer.getText().trim());
        return unit;
    }
}
