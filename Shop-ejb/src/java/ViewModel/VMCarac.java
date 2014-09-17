/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Carac;
import flexjson.JSON;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class VMCarac extends VMAbstract<VMCarac,Carac> {
    public Integer id;

    public String Label;

    public String Value;
    
    public VMCarac(Carac carac) {
        id = carac.getIdCarac();
        Label = carac.getTypeCaracidCarac().getLabel();
        Value = carac.getValue();
    }
    
    VMCarac() {
        super();
    }
    
    public static List<VMCarac> toVMCaracList(List<Carac> list) {
        List<VMCarac> converted = new ArrayList<VMCarac>();
        for(Carac carac : list) {
            converted.add(new VMCarac(carac));
        }
        return converted;
    }
    
    @JSON(include=false)
    public static VMCarac getInstance() {
        return new VMCarac();
    }
    

    @Override
    public VMCarac to(Carac element) {
        return new VMCarac(element);
    }
}