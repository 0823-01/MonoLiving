package com.kh.order.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.order.model.service.OrderService;

import java.io.IOException;

@WebServlet("/updateOrderStatus.me")
public class UpdateOrderStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String status = request.getParameter("status");

        OrderService orderService = new OrderService();
        boolean isUpdated = orderService.updateOrderStatus(orderId, status);

        if (isUpdated) {
            // 업데이트가 성공한 경우
            request.setAttribute("message", "상태가 성공적으로 업데이트되었습니다.");
        } else {
            // 업데이트 실패한 경우
            request.setAttribute("message", "상태 업데이트에 실패했습니다.");
        }

        // 주문 관리 페이지로 리디렉션 (메시지를 포함)
        request.getRequestDispatcher("/views/product/OrderMange.jsp").forward(request, response);
    }
}
