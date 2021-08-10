package com.ye.stock.order.dao;

import com.ye.stock.order.pojo.StockOrder;

public interface StockOrderDao {

    public void add(StockOrder st);

    public void del(StockOrder st);

    public StockOrder getStockOrder(StockOrder st);

}
