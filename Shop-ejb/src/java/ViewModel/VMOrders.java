/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Customer;
import Entity.Familly;
import Entity.Item;
import Entity.Orders;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Order;

/**
 *
 * @author anton
 */
public class VMOrders extends VMAbstract<VMOrders,Orders>{

    public Integer idOrder;
    public List<VMItem> itemList;
    
    private static JSONSerializer json = new JSONSerializer().prettyPrint(true);

    public VMOrders(Orders orders) {
        idOrder = orders.getIdOrder();
        itemList = VMItem.getInstance().toVMItemList(orders.getItemList());
    }
    
    @Override
    public String toString() {
        return json.deepSerialize(this);
    }

    @Override
    public VMOrders to(Orders o) {
        return new VMOrders(o);
    }
}
