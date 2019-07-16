package com.github;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @auther plg
 * @date 2019/5/5 21:54
 */
public class GoodsManager {
    Scanner input = new Scanner(System.in);
    private HashSet<Goods> goodsSet = new HashSet<>();
    private boolean addGoods(Goods goods){
        goodsSet.add(goods);
        return true;
    }
    private boolean deleteGoods(Goods goods){
        if(goodsSet.contains(goods)){
            goodsSet.remove(goods);
            return true;
        }else{
            System.out.println("该商品未上架。。。。");
            return false;
        }
    }
    private boolean modifyGoods(Goods newGoods,Goods oldGoods){
        if(goodsSet.contains(oldGoods)){
            deleteGoods(oldGoods);
            goodsSet.add(newGoods);
            return true;

        }else{
            System.out.println("该商品未上架。。。。");
            return false;
        }
    }
    private boolean searchGoods(Goods goods){
        if(goodsSet.contains(goods)){
            System.out.println("===============查找成功==============");
            goods.show();
            return true;
        }else{
            System.out.println("该商品未上架。。。。。");
            return false;
        }
    }
    public void add(){
        System.out.println("请输入商品信息按照如下格式：");
        System.out.println("名称--价钱");
        String str = input.nextLine();
        String[] arr = str.split("--");
        Goods goods = new Goods(arr[0],arr[1]);
        if(this.addGoods(goods)){
            this.show();
        }
    }
    public void delete(){

        System.out.println("请输入要删除的商品信息按照如下格式：");
        System.out.println("名称--价钱");
        String str = input.nextLine();
        String[] arr = str.split("--");
        Goods goods = new Goods(arr[0],arr[1]);
       if( this.deleteGoods(goods)){
           System.out.println("===============删除成功==============");
           this.show();
       }
    }
    public void modify(){
        System.out.println("请输入修改前的商品信息按照如下格式：");
        System.out.println("名称--价钱");
        String str1 = input.nextLine();
        String[] arr1 = str1.split("--");
        Goods oldGoods = new Goods(arr1[0],arr1[1]);
        System.out.println("请输入修改后的商品信息按照如下格式：");
        System.out.println("名称--价钱");
        String str2 = input.nextLine();
        String[] arr2 = str2.split("--");
        Goods newGoods = new Goods(arr2[0],arr2[1]);
        if(this.modifyGoods(newGoods,oldGoods)){
            System.out.println("===============修改成功==============");
            this.show();
        }
    }
    public void search(){
        System.out.println("请输入要查询的商品信息按照如下格式：");
        System.out.println("名称--价钱");
        String str = input.nextLine();
        String[] arr = str.split("--");
        Goods goods = new Goods(arr[0],arr[1]);
        this.searchGoods(goods);
    }
    public void show(){
        int i = 0;
        System.out.println("===============商品信息==============");
        System.out.println("商品id          商品名称        单价");
        for(Goods goods : goodsSet){
            System.out.println("   " + (i++) + "             " + goods.getName() + "         " + goods.getPrice()+"元");
        }
    }
    public void beforeLoad(){
        File file = new File("good.txt");
        if(file.length() == 0){
            return;
        }
        try(Scanner load = new Scanner(new FileInputStream(file))){
            while(load.hasNext()){
                String str = load.nextLine();
                String[] arr = str.split("--");
                Goods goods = new Goods(arr[0],arr[1]);
                goodsSet.add(goods);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void afterload(){
        File file = new File("good.txt");
        try(Writer out = new FileWriter(file)){
            for(Goods goods : goodsSet){
                out.write(goods.getName() + "--" + goods.getPrice() + "\n");
            }
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
