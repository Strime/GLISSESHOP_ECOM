/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sancassani
 */
public class VMItem extends VMAbstract<VMItem,Item> {

    public Integer Count;
    public VMReference Reference;
    
    
    public VMItem() {
        
    }
    public VMItem(Item item) {
        Count = item.getCount();
        Reference = new VMReference(item.getIdRef());
    }
    @Override
    public VMItem to(Item element) {
        return new VMItem(element);
    }
    
    public static VMItem getInstance() {
        return new VMItem();
    }
    
    public List<VMItem> toVMItemList(List<Item> list) {
        List<VMItem> converted = new ArrayList<VMItem>();
        for(Item item : list) {
            converted.add(new VMItem(item));
        }
        return converted;
    }
    
}
