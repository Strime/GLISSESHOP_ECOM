/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.DicoCarac;
import Entity.Familly;
import Session.ReferenceSessionbean;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class VMFamily {

    public Integer id;

    public String Label;
    
    public Integer ParentId;
    
    public String ParentLabel;

    public List<VMFamily> Children;
    
    public List<VMTypeCarac> TypeCaracs;
    
    private static JSONSerializer json = new JSONSerializer().prettyPrint(true);
    
    public VMFamily(Familly family) {
        id = family.getIdFamilly();
        Label = family.getLabel();
        if(family.getFamillyidFamilly() != null) {
            ParentId = family.getFamillyidFamilly().getIdFamilly();
            ParentLabel = family.getFamillyidFamilly().getLabel();
        } else {
            ParentId = null;
            ParentLabel = null;
        }        
        DicoCarac dico = family.getDicoCaracidFamilly();
        if(dico != null) {
            TypeCaracs = VMTypeCarac.getInstance().toVMTypeCaracList(dico.getTypeCaracList(),(new ReferenceSessionbean()).getAllRefsFromFamId(id));
        } else {
            TypeCaracs = new ArrayList<VMTypeCarac>();
        }
        if(family.getFamillyidFamilly() != null)
        {
            DicoCarac dicoParent = family.getFamillyidFamilly().getDicoCaracidFamilly();
            if(dicoParent != null) {
                List<VMTypeCarac> newList = VMTypeCarac.getInstance().toVMTypeCaracList(dicoParent.getTypeCaracList(),(new ReferenceSessionbean()).getAllRefsFromFamId(id));
                for(VMTypeCarac tc : newList) {
                    boolean found = false;
                    for(VMTypeCarac tc2 : TypeCaracs) {
                        if(tc.id == tc2.id) {
                            //tc2.Values.addAll(tc.Values);
                            found = true;
                        }
                    }
                    if(!found)
                            TypeCaracs.add(tc);
                    
                }
            }
        }
        if(family.getFamillyList().size() > 0) {
            Children = toVMFamilyList(family.getFamillyList());
        }
    }
    
    public static List<VMFamily> toVMFamilyList(List<Familly> list) {
        List<VMFamily> converted = new ArrayList<>();
        for(Familly family : list) {
            converted.add(new VMFamily(family));
        }
        return converted;
    }
    
    @Override
    public String toString() {
        return json.deepSerialize(this);
    }
}
