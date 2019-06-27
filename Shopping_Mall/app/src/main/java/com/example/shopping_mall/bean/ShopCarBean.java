package com.example.shopping_mall.bean;

public class ShopCarBean {

  private String name;
  private double price;
  private int count;
  public boolean isCheck = false;
  public boolean isChoosed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public ShopCarBean(String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }
}
