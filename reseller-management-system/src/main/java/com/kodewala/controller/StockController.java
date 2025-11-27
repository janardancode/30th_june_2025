package com.kodewala.controller;

import com.kodewala.model.Stock;
import com.kodewala.model.Reseller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StockController {

    private static final List<Stock> STOCKS = new ArrayList<>();
    private static int stockSeq = 1;

    private Reseller getCurrentLoggedInReseller() {
        return ResellerController.currentLoggedInReseller;
    }

    @GetMapping("/stocks")
    public String listStocks(Model model) {
        Reseller r = getCurrentLoggedInReseller();
        if (r == null) return "redirect:/login";

        List<Stock> myStocks = new ArrayList<>();
        for (Stock s : STOCKS) {
            if (s.getResellerId() == r.getId()) myStocks.add(s);
        }

        System.out.println("=== Listing Stocks for Reseller id=" + r.getId() + " (" + r.getEmail() + ") ===");
        for (Stock s : myStocks) {
            System.out.println("  Stock id=" + s.getId() + " name=" + s.getProductName() + " qty=" + s.getQuantity());
        }

        model.addAttribute("stocks", myStocks);
        model.addAttribute("reseller", r);
        return "stocks-list";
    }

    @GetMapping("/stocks/new")
    public String newStockForm(Model model) {
        if (getCurrentLoggedInReseller() == null) return "redirect:/login";
        model.addAttribute("stock", new Stock());
        return "stock-form";
    }

    @PostMapping("/stocks/save")
    public String saveStock(@ModelAttribute Stock stock) {
        Reseller r = getCurrentLoggedInReseller();
        if (r == null) return "redirect:/login";

        if (stock.getId() == 0) {
            stock.setId(stockSeq++);
            stock.setResellerId(r.getId());   
            STOCKS.add(stock);

            System.out.println("=== New Stock Created ===");
            System.out.println(" ResellerId: " + stock.getResellerId());
            System.out.println(" Id: " + stock.getId());
            System.out.println(" Product: " + stock.getProductName());
            System.out.println(" Qty: " + stock.getQuantity());
            System.out.println(" Price: " + stock.getPrice());
        } else {
            for (Stock s : STOCKS) {
                if (s.getId() == stock.getId() && s.getResellerId() == r.getId()) {
                    s.setProductName(stock.getProductName());
                    s.setQuantity(stock.getQuantity());
                    s.setPrice(stock.getPrice());
                    System.out.println("=== Stock Updated Id: " + s.getId() + " ===");
                    break;
                }
            }
        }
        return "redirect:/stocks";
    }

    @GetMapping("/stocks/edit")
    public String editStock(@RequestParam("id") int id, Model model) {
        Reseller r = getCurrentLoggedInReseller();
        if (r == null) return "redirect:/login";

        Stock target = null;
        for (Stock s : STOCKS) {
            if (s.getId() == id && s.getResellerId() == r.getId()) {
                target = s;
                break;
            }
        }
        if (target == null) return "redirect:/stocks";
        model.addAttribute("stock", target);
        return "stock-form";
    }

    @GetMapping("/stocks/delete")
    public String deleteStock(@RequestParam("id") int id) {
        Reseller r = getCurrentLoggedInReseller();
        if (r == null) return "redirect:/login";

        Stock toDelete = null;
        for (Stock s : STOCKS) {
            if (s.getId() == id && s.getResellerId() == r.getId()) {
                toDelete = s;
                break;
            }
        }
        if (toDelete != null) {
            STOCKS.remove(toDelete);
            System.out.println("=== Stock Deleted Id: " + toDelete.getId());
        }
        return "redirect:/stocks";
    }
}
