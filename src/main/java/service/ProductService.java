package service;

import dao.ProductDAO;
import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public ArrayList<Product> getProducts() {
        return productDAO.getAllProduct();
    }

    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    public void createProduct(Product product) {
    }

    public void deleteProduct(int id) throws SQLException {
    }

    public void editProduct(String id, String nameProduct, String price, String quantity,
                               String color, String description, String category) throws SQLException {

    }

    public ArrayList<Category> getCategories() {
        return productDAO.getAllCategory();
    }
}
