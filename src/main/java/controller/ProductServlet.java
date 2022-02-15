package controller;

import dao.ProductDAO;
import model.Category;
import model.Product;
import regex.Validate;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    Validate validate = new Validate();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "createGet":
                    createGet(request, response);
                    break;
                case "createPost":
                    createPost(request, response);
                    break;
                case "editGet":
                    editGet(request, response);
                    break;
                case "editPost":
                    editPost(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
                default:
                    displayAllProduct(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter(request.getParameter("search"));
        ArrayList<Product> products = productService.getProducts();
        ArrayList<Product> productsOfSearch = new ArrayList<>();
        for (Product product : products) {
            if (validate.validateNameProduct(search, product.getNameProduct())) {
                productsOfSearch.add(product);
            }
        }
        request.setAttribute("products", productsOfSearch);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_product = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id_product);
        displayAllProduct(request, response);
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id_product = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String id_category = request.getParameter("category");
        ProductDAO productDAO = new ProductDAO();
        productDAO.updateProduct(id_product,name, price, quantity, color, description,id_category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void editGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_product = Integer.parseInt(request.getParameter("id"));
        Product productEdit = productService.getProduct(id_product);
        ArrayList<Category> categories = productService.getCategories();
        int id_category = -1;
        for (Category category : categories) {
            if (category.getNameCategory().equals(productEdit.getCategory())) {
                id_category = category.getIdCategory();
            }
        }
        request.setAttribute("id_category", id_category);
        request.setAttribute("productEdit", productEdit);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String id_category = request.getParameter("category");
        ProductDAO productDAO = new ProductDAO();
        productDAO.createProduct(name, price, quantity, color, description,id_category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> categories = productService.getCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> products = productService.getProducts();
        ProductDAO productDAO = new ProductDAO();
        List<Category> categories = productDAO.getAllCategory();
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        requestDispatcher.forward(request, response);
    }
}
