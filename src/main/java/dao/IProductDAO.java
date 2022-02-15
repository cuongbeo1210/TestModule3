package dao;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductDAO {
    ArrayList<Product> getAllProduct();

    ArrayList<Category> getAllCategory();

    Product getProduct(int id);

    void createProduct(String name, String price, String quantity, String color, String description, String category);

    void deleteProduct(int id) throws SQLException;


    void updateProduct(String id, String category_id, String title, String price,
                       String discount, String thumbnail, String description) throws SQLException;
}
