package com.github.excellent01;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @auther plg
 * @date 2019/4/24 16:01
 */
public class OrderManager {
    private HashMap<Goods, Integer> map = new HashMap<>();
    public  boolean add(Goods goods,int count){
       // goods = new com.github.excellent01.Goods(goods,)
        if(!map.containsKey(goods)){
            map.put(goods,count);
            return true;
        }else{
            System.out.println("请选择修改项,该功能用于添加新商品。。。：");
            return false;

        }
    }

    public boolean remove(Goods goods){
        if(map.containsKey(goods)){
            map.remove(goods);
            return true;
        }
        return false;
    }

    public boolean search(Goods goods){
        if(map.containsKey(goods)){
            int count = map.get(goods);
            System.out.println("商品名称-----单价-----数量----总价");
            System.out.println(goods.getName() + "        " +  goods.getPrice() + "        " + count + "    " + (goods.getPrice().multiply(new BigDecimal(count))));
            return true;
        }
        return false;
    }
    public boolean modify(Goods goods,int count){
        if(map.containsKey(goods)){
            map.put(goods,count);
            return true;
        }
        return false;
    }
    public void print(){
        System.out.println("商品名称-----数量-----价格");
       for(Goods goods : map.keySet()){
           Integer count = map.get(goods);
           if(count > 0){
               //System.out.println(map);
               System.out.println(goods.getName() + "          " +  count  + "        " +(goods.getPrice().multiply(new BigDecimal(count))));
           }
       }
    }

    public void load(){
        File file = new File("order.txt");
        BigDecimal sum = new BigDecimal(0);
        try(Writer out = new FileWriter(file)){
            out.write("商品名称-----数量-----价格" + "\n");
            for(Goods goods : map.keySet()){
                int count = map.get(goods);
                BigDecimal price = (goods.getPrice().multiply(new BigDecimal(count)));
                sum = sum.add(price);
                if(count > 0){
                   out.write(goods.getName() + "           "  + count + "          " + price + "\n");
                }
            }
            out.write("---------------------------总价" + "\n");
            out.write("                         " + sum + "元");
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}