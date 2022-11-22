package com.example.Section3Project.controller;

import com.example.Section3Project.contants.FunctionUtils;
import com.example.Section3Project.model.Category;
import com.example.Section3Project.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "HomeServletController", value = "/home-servlet-controller")
public class HomeServletController extends HttpServlet {

    private List<Category> categories;
    private List<Product> products;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body style=''>");
        out.println("<h1>HomeServletController</h1>");

        out.println("<div style='display: flex; justify-content: space-between; align-items: center'>");
        out.println("<h3>Product List</h3>");
        out.println("<a href=" + request.getContextPath() + "/create-product-servlet-controller" + ">Add new product</a>");
        out.println("</div>");

        out.println("<ul style='display: flex; flex-wrap:wrap; list-style: none; padding: 0'>");
        for (Product pro : products) {
            out.print("<li style='margin: 20px;'>");
            out.print("<div>Product id:" + pro.getId() + "</div>");
            out.print("<div>Product name:" + pro.getName() + "</div>");
            out.print("<div>Product desc:" + pro.getDescription() + "</div>");
            out.print("<div>Product crTime:" + simpleDateFormat.format(pro.getCreatedTime()) + "</div>");
            out.print("<div>Product updTime:" + simpleDateFormat.format(pro.getUpdatedTime()) + "</div>");
            out.print("<div>Product price:" + pro.getPrice() + "</div>");
            out.print("<div>Product status:" + (pro.getStatus() ? "available" : "notavailable") + "</div>");

            Optional<Category> category = categories.stream().filter(x -> pro.getCategory().getId() != null && pro.getCategory().getId().equals(x.getId())).findFirst();
            if (category.isPresent()) {
                out.print("<div>Category:" + category.get().getName() + "</div>");
            } else {
                out.print("<div>There is not such Category" + "</div>");
            }
            final String resquestURL = request.getContextPath() + "/detail-servlet-controller?productId=" + pro.getId();
            out.print("<a href='" + resquestURL + "'>Click to detail product</a>");
            out.print("</li>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {

        categories = FunctionUtils.listCategories();
        products = FunctionUtils.listProducts();

    }


}
