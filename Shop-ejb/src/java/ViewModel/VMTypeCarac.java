/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Carac;
import Entity.Reference;
import Entity.TypeCarac;
import flexjson.JSON;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author anton
 */
public class VMTypeCarac extends VMAbstract<VMTypeCarac,TypeCarac> {
    public Integer id;

    public String Label;
    
    public List<Value> Values = new ArrayList<Value>();
    
    public class Value {
    public String Value;
    public List<Integer> References = new ArrayList<Integer>();
    Value(String _value, Integer _refID) {
        Value = _value;
        References.add(_refID);
    }
}
    
    public VMTypeCarac(TypeCarac carac) {
        id = carac.getIdTypeCarac();
        Label = carac.getLabel();
    }
    
    public VMTypeCarac(TypeCarac carac, List<Reference> referenceList) {
        this(carac);
        for(Reference ref : referenceList) {
            for(Carac c : ref.getCaracList())
                if(c.getTypeCaracidCarac().getIdTypeCarac() == id)
                    addValue(c.getValue(),ref.getIdReference());
        }
    }
    
    private void addValue(String value, Integer refID) {
        for(Value v : Values) {
            if(v.Value.equals(value)) {
                v.References.add(refID);
                return; 
            }
        }
        Values.add(new Value(value,refID));
    }
    
    
    VMTypeCarac() {
        super();
    }
    
    public static List<VMTypeCarac> toVMTypeCaracList(List<TypeCarac> list) {
        if(list == null)
            return null;
        List<VMTypeCarac> converted = new ArrayList<>();
        for(TypeCarac carac : list) {
            converted.add(new VMTypeCarac(carac));
        }
        return converted;
    }
    
    List<VMTypeCarac> toVMTypeCaracList(List<TypeCarac> typeCaracList, List<Reference> referenceList) {
        if(typeCaracList == null)
            return null;
        List<VMTypeCarac> converted = new ArrayList<>();
        for(TypeCarac carac : typeCaracList) {
            converted.add(new VMTypeCarac(carac,referenceList));
        }
        return converted;
    }
    
    @JSON(include=false)
    public static VMTypeCarac getInstance() {
        return new VMTypeCarac();
    }
    

    @Override
    public VMTypeCarac to(TypeCarac element) {
        return new VMTypeCarac(element);
    }

    
}