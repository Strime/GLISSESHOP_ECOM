/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Item;
import Entity.Orders;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Sancassani
 */
public class DALOrder {
    
    public static void insertAccount(String items,EntityManager em) {
        em.persist(items);
    }
    
    public static void insertOrder(Orders order,EntityManager em) {
        em.persist(order);
    }

    public static void insertItem(Item item, EntityManager em) {
        em.persist(item);
    }
    
    public static List<Orders> getOrders(String id,EntityManager em) {
        List<Orders> arr_orders = (List<Orders>) em.createQuery("SELECT o FROM Orders o Where o.customeridOrders.idCustomer = "+id).getResultList();
        return arr_orders;
    }
}
