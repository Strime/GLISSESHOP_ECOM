/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.DicoCarac;
import Entity.Product;
import flexjson.JSON;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class VMProduct extends VMAbstract<VMProduct,Product> {
    public Integer id;

    public String Label;
    
    public Integer FamId;
    
    public String FamLabel;
    
    public Integer ParentId;
    
    public String ParentLabel;
    
    public String Description;
    
    public Integer Price;

    public List<VMReference> Children;
    
    public List<String> Medias = new ArrayList<String>();
    
    public List<VMTypeCarac> TypeCaracs = new ArrayList<VMTypeCarac>();
    
    public VMProduct(Product product) {
        id = product.getIdProduct();
        Label = product.getLabel();
        FamId = product.getFamillyidProduct().getIdFamilly();
        FamLabel = product.getFamillyidProduct().getLabel();
        if(product.getFamillyidProduct().getFamillyidFamilly() != null) {
            ParentId = product.getFamillyidProduct().getFamillyidFamilly().getIdFamilly();
            ParentLabel = product.getFamillyidProduct().getFamillyidFamilly().getLabel();
        } else {
            ParentId = null;
            ParentLabel = null;
        }                
        Description = product.getLongLabel();
        Price = product.getPrice();
        DicoCarac dico = product.getFamillyidProduct().getDicoCaracidFamilly();
        if(dico != null)
        TypeCaracs = VMTypeCarac.getInstance().toVMTypeCaracList(dico.getTypeCaracList(),product.getReferenceList());
        List<VMMedia> list =  VMMedia.getInstance().toList(product.getDicoMediaidProduct().getMediaList());
        
        for(VMMedia media : list) {
            Medias.add(media.FilePath);
        }
        if(product.getReferenceList().size() > 0) {
            Children = VMReference.toVMReferenceList(product.getReferenceList());
        }
        else
            Children = new ArrayList<VMReference>();
        
    }
    
    VMProduct() {
        super();
    }
    
    @JSON(include=false)
    public static VMProduct getInstance() {
        return new VMProduct();
    }
    

    @Override
    public VMProduct to(Product element) {
        return new VMProduct(element);
    }
}
