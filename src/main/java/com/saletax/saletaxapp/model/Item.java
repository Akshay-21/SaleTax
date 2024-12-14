package com.saletax.saletaxapp.model;

import com.saletax.saletaxapp.constant.ProductType;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "item")  // Updated table name
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private boolean isImport;
    private ProductType type;

    public Item(String name, double price, boolean isImport, ProductType type) {
        this.name = name;
        this.price = price;
        this.isImport = isImport;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isImport() {
        return isImport;
    }

    public void setImport(boolean anImport) {
        isImport = anImport;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double calculateSalesTax() {
        double basicTax = 0;

        if (!isExempt()) {
            basicTax = price * 0.10;
        }

        if (isImport()) {
            basicTax += price * 0.05 ;
        }

        return roundUp(basicTax);
    }

    public boolean isExempt() {
        return type.equals(ProductType.BOOK) || type.equals(ProductType.FOOD) || type.equals(ProductType.MEDICAL);
    }

    private double roundUp(double taxValue) {
        return Math.ceil(taxValue * 20.0) / 20.0;
    }

    public double getTotalPrice() {
        return price + calculateSalesTax();
    }
}
