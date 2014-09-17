/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public abstract class VMAbstract<T,X> {

    private static JSONSerializer json = new JSONSerializer().prettyPrint(true);
    
    public abstract T to(X element);
    
    public List<T> toList(List<X> list) {
        List<T> converted = new ArrayList<T>();
        for(X element : list) {
            converted.add(to(element));
        }
        return converted;
    }
    
    public String toString() {
        return json.deepSerialize(this);
    }
    
}
