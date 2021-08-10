package com.ye.stock.order.pojo;


import lombok.Data;

@Data
public class StockOrder {
    private String code;
    private String name;
    private String num;
    private String price;
}
