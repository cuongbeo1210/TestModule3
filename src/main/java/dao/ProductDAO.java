package dao;

import context.DBContext;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO implements IProductDAO {
    private final DBContext DBContext = new DBContext();

    private static final String SELECT_ALL_PRODUCTS = "select idProduct, nameProduct, price, quantity, color, description, category from testmodule3.product join category on product.category = category.idCategory;";
    private static final String SELECT_ALL_CATEGORIES = "select * from testmodule3.category;";
    private static final String SELECT_PRODUCT_BY_ID = "select idProduct, nameProduct, price, quantity, color, description, category from product join category on product.category = category.idCategory where idProduct = ?;";
    private static final String INSERT_PRODUCT_SQL = "insert into testmodule3.product (nameProduct, price, quantity, color, description, category) value (?,?,?,?,?,?);";
    private static final String DELETE_PRODUCT_SQL = "delete from product where idProduct = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update product set nameProduct = ?, price = ?, quantity = ?, color = ?, description = ?, category = ? where idProduct = ?;";

    @Override
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_product = rs.getInt(1);
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                String color = rs.getString(5);
                String description = rs.getString(6);
                int category = rs.getInt(7);
                products.add(new Product(id_product, name, price, quantity, color, description, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_category = rs.getInt("idCategory");
                String name_category = rs.getString("nameCategory");
                categories.add(new Category(id_category, name_category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Product getProduct(int id) {
        Product product = null;
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("nameProduct");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category = rs.getInt("category");
                product = new Product(id, name, price, quantity, color, description, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void createProduct(String name, String price, String quantity, String color, String description, String category) {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, price);
            preparedStatement.setString(3, quantity);
            preparedStatement.setString(4, color);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(String id, String nameProduct, String price, String quantity,
                              String color, String description, String category) throws SQLException {
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, nameProduct);
            statement.setString(2, price);
            statement.setString(3, quantity);
            statement.setString(4, color);
            statement.setString(5, description);
            statement.setString(6, category);
            statement.setString(7, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
