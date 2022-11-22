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

@WebServlet(name = "CreateProductServletController", value = "/create-product-servlet-controller")
public class CreateProductServletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Create Product</h1>");
        out.println("<form action='/Section3Project/create-product-servlet-controller' method='get'>");
        out.println("<label for='productID'>ID product:</label>");
        out.println("<input type='text' id='productID' name='productID' placeholder='Input ID product' autofocus required><br><br>");
        out.println("<label for='productName'>Name product:</label>");
        out.println("<input type='text' id='productName' name='productName' placeholder='Input name product' required><br><br>");
        out.println("<label for='productPrice'>Price product:</label>");
        out.println("<input type='number' id='productPrice' name='productPrice' placeholder='Input price product' required><br><br>");
        out.println("<label for='productDesc'>Description product:</label>");
        out.println("<input type='text' id='productDesc' name='productDesc' placeholder='Input description product' required><br><br>");
        out.println("<label>Product status:</label>");
        out.println("<input type='radio' id='available' name='productStatus' value='available' checked>");
        out.println("<label for='available'>Available product:</label>");
        out.println("<input type='radio' id='notavailable' name='productStatus' value='notavailable'>");
        out.println("<label for='notavailable'>Not available product:</label><br><br>");
        out.println("<label for='productCategory'>Category product:</label>");
        out.println("<select name='productCategory' id='productCategory'>");
        for (Category cat : FunctionUtils.categories) {
            out.println("<option value='" + cat.getId() + "'>" + cat.getName() + "</option>");
        }
        out.println("</select><br><br>");
        out.println(" <input type='submit' value='Create Product'>");
        out.println("</form>");

        String productId = request.getParameter("productID");
        String productName = request.getParameter("productName");
        Integer productPrice = Integer.parseInt(request.getParameter("productPrice"));
        String productDesc = request.getParameter("productDesc");
        Boolean productStatus = request.getParameter("productStatus") == "available" ? true : false;
        Category productCategory = FunctionUtils.categories.stream()
                .filter(x -> x.getId().equals(request.getParameter("productCategory")))
                .findFirst().get();

        Optional<Product> product = FunctionUtils.products.stream().filter(x -> productId != null && x.getId().equals(productId)).findFirst();

        if (product.isPresent()) {
            out.println("<script>");
            out.println("alert('ID product is exit. Please input again!');");
            out.println("</script>");
        } else if ( productName != null && productPrice != null && productDesc != null && productStatus != null) {
            FunctionUtils.products.add(new Product(productId, productName, productPrice, productDesc, new Date(), new Date(), productStatus,productCategory));
            final String resquestURL = request.getContextPath() + "/home-servlet-controller";
            response.sendRedirect(resquestURL);
        }


        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
