package com.github;

import java.util.Objects;

/**
 * @auther plg
 * @date 2019/5/5 21:51
 */
public class Goods {
    private int id = 0;
    private String name;
    private String price;

    public Goods(String name, String price) {
        this.name = name;
        this.price = price;
        this.id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void show(){
        System.out.println("============商品信息===========");
        System.out.println("Id    " + "      名称    " + "      价格");
        System.out.println(this.id + "         " + this.name + "         " + this.price);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(name, goods.name) &&
                Objects.equals(price, goods.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
