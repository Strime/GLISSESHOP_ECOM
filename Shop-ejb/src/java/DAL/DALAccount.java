/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Customer;
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
        Vector listRes = (Vector) em.createQuery("SELECT c FROM Customer c Where c.mail = '"+mail+"'").getResultList();
        
        if(listRes.isEmpty()) 
            return null;
        else 
            return (Customer) listRes.get(0);
    }
    
    public static void insertAccount(Customer c,EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(c);
        tx.commit();
    }
}
