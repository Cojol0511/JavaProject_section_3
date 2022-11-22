package com.example.Section3Project.contants;

import com.example.Section3Project.model.Category;
import com.example.Section3Project.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FunctionUtils {
    public static List<Category> categories;
    public static List<Product> products;

    public static List<Category> listCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("idc1", "Audi", "desc_Audi"));
        categories.add(new Category("idc2", "Mecx", "desc_Mecx"));
        categories.add(new Category("idc3", "Ford", "desc_Ford"));
        categories.add(new Category("idc4", "Vinf", "desc_Vinf"));
        return categories;
    }

    public static List<Product> listProducts() {
        products = new ArrayList<>();
        products.add(new Product("idp1", "BenL_1", 05, "desc_Audi_1", new Date(), new Date(), true, categories.get(0)));
        products.add(new Product("idp2", "Audi_2", 10, "desc_Audi_2", new Date(), new Date(), false, categories.get(0)));
        products.add(new Product("idp3", "Mecx_1", 15, "desc_Mecx_1", new Date(), new Date(), true, categories.get(1)));
        products.add(new Product("idp4", "Mecx_2", 20, "desc_Mecx_2", new Date(), new Date(), false, categories.get(1)));
        products.add(new Product("idp5", "Ford_1", 25, "desc_Ford_1", new Date(), new Date(), true, categories.get(2)));
        products.add(new Product("idp6", "Vinf_1", 30, "desc_Vinf_1", new Date(), new Date(), true, categories.get(3)));
        return products;
    }

}
