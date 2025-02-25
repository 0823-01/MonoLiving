package com.kh.order.model.service;

import java.util.ArrayList;

import com.kh.order.model.dao.OrderDao;
import com.kh.order.model.vo.Order;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    // 주문 목록을 가져오는 메서드 추가
    public ArrayList<Order> getAllOrders() {
        return orderDao.selectAllOrders(); // OrderDao의 메서드 호출
    }
}
