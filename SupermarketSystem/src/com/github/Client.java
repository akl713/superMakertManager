package com.github;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
/**
 * @auther plg
 * @date 2019/5/5 22:37
 */
public class Client {
    private static Scanner input = new Scanner(System.in);
    private static String name = "plg";
    private static String word = "123456";
    private static GoodsManager goodsManager = new GoodsManager();
    private static OrderManager orderManager = new OrderManager();
    public static void menuIdenty() {
        Menu.selectIdentity();
        String select = null;
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
    public static void goodsMannger(){
        System.out.println("登陆成功..");
        goodsManager.beforeLoad();
        String select = null;
        Menu.GoodsMenu();
        do {
            System.out.println("请选择：");
            select = input.nextLine();
            switch(select){
                case "A":
                    goodsManager.add();
                    break;
                case "B":
                    goodsManager.delete();
                    break;
                case "C":
                   goodsManager.search();
                    break;
                case "D":
                    goodsManager.modify();
                    break;
                case"E":
                    goodsManager.show();
                    break;
                case"Q":
                    goodsManager.afterload();
                    break;
                default:
                    break;
            }
        }while(!select.equals("Q"));
    }
    private static void OrderMannger(){
        System.out.println("现有的商品：");
        goodsManager.show();
        String select = null;
        com.github.excellent01.Menu.OrderMenu();
        do {
            System.out.print("请选择：");
            select = input.nextLine();
            switch(select){
                case "A":
                    orderManager.addOrder();
                    break;
                case "B":
                   orderManager.deleteOrder();
                    break;
                case "C":
                    orderManager.searchOrder();
                    break;
                case "D":
                    orderManager.modifyOrder();
                    break;
                case "E":
                    orderManager.show();
                case "F":
                    orderManager.load();
                default:
                    break;
            }
        }while(!select.equals("Q"));

    }
    public static void main(String[] args) {
        while(true){
            menuIdenty();
        }
    }

}
