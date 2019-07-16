package com.github;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @auther plg
 * @date 2019/5/6 12:10
 */
public class OrderManager {
    private Scanner input = new Scanner(System.in);
    private HashMap<Goods,Integer> orderMap = new HashMap<>();
    private boolean add(Goods goods,int count){
      if(goods == null || count == 0){
          System.out.println("参数不合法。。。。");
          return false;
      }else{
          orderMap.put(goods,count);
          System.out.println("===============添加成功==============");
          this.show();
          return true;
      }
    }
    private boolean delete(Goods goods){
        if(orderMap.containsKey(goods)){
            orderMap.remove(goods);
            System.out.println("===============删除成功==============");
            this.show();
            return true;
        }else {
            System.out.println("===============删除失败==============");
            System.out.println("没有该商品。。。");
            return false;
        }

    }
    private boolean search(Goods goods){
        if(orderMap.containsKey(goods)){
            // 名称---单价---数量
            System.out.println( " 商品名称  " + "   " + " 商品单价 " + "    " + "  商品数量 " );
            System.out.println( "    " +  goods.getName() + "          " + goods.getPrice() + "           " +  orderMap.get(goods));
            return true;
        }else{
            System.out.println("没有该订单。。。");
            return false;
        }
    }
    private boolean modify(Goods oldGoods,Goods newGoods,int count){
        if(orderMap.containsKey(oldGoods)){
            orderMap.remove(oldGoods);
            orderMap.put(newGoods,count);
            this.show();
            return true;
        }else{
            System.out.println("修改失败，没有该商品。。");
            return false;
        }
    }
    public void addOrder(){
        System.out.println("请输入要购买的商品信息，按照如下格式：");
        System.out.println("名称--单价--数量");
        System.out.print("请输入：");
        String str = input.nextLine();
        String[] arr = str.split("--");
        Goods goods = new Goods(arr[0],arr[1]);
        add(goods,Integer.parseInt(arr[2]));
    }
    public void deleteOrder(){
        System.out.println("请输入要删除的订单信息，按照如下格式：");
        System.out.println("名称--单价");
        System.out.print("请输入：");
        String str = input.nextLine();
        String[] arr = str.split("--");
        Goods goods = new Goods(arr[0],arr[1]);
        delete(goods);
    }
    public void modifyOrder(){
        System.out.println("请输入修改前的订单信息，按照如下格式：");
        System.out.println("名称--单价--数量");
        System.out.print("请输入：");
        String str1 = input.nextLine();
        String[] arr1 = str1.split("--");
        Goods oldGoods = new Goods(arr1[0],arr1[1]);
        System.out.println("请输入修改后的订单信息，按照如下格式：");
        System.out.println("名称--单价--数量");
        System.out.print("请输入：");
        String str2 = input.nextLine();
        String[] arr2 = str1.split("--");
        Goods newGoods = new Goods(arr2[0],arr2[1]);
        this.modify(oldGoods,newGoods,Integer.parseInt(arr2[2]));
    }
    public void searchOrder(){
        System.out.println("请输入要查看的订单信息，按照如下格式：");
        System.out.println("名称--单价");
        System.out.print("请输入：");
        String str = input.nextLine();
        String[] arr = str.split("--");
        Goods goods = new Goods(arr[0],arr[1]);
        this.search(goods);
    }
    public void show(){
        int i = 0;
        System.out.println("===============订单信息===============");
        System.out.println(" Id    " + "  商品名称  " + "   " + " 商品单价 " + "    " + "  商品数量 " );
        for(Goods goods : orderMap.keySet()){
            System.out.println("  " + (i++) + "      " +  goods.getName() + "          " + goods.getPrice() + "           " +  orderMap.get(goods));
        }

    }
    public void load(){
        File file = new File("order.txt");
        int count = 0;
        int sum = 0;
        try(Writer out = new FileWriter(file)){
            out.write("=====================订单信息===================\n");
            out.write("商品名称               数量               单价" + "\n");
            for(Goods goods : orderMap.keySet()){
                count = orderMap.get(goods);
                out.write("  " +  goods.getName()  + "              " +  count +   "               " + goods.getPrice() + "\n");
                sum += count * Double.parseDouble(goods.getPrice());
            }

            out.write("------------------------------------------总价" + "\n");
            out.write("                                      " + sum + "元");
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
