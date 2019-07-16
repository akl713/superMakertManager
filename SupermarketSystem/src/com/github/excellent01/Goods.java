package com.github.excellent01;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @auther plg
 * @date 2019/4/24 15:27
 */
public class Goods {
    private Integer id = 0;
    private String name;
    private BigDecimal price;
    private static int num = 0;
    public Goods(){}
    public Goods(String name){
        this.name = name;
    }
    public Goods(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        id = num++;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(name, goods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "com.github.excellent01.Goods{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
