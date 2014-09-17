/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Reference;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class VMReference {
    public Integer id;
    public String Label;
    public Integer Prix;
    public Integer Stock;
    
    private static JSONSerializer json = new JSONSerializer().prettyPrint(true);
    
    public VMReference(Reference reference) {
        id = reference.getIdReference();
        Label = reference.getProductidReference().getLabel();
        Prix = reference.getProductidReference().getPrice();
    }
    
    public static List<VMReference> toVMReferenceList(List<Reference> list) {
        List<VMReference> converted = new ArrayList<VMReference>();
        for(Reference reference : list) {
            converted.add(new VMReference(reference));
        }
        return converted;
    }
}
