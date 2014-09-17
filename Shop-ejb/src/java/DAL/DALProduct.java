/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Product;
import Session.ProductSessionBean.VMCaracT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sancassani
 */
public class DALProduct {

    public static Product getProduct(Integer id,EntityManager em) {
        Product arr_prod = (Product)em.createQuery("SELECT p FROM Product p Where p.idProduct = "+id).getResultList().get(0);
        return arr_prod;
    }

    public static List<Product> getAll(EntityManager em) {
        List<Product> arr_prod = (List<Product>)em.createQuery("SELECT p FROM Product p").getResultList();
        return arr_prod;
    }

    public static List<Product> getFromFamily(Integer famID, EntityManager em) {
        List<Product> arr_prod = (List<Product>)em.createQuery("SELECT p FROM Product p WHERE p.famillyidProduct.idFamilly = "+famID.toString()+" OR (p.famillyidProduct.famillyidFamilly IS NOT NULL AND p.famillyidProduct.famillyidFamilly.idFamilly = "+famID.toString()+")").getResultList();
        return arr_prod;
    }

    public static List<Product> getFromFamilyFiltered(Integer famID, List<String> possValues, List<Integer> possTypes, EntityManager em) {
        List<Product> arr_prod = (List<Product>)em.createQuery("SELECT DISTINCT c.referenceidReference.productidReference FROM Carac c WHERE c.typeCaracidCarac IN :types AND c.value IN :values").setParameter("types", possTypes).setParameter("values", possValues).getResultList();
        return arr_prod;
    }

    public static List<Product> getByKeyword(String keyword, EntityManager em) {
       List<Product> arr_prod = (List<Product>)em.createQuery("SELECT p FROM Product p WHERE p.label LIKE :keyword").setParameter("keyword", keyword+"%").getResultList();
       return arr_prod;
    }
}
