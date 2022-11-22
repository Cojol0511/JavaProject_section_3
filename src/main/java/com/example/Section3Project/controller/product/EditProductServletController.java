package com.example.Section3Project.controller.product;

import com.example.Section3Project.contants.FunctionUtils;
import com.example.Section3Project.model.Category;
import com.example.Section3Project.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "EditProductServletController", value = "/edit-product-servlet-controller")
public class EditProductServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Create Product</h1>");

        String productId = request.getParameter("productId");
        out.println("<h2>Product Id: " + productId + "</h2>");
        Optional<Product> product = FunctionUtils.products.stream()
                .filter(x -> productId != null && productId.equals(x.getId()))
                .findFirst();

        out.println("<form action='/Section3Project/edit-product-servlet-controller' method='post'>");
        out.println("<input type='hidden' id='productID' name='productID' placeholder='Input ID product' value='" + product.get().getId() + "'  required><br><br>");
        out.println("<label for='productName'>Name product:</label>");
        out.println("<input type='text' id='productName' name='productName' placeholder='Input name product' value='" + product.get().getName() + "' autofocus required><br><br>");
        out.println("<label for='productPrice'>Price product:</label>");
        out.println("<input type='number' id='productPrice' name='productPrice' placeholder='Input price product' value='" + product.get().getPrice() + "'required><br><br>");
        out.println("<label for='productDesc'>Description product:</label>");
        out.println("<input type='text' id='productDesc' name='productDesc' placeholder='Input description product' value='" + product.get().getDescription() + "'required><br><br>");
        out.println("<label>Product status:</label>");
        out.println("<input type='radio' id='available' name='productStatus' value='available'" + (product.get().getStatus() == true ? "checked" : "") + ">");
        out.println("<label for='available'>Available product:</label>");
        out.println("<input type='radio' id='notavailable' name='productStatus' value='notavailable'" + (product.get().getStatus() == false ? "checked" : "") + ">");
        out.println("<label for='notavailable'>Not available product:</label><br><br>");
        out.println("<label for='productCategory'>Category product:</label>");
        out.println("<select name='productCategory' id='productCategory'>");
        for (Category cat : FunctionUtils.categories) {
            out.println("<option value='" + cat.getId() + "' " + (product.get().getCategory().getId().equals(cat.getId()) ? "selected" : "") + ">" + cat.getName() + "</option>");
        }
        out.println("</select><br><br>");
        out.println(" <input type='submit' value='Update Product'>");
        out.println("</form>");


        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productID");
        Optional<Product> product = FunctionUtils.products.stream()
                .filter(x -> x.getId().equals(productId))
                .findFirst();

        String productName = request.getParameter("productName");
        Integer productPrice = Integer.parseInt(request.getParameter("productPrice"));
        String productDesc = request.getParameter("productDesc");
        Boolean productStatus = request.getParameter("productStatus") == "available" ? true : false;
        Category productCategory = FunctionUtils.categories.stream()
                .filter(x -> x.getId().equals(request.getParameter("productCategory")))
                .findFirst().get();

//            Edit product
        product.get().setName(productName);
        product.get().setPrice(productPrice);
        product.get().setDescription(productDesc);
        product.get().setStatus(productStatus);
        product.get().setUpdatedTime(new Date());
        product.get().setCategory(productCategory);

        final String resquestURL = request.getContextPath() + "/home-servlet-controller";
        response.sendRedirect(resquestURL);

    }
}
