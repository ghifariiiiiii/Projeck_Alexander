/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.projeck_alexander;

/**
 *
 * @author ADVAN
 */
import controller.WorkOrderController;
import model.WorkOrderRepository;
import model.WorkOrderRepositoryImpl;
import view.WorkOrderView;
import javax.swing.SwingUtilities;

public class Projeck_Alexander {

    public static void main(String[] args) {
        // Menjalankan GUI di Event Dispatch Thread sesuai standar Java Swing (Modul 2)
        SwingUtilities.invokeLater(() -> {
            // Instansiasi Model (melalui Interface), View, dan menyuntikkannya ke Controller (DIP)
            WorkOrderRepository repository = new WorkOrderRepositoryImpl();
            WorkOrderView view = new WorkOrderView();
            new WorkOrderController(repository, view);

            view.setVisible(true);
        });
    }
}
