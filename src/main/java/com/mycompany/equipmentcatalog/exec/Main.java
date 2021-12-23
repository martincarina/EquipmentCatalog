package com.mycompany.equipmentcatalog.exec;

import com.mycompany.equipmentcatalog.view.EquipmentView;
import javax.swing.*;

public class Main {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EquipmentView frame1 = new EquipmentView();
     frame1.setTitle("Equipment Catalog");
     frame1.setVisible(true);
        });
    }
}
