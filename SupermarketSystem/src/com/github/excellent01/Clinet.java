package com.github.excellent01;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @auther plg
 * @date 2019/4/24 16:03
 */
public class Clinet {
    private static Scanner input = new Scanner(System.in);
    private static String select = null;
    private static String name = "123456";
    private static String word = "654321";
    private static void menuIdenty() {
        Menu.selectIdentity();
        do {
            System.out.println("请选择身份：");
            select = input.nextLine();
             if (select.equals("A")) {
                 int i = 0;
                 while(i < 3){
                     System.out.println("请输入用户名：");
                     String username = input.nextLine();
                     System.out.println("请输入密码：");
                     String password = input.nextLine();
                     if(username.equals(name) && password.equals(word)){
                         loadGoods();
                         goodsMannger();
                         break;
                     }
                     System.out.println("用户名或者密码错误，请重新输入");
                     i++;
                 }
                 if(i == 3){
                     System.out.println("三次机会已用完了,明天再试。。。");
                     System.exit(0);
                 }
                 break;
            } else if (select.equals("B")) {
                 OrderMannger();
                 break;
            }else if(select.equals("Q")){
             }else{
                 System.out.println("输入有误，请重新输入...");
             }
        } while (!select.equals("Q"));
    }
    private static void goodsMannger(){
        Menu.GoodsMenu();
        do {
            System.out.println("请选择：");
            select = input.nextLine();
            switch(select){
                case "A":
                    add();
                    break;
                case "B":
                    remove();
                    break;
                case "C":
                    search();
                    break;
                case "D":
                    modify();
                    break;
                case"E":
                    goodManger.show();
                    break;
                case"Q":
                    goodManger.afterload();
                    break;
                default:
                    break;
            }
        }while(!select.equals("Q"));
    }

    private static void OrderMannger(){
        System.out.println("现有的商品：");
        goodManger.show();
        Menu.OrderMenu();
        do {
            System.out.print("请选择：");
            select = input.nextLine();
            switch(select){
                case "A":
                    addO();
                    break;
                case "B":
                    removeO();
                    break;
                case "C":
                    searchO();
                    break;
                case "D":
                    modifyO();
                    break;
                case "E":
                   printO();
                case "F":
                    load();
                default:
                    break;
            }
        }while(!select.equals("Q"));

    }
    private static GoodManger goodManger = new GoodManger();
    private static OrderManager orderManager = new OrderManager();
    // 商品的增删改查
    private static void add(){
        System.out.println("请按照该格式添加商品: 牙刷-20");
        System.out.println("请输入商品的信息：");
        String messgae = input.nextLine();
        String[] arr = messgae.split("-");
        Goods goods = new Goods(arr[0],new BigDecimal(Double.parseDouble(arr[1])).setScale(2));
        if(goodManger.add(goods)){
            System.out.println("添加成功!!!");
            goodManger.show();
            return;
        }
        System.out.println("添加失败！！！");
        //System.out.println(goods);

    }
    private static void remove(){
        System.out.println("请输入要下架的商品的名称：");
        String name = input.nextLine();
       if( goodManger.remove(new Goods(name))){
           System.out.println("下架成功....");
           goodManger.show();
           return;
       }
        System.out.println("下架失败。。。");
    }
    private static boolean search(){
        System.out.println("请输入要查询的商品名称：");
        String name = input.nextLine();
        if(goodManger.search(new Goods(name)) != null){
            System.out.println(goodManger.search(new Goods(name)));
            return true;
        }else{
            System.out.println("没有该商品。。。");
        }
        return false;
    }
    private static void modify(){
        System.out.println("请输入旧商品名称：");
        String name = input.nextLine();
        if(goodManger.search(new Goods(name)) != null){
            System.out.println("请输入新的商品名称");
            name = input.nextLine();
            System.out.println("请输入价格：");
            double price = input.nextDouble();
            goodManger.modifyName(name,new BigDecimal(price).setScale(2));
            goodManger.show();
        }
    }
    private static void loadGoods(){
        File file = new File("good.txt");
        if(file.length() == 0){
            return;
        }
        try(Scanner load = new Scanner(new FileInputStream(file));){
            while(load.hasNext()){
                String str = load.nextLine();
                String[] arr = str.split("-");
                goodManger.add(new Goods(arr[0],new BigDecimal(arr[1])));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // 订单的增删改查
    private static void addO(){
        System.out.println("请按照如下格式输入：牙刷*2");
        String str = input.nextLine();
        String[] arr = str.split("\\*");
        BigDecimal price = goodManger.getPrice(new Goods(arr[0]));
        //System.out.println(price);
        if(price != null) {
            orderManager.add(new Goods(arr[0], price), Integer.parseInt(arr[1]));
            orderManager.print();
        }else{
            System.out.println("该商品还未上架。。。");
        }
    }
    private static void removeO(){
        System.out.println("请输入要删除的商品的名称：");
        String name = input.nextLine();
        if( orderManager.remove(new Goods(name))){
            System.out.println("删除成功....");
            orderManager.print();
            return;
        }
        System.out.println("删除失败。。。");
    }
    private static void searchO(){
        System.out.println("请输入要查询的商品名称：");
        String name = input.nextLine();
        BigDecimal price = goodManger.getPrice(new Goods(name));
        orderManager.search(new Goods(name,price));
    }
    private static void modifyO(){
        System.out.println("只能修改数量哦.");
        System.out.println("请输入要修改的商品名称：");
        String name = input.nextLine();
        BigDecimal price = goodManger.getPrice(new Goods(name));
        if(price != null){
            if(orderManager.search(new Goods(name,price))){
                System.out.println("请输入修改后的数量...");
                int count = input.nextInt();
                orderManager.modify(new Goods(name,price),count);
                orderManager.print();
            }else{
                System.out.println("订单中没有该商品...");
            }
        }else{
            System.out.println("该商品未上架...");
        }

    }
    private static void printO(){
        orderManager.print();
    }
    private static void load(){
        orderManager.load();
    }

    public static void main(String[] args) {
        while(true){
            menuIdenty();
        }
    }
}
