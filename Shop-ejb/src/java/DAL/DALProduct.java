/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import Entity.Carac;
import Entity.Product;
import Session.ProductSessionBean.VMCaracT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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
        List<Carac> arr_carac = (List<Carac>)em.createQuery("SELECT DISTINCT c FROM Carac c WHERE c.typeCaracidCarac IN :types AND c.value IN :values AND (c.referenceidReference.productidReference.famillyidProduct.idFamilly = "+famID.toString()+" OR (c.referenceidReference.productidReference.famillyidProduct.famillyidFamilly IS NOT NULL AND c.referenceidReference.productidReference.famillyidProduct.famillyidFamilly.idFamilly = "+famID.toString()+"))").setParameter("types", possTypes).setParameter("values", possValues).getResultList();
        List<Product> arr_prod = new ArrayList<>();
        
        Map<Product,List<Integer>> candidates = new HashMap<>();
        //pas super ça...
        for(Carac c : arr_carac) {
            Integer typeId = c.getTypeCaracidCarac().getIdTypeCarac();
            Product product = c.getReferenceidReference().getProductidReference();
            if(candidates.containsKey(product)) {
                    if(!candidates.get(product).contains(typeId)) {
                        candidates.get(product).add(typeId);
                    }
            }
            else {
                candidates.put(product, new ArrayList<Integer>());
                candidates.get(product).add(typeId);
            }
        }
        Iterator it = candidates.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            if((((List<Integer>) pairs.getValue()).size()==possTypes.size())) {
                arr_prod.add((Product)pairs.getKey());
            }
                
        }
        return arr_prod;
    }

    public static List<Product> getByKeyword(String keyword, EntityManager em) {
       List<Product> arr_prod = (List<Product>)em.createQuery("SELECT p FROM Product p WHERE p.label LIKE :keyword").setParameter("keyword", keyword+"%").getResultList();
       return arr_prod;
    }
}
