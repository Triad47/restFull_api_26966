package auca.ac.rw.question4ecommerceapi.controller.ecommerce;

import auca.ac.rw.question4ecommerceapi.model.ecommerce.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "iPhone 14", "Apple smartphone", 1200.0, "Electronics", 10, "Apple"));
        products.add(new Product(2L, "Samsung Galaxy S23", "Samsung smartphone", 1100.0, "Electronics", 15, "Samsung"));
        products.add(new Product(3L, "HP Laptop", "HP Core i7 Laptop", 900.0, "Computers", 5, "HP"));
        products.add(new Product(4L, "Dell XPS", "Dell premium laptop", 1300.0, "Computers", 0, "Dell"));
        products.add(new Product(5L, "Sony Headphones", "Noise cancelling headphones", 250.0, "Accessories", 20, "Sony"));
        products.add(new Product(6L, "Nike Shoes", "Running shoes", 120.0, "Fashion", 30, "Nike"));
        products.add(new Product(7L, "Adidas T-Shirt", "Sport t-shirt", 35.0, "Fashion", 50, "Adidas"));
        products.add(new Product(8L, "LG TV", "55 inch smart TV", 700.0, "Electronics", 8, "LG"));
        products.add(new Product(9L, "Canon Camera", "DSLR Camera", 650.0, "Electronics", 12, "Canon"));
        products.add(new Product(10L, "Lenovo ThinkPad", "Business laptop", 950.0, "Computers", 6, "Lenovo"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ) {
        if (page == null || limit == null) {
            return ResponseEntity.ok(products);
        }

        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, products.size());

        if (startIndex >= products.size() || startIndex < 0) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(products.subList(startIndex, endIndex));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product found = findById(productId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory() != null && p.getCategory().equalsIgnoreCase(category)) {
                results.add(p);
            }
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand() != null && p.getBrand().equalsIgnoreCase(brand)) {
                results.add(p);
            }
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> results = new ArrayList<>();

        for (Product p : products) {
            if ((p.getName() != null && p.getName().toLowerCase().contains(keyword.toLowerCase())) ||
                    (p.getDescription() != null && p.getDescription().toLowerCase().contains(keyword.toLowerCase()))) {
                results.add(p);
            }
        }

        return ResponseEntity.ok(results);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsInPriceRange(
            @RequestParam Double min,
            @RequestParam Double max
    ) {
        List<Product> results = new ArrayList<>();

        for (Product p : products) {
            if (p.getPrice() != null && p.getPrice() >= min && p.getPrice() <= max) {
                results.add(p);
            }
        }

        return ResponseEntity.ok(results);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> results = new ArrayList<>();

        for (Product p : products) {
            if (p.getStockQuantity() > 0) {
                results.add(p);
            }
        }

        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        if (newProduct.getProductId() == null) {
            newProduct.setProductId(getNextId());
        }

        products.add(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct
    ) {
        Product found = findById(productId);

        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        found.setName(updatedProduct.getName());
        found.setDescription(updatedProduct.getDescription());
        found.setPrice(updatedProduct.getPrice());
        found.setCategory(updatedProduct.getCategory());
        found.setStockQuantity(updatedProduct.getStockQuantity());
        found.setBrand(updatedProduct.getBrand());

        return ResponseEntity.ok(found);
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStockQuantity(
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        Product found = findById(productId);

        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        found.setStockQuantity(quantity);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        Product found = findById(productId);

        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        products.remove(found);
        return ResponseEntity.noContent().build(); 
    }


    private Product findById(Long id) {
        for (Product p : products) {
            if (p.getProductId() != null && p.getProductId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private long getNextId() {
        long max = 0;
        for (Product p : products) {
            if (p.getProductId() != null && p.getProductId() > max) {
                max = p.getProductId();
            }
        }
        return max + 1;
    }
}
