package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
//        int time=Integer.parseInt(deliveryTime);
//
//        String hours="";
//        boolean h=false;
//        for(char ch: deliveryTime.toCharArray()){
//            if(!Character.isDigit(ch) && )
//        }

        String a[]=deliveryTime.split(":");
        int hour=Integer.parseInt(a[0]);
        int min=Integer.parseInt(a[1]);
        this.deliveryTime=(hour*60)+min;



    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
