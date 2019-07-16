package com.github.excellent01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @auther plg
 * @date 2019/4/24 15:30
 */
public class GoodManger {
    private LinkedList<Goods> linkedList = new LinkedList<>();
    public boolean add(Goods goods){
        if(goods == null || linkedList.contains(goods)){
            System.out.println("已经商家过该商品了。。。");
            return false;
        }
        return linkedList.add(goods);
    }
    public boolean remove(Goods goods){
        if(goods == null || !linkedList.contains(goods)){
            System.out.println("没有上架过该商品，不可删除。。。");
            return false;
        }
        return linkedList.remove(goods);
    }
    public String search(Goods goods){
        if(goods == null){
            System.out.println("没有该商品。。。");
        }
        if(linkedList.contains(goods)){
            return goods.toString();
        }
        return null;
    }
    public BigDecimal getPrice(Goods goods){
        if(linkedList.contains(goods)){
            for(Goods goods1 : linkedList){
                if(goods.equals(goods1)){
                    return goods1.getPrice();
                }
            }
        }
        return null;
    }
    public boolean modifyName(String name, BigDecimal price){
        if(name == null || price.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("修改失败。。。");
        }
       Iterator<Goods> iterator = linkedList.iterator();
        Goods temp = null;
        while(iterator.hasNext()){
            temp = iterator.next();
            if(temp.getName().equals(name)){
                break;
            }
        }
        if(iterator.hasNext()) {
            temp.setName(name);
            temp.setPrice(price);
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return linkedList.toString();
    }
    public void show(){
        System.out.println("商品名称" + "        " + "单价");
        for(Goods goods : linkedList){
            System.out.println(goods.getName() + "             " + goods.getPrice() + "元");
        }
    }
    public void afterload(){
        File file = new File("good.txt");
        try(Writer out = new FileWriter(file)){
            for(Goods goods : linkedList){
                out.write(goods.getName() + "-" + goods.getPrice() + "\n");
            }
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
