package com.github;

/**
 * @auther plg
 * @date 2019/4/24 16:12
 */
public class Menu {
    public static void selectIdentity(){
        System.out.println("**************************************");
        System.out.println("*************请选择身份***************");
        System.out.println("***A:管理人员***********B:客户********");
        System.out.println("**************Q:退出******************");
    }
    public static void GoodsMenu(){
        System.out.println("*************************************");
        System.out.println("***************商品管理****************");
        System.out.println("*************A:添加商品***************");
        System.out.println("*************B:删除商品****************");
        System.out.println("*************C:查找商品***************");
        System.out.println("*************D:修改商品***************");
        System.out.println("*************E:展示商品***************");
        System.out.println("**************Q:退出******************");
        System.out.println("**************************************");
    }
    public static void OrderMenu(){
        System.out.println("*************************************");
        System.out.println("***************订单管理****************");
        System.out.println("*************A:购买商品***************");
        System.out.println("*************B:删除商品****************");
        System.out.println("*************C:查看信息***************");
        System.out.println("*************D:修改订单***************");
        System.out.println("*************E:打印订单****************");
        System.out.println("*************F:订单下载****************");
        System.out.println("**************Q:退出******************");
        System.out.println("**************************************");
    }
}
