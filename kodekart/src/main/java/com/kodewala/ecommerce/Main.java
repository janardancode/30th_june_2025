package com.kodewala.ecommerce;

import java.util.List;
import java.util.Scanner;

import com.kodewala.dao.CartDao;
import com.kodewala.dao.OrderDao;
import com.kodewala.dao.ProductDao;
import com.kodewala.dao.UserDao;
import com.kodewala.model.Cart;
import com.kodewala.model.Order;
import com.kodewala.model.Product;
import com.kodewala.model.User;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserDao userDao = new UserDao();
    static ProductDao productDao = new ProductDao();
    static CartDao cartDao = new CartDao();
    static OrderDao orderDao = new OrderDao();
    static User currentUser;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- kodekart E-commerce ---");
            System.out.println("1. Register\n2. Login\n3. Exit\nChoose option:");
            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Exiting... bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void register() {
        System.out.println("== User Registration ==");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Password: ");
        String pwd = scanner.nextLine();

        User user = new User(0, name, email, phone, pwd, "user");
        boolean success = userDao.registerUser(user);
        System.out.println(success ? "Registration successful." : "Registration failed.");
    }

    static void login() {
        System.out.println("== User Login ==");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String pwd = scanner.nextLine();

        currentUser = userDao.loginUser(email, pwd);
        if (currentUser == null) {
            System.out.println("Invalid login credentials.");
            return;
        }
        System.out.println("Welcome, " + currentUser.getName() + ". Role: " + currentUser.getRole());

        if ("admin".equalsIgnoreCase(currentUser.getRole()))
            adminMenu();
        else
            userMenu();
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n-- Admin Menu --");
            System.out.println("1. Add Product\n2. Update Product\n3. Delete Product\n4. View All Products\n5. View All Orders\n6. Logout");
            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    updateProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    viewProducts();
                    break;
                case "5":
                    viewAllOrders();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void userMenu() {
        while (true) {
            System.out.println("\n-- User Menu --");
            System.out.println("1. View Products\n2. Search Products\n3. Add to Cart\n4. View Cart\n5. Remove from Cart\n6. Place Order\n7. View Order History\n8. Logout");
            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    viewProducts();
                    break;
                case "2":
                    searchProducts();
                    break;
                case "3":
                    addToCart();
                    break;
                case "4":
                    viewCart();
                    break;
                case "5":
                    removeFromCart();
                    break;
                case "6":
                    placeOrder();
                    break;
                case "7":
                    viewOrderHistory();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ---------------- Admin methods ----------------
    static void addProduct() {
        System.out.println("== Add Product ==");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Description: ");
        String desc = scanner.nextLine();

        Product p = new Product(0, name, category, price, qty, desc);
        boolean ok = productDao.addProduct(p);
        System.out.println(ok ? "Product added." : "Add failed.");
    }

    static void updateProduct() {
        System.out.println("== Update Product ==");
        System.out.print("Product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product existing = productDao.getProductById(id);
        if (existing == null) {
            System.out.println("Product not found.");
            return;
        }
        System.out.println("Leave blank to keep existing value.");
        System.out.print("Name (" + existing.getName() + "): ");
        String name = scanner.nextLine(); if (!name.isBlank()) existing.setName(name);
        System.out.print("Category (" + existing.getCategory() + "): ");
        String category = scanner.nextLine(); if (!category.isBlank()) existing.setCategory(category);
        System.out.print("Price (" + existing.getPrice() + "): ");
        String priceStr = scanner.nextLine(); if (!priceStr.isBlank()) existing.setPrice(Double.parseDouble(priceStr));
        System.out.print("Quantity (" + existing.getQuantity() + "): ");
        String qStr = scanner.nextLine(); if (!qStr.isBlank()) existing.setQuantity(Integer.parseInt(qStr));
        System.out.print("Description (" + existing.getDescription() + "): ");
        String desc = scanner.nextLine(); if (!desc.isBlank()) existing.setDescription(desc);

        boolean ok = productDao.updateProduct(existing);
        System.out.println(ok ? "Product updated." : "Update failed.");
    }

    static void deleteProduct() {
        System.out.println("== Delete Product ==");
        System.out.print("Product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean ok = productDao.deleteProduct(id);
        System.out.println(ok ? "Deleted." : "Delete failed or product not found.");
    }

    static void viewAllOrders() {
        System.out.println("== All Orders ==");
        List<Order> orders = orderDao.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        for (Order o : orders) {
            System.out.println(o); // ensure Order.toString() is meaningful, or print fields
        }
    }

    // ---------------- User methods ----------------
    static void viewProducts() {
        System.out.println("== Products ==");
        List<Product> list = productDao.getAllProducts();
        if (list.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product p : list) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategory() + " | price: " + p.getPrice() + " | qty: " + p.getQuantity());
        }
    }

    static void searchProducts() {
        System.out.println("Search by: 1. Name  2. Category");
        String opt = scanner.nextLine();
        if ("1".equals(opt)) {
            System.out.print("Enter name keyword: ");
            String kw = scanner.nextLine();
            List<Product> res = productDao.searchByName(kw);
            printProductList(res);
        } else if ("2".equals(opt)) {
            System.out.print("Enter category keyword: ");
            String kw = scanner.nextLine();
            List<Product> res = productDao.searchByCategory(kw);
            printProductList(res);
        } else {
            System.out.println("Invalid option.");
        }
    }

    static void printProductList(List<Product> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : list) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategory() + " | price: " + p.getPrice() + " | qty: " + p.getQuantity());
        }
    }

    static void addToCart() {
        if (!requireLogin()) return;
        System.out.print("Enter product id: ");
        int pid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        boolean ok = cartDao.addToCart(currentUser.getId(), pid, qty);
        System.out.println(ok ? "Added to cart." : "Failed to add to cart (check stock).");
    }

    static void viewCart() {
        if (!requireLogin()) return;
        List<Cart> items = cartDao.getCartItems(currentUser.getId());
        if (items == null || items.isEmpty()) {
            System.out.println("Cart empty.");
            return;
        }
        double total = 0;
        for (Cart c : items) {
            Product p = productDao.getProductById(c.getProductId());
            double line = (p != null ? p.getPrice() : 0) * c.getQuantity();
            total += line;
            System.out.println("ProductId: " + c.getProductId() + " | qty: " + c.getQuantity() + " | price: " + (p != null ? p.getPrice() : "N/A") + " | line: " + line);
        }
        System.out.println("Cart total: " + total);
    }

    static void removeFromCart() {
        if (!requireLogin()) return;
        System.out.print("Enter product id to remove: ");
        int pid = Integer.parseInt(scanner.nextLine());
        boolean ok = cartDao.removeFromCart(currentUser.getId(), pid);
        System.out.println(ok ? "Removed from cart." : "Remove failed or item not in cart.");
    }

    static void placeOrder() {
        if (!requireLogin()) return;
        boolean ok = orderDao.placeOrder(currentUser.getId());
        System.out.println(ok ? "Order placed." : "Order failed.");
    }

    static void viewOrderHistory() {
        if (!requireLogin()) return;
        List<Order> orders = orderDao.getUserOrders(currentUser.getId());
        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // ---------------- Helpers ----------------
    static boolean requireLogin() {
        if (currentUser == null) {
            System.out.println("You must login first.");
            return false;
        }
        return true;
    }
}
