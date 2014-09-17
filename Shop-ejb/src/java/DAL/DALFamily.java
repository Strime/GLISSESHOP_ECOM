/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Familly;
import Entity.Reference;
import java.util.List;
//import Faces.FamillyFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Sancassani
 */

public class DALFamily {
    
    public static Familly getFamily(Integer id,EntityManager em) {
        Familly arr_fam = (Familly)em.createQuery("SELECT f FROM Familly f Where f.idFamilly = "+id).getResultList().get(0);
        
        return arr_fam;
    }

    public static List<Familly> getAll(EntityManager em) {
        List<Familly> arr_fam = (List<Familly>)em.createQuery("SELECT f FROM Familly f").getResultList();
        return arr_fam;
    }

    public static List<Familly> getFirstLevel(EntityManager em) {
        List<Familly> arr_fam = (List<Familly>)em.createQuery("SELECT f FROM Familly f WHERE f.famillyidFamilly IS NULL").getResultList();
        return arr_fam;
    }
}
