/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Item;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Sancassani
 */
public class DALOrder {
    
    public static void insertAccount(String items,EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(items);
        tx.commit();
    }
}
