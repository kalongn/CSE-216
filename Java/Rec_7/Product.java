package Java.Rec_7;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Product implements Comparable<Product> {
    String productCategory;
    int productCost;
    String productName;

    public Product(String productCategory, int productCost, String productName) {
        this.productCategory = productCategory;
        this.productCost = productCost;
        this.productName = productName;
    }

    @Override
    public int compareTo(Product o) {
        if (this.productCost > o.productCost) {
            return 1;
        } else if (this.productCost == o.productCost) {
            if (this.productCategory.compareTo(o.productCategory) == 0) {
                return this.productName.compareTo(o.productName);
            }
            return this.productCategory.compareTo(o.productCategory);
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return this.productName + " " + this.productCategory + " " + this.productCost;
    }

    public static void main(String[] args) {
        Product test = new Product("A", 0, "Za");
        Product test2 = new Product("C", 0, "Da");
        Product test3 = new Product("B", 2, "Ka");

        List<Product> productList = new ArrayList<>();
        productList.add(test);
        productList.add(test2);
        productList.add(test3);
        Collections.sort(productList);
        for (Product i : productList) {
            System.out.println(i);
        }
    }

}