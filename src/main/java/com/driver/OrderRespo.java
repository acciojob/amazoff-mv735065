package com.driver;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRespo {

Map<String, Order> orderList;

Map<String,DeliveryPartner> partnerList;

Map<String,String>  orderPartner;



    public void addOrder(Order order) {
        orderList.put(order.getId(), order);
    }


    public void addPartner(String partnerId) {
        partnerList.put(partnerId,new DeliveryPartner(partnerId));



    }


    public void addOrderPartnerPair(String orderId, String partnerId) {

        orderPartner.put(orderId,partnerId);

        DeliveryPartner deliveryPartner=partnerList.get(partnerId);

        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);

     partnerList.put(partnerId,deliveryPartner);


    }


    public Order getOrderById(String orderId) {
   return orderList.get(orderId);

    }

    public DeliveryPartner getPartnerById(String partnerId) {

        return partnerList.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {

        DeliveryPartner deliveryPartner=partnerList.get(partnerId);

        return deliveryPartner.getNumberOfOrders();

    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> list= new ArrayList<>();
        for(String order: orderPartner.keySet()){
            if(orderPartner.get(order).equals(partnerId)) list.add(order);
        }
        return list;
    }


    public List<String> getAllOrders() {

        List<String> list= new ArrayList<>();
        for(Order order: orderList.values()){
            list.add(order.getId());
        }
     return list;
    }

    public Integer getCountOfUnassignedOrders() {
        return orderList.size()-orderPartner.size();


    }

    public void deleteOrderById(String orderId) {

        orderList.remove(orderId);
        orderPartner.remove(orderId);

    }


    public void deletePartnerById(String partnerId) {
        partnerList.remove(partnerId);

        for(String i : orderPartner.keySet()){
            if(orderPartner.get(i).equals(partnerId)) orderPartner.remove(i);
        }

    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {


        String s=""; int time=0;
        for(String i : orderPartner.keySet()){
            if(orderPartner.get(i).equals(partnerId)){
                if(orderList.get(i).getDeliveryTime()>time){
                    time=orderList.get(i).getDeliveryTime();
                    s=i;
                }
            }
        }
return s;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {

        String a[]=time.split(":");
        int t=(60*(Integer.parseInt(a[0])))+Integer.parseInt(a[1]);

        Integer c=0;

        for(String i : orderPartner.keySet()){
            if(orderPartner.get(i).equals(partnerId)) {
                if(orderList.get(i).getDeliveryTime()>t); c++;

            }
            }

        return c;
    }
}


