package auca.ac.rw.question3restaurantapi.controller.menu;

import auca.ac.rw.question3restaurantapi.model.menu.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuController() {
        menuItems.add(new MenuItem(1L, "Samosa", "Crispy pastry with beef filling", 800.0, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Chicken Wings", "Spicy grilled wings", 2500.0, "Appetizer", true));

        menuItems.add(new MenuItem(3L, "Grilled Chicken", "Served with fries and salad", 6500.0, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Beef Steak", "Juicy steak with pepper sauce", 9500.0, "Main Course", false));
        menuItems.add(new MenuItem(5L, "Vegetable Pasta", "Pasta with fresh vegetables", 5500.0, "Main Course", true));

        menuItems.add(new MenuItem(6L, "Chocolate Cake", "Rich chocolate slice", 3000.0, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Fruit Salad", "Seasonal mixed fruits", 2500.0, "Dessert", false));

        menuItems.add(new MenuItem(8L, "Fresh Juice", "Mango or Passion fruit", 2000.0, "Beverage", true));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        MenuItem found = findById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(found); 
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getByCategory(@PathVariable String category) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory() != null && item.getCategory().equalsIgnoreCase(category)) {
                results.add(item);
            }
        }
        return ResponseEntity.ok(results); 
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getByAvailability(
            @RequestParam(value = "available", defaultValue = "true") boolean available
    ) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                results.add(item);
            }
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchByName(@RequestParam String name) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName() != null && item.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(item);
            }
        }
        return ResponseEntity.ok(results); 
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem newItem) {
        if (newItem.getId() == null) {
            newItem.setId(getNextId());
        }
        menuItems.add(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem); 
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        MenuItem found = findById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }

        found.setAvailable(!found.isAvailable());
        return ResponseEntity.ok(found); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        MenuItem found = findById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        menuItems.remove(found);
        return ResponseEntity.noContent().build(); 
    }


    private MenuItem findById(Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    private long getNextId() {
        long max = 0;
        for (MenuItem item : menuItems) {
            if (item.getId() != null && item.getId() > max) {
                max = item.getId();
            }
        }
        return max + 1;
    }
}
