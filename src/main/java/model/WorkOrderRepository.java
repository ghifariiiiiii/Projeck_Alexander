/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
import java.util.List;

public interface WorkOrderRepository {

    void addWorkOrder(WorkOrder wo);

    List<WorkOrder> getAllWorkOrders();

    WorkOrder searchWorkOrder(String idWo);

    void updateStatusProgress(String idWo, String statusBaru);

    void deleteWorkOrder(String idWo);
}
