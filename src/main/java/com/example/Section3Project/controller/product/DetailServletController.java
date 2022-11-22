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

@WebServlet(name = "DetailServletController", value = "/detail-servlet-controller")
public class DetailServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Detail Product</h1>");
        String productId = request.getParameter("productId");
        out.println("<h2>Product Id: " + productId + "</h2>");

//        Get and Show Product
        Optional<Product> product = FunctionUtils.products.stream()
                .filter(x -> productId != null && productId.equals(x.getId()))
                .findFirst();
        out.print("<div>Product name:" + product.get().getName() + "</div>");
        out.print("<div>Product desc:" + product.get().getDescription() + "</div>");
        out.print("<div>Product crTime:" + simpleDateFormat.format(product.get().getCreatedTime()) + "</div>");
        out.print("<div>Product updTime:" + simpleDateFormat.format(product.get().getUpdatedTime()) + "</div>");
        out.print("<div>Product price:" + product.get().getPrice() + "</div>");
        out.print("<div>Product status:" + (product.get().getStatus() ? "available" : "notavailable") + "</div>");

        Optional<Category> category = FunctionUtils.categories.stream().filter(x -> product.get().getCategory().getId() != null && product.get().getCategory().getId().equals(x.getId())).findFirst();
        if (category.isPresent()) {
            out.print("<div>Category:" + category.get().getName() + "</div>");
        } else {
            out.print("<div>There is not such Category" + "</div>");
        }

//        Button Edit product
        final String resquestURL = request.getContextPath() + "/edit-product-servlet-controller?productId=" + productId;
        out.print("<a href='" + resquestURL + "'>Click to edit product</a><br><br>");

//        Button Del product
        out.println("<button onclick='deleteProductFunction()'>Delete Product</button>");
        out.println("<script>");
        out.println("function deleteProductFunction() {" +
                " let text = 'Are you sure to Delete product?';" +
                "if (confirm(text) == true) {" +
                "   window.location.href = '/Section3Project/delete-product-servlet-controller?productId=" + productId + "';" +
                "}" +
                "}");

        out.println("</script>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
