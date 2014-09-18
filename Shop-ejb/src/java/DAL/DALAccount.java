/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Customer;
import Entity.Orders;
import java.util.List; 
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author wojaka
 */
public class DALAccount {
    public static Customer getAccountByMail(String mail,EntityManager em) {
        Customer c;
        c = (Customer) em.createQuery("SELECT c FROM Customer c Where c.mail = '"+mail+"'").getSingleResult();
        List<Orders> l = c.getOrdersList();
        return c;
    }
    
    public static void insertAccount(Customer c,EntityManager em) {
        em.persist(c);
    }
}
