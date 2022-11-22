package com.example.Section3Project.controller.product;

import com.example.Section3Project.contants.FunctionUtils;
import com.example.Section3Project.model.Category;
import com.example.Section3Project.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Optional;

@WebServlet(name = "DeleteProductServletController", value = "/delete-product-servlet-controller")
public class DeleteProductServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String productId = request.getParameter("productId");

        FunctionUtils.products.removeIf(x -> x.getId().equals(productId));
        final String resquestURL = request.getContextPath() + "/home-servlet-controller";
        response.sendRedirect(resquestURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
