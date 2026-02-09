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

    // In-memory data
    private final List<MenuItem> menuItems = new ArrayList<>();

    // Challenge: at least 8 items across categories
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

    // GET /api/menu - Get all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems); // 200
    }

    // GET /api/menu/{id} - Get specific menu item
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        MenuItem found = findById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(found); // 200
    }

    // GET /api/menu/category/{category} - Get items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getByCategory(@PathVariable String category) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory() != null && item.getCategory().equalsIgnoreCase(category)) {
                results.add(item);
            }
        }
        return ResponseEntity.ok(results); // 200
    }

    // GET /api/menu/available?available=true - Get only available items
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
        return ResponseEntity.ok(results); // 200
    }

    // GET /api/menu/search?name={name} - Search menu items by name
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchByName(@RequestParam String name) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName() != null && item.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(item);
            }
        }
        return ResponseEntity.ok(results); // 200
    }

    // POST /api/menu - Add new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem newItem) {
        if (newItem.getId() == null) {
            newItem.setId(getNextId());
        }
        menuItems.add(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem); // 201
    }

    // PUT /api/menu/{id}/availability - Toggle item availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        MenuItem found = findById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        found.setAvailable(!found.isAvailable());
        return ResponseEntity.ok(found); // 200
    }

    // DELETE /api/menu/{id} - Remove menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        MenuItem found = findById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        menuItems.remove(found);
        return ResponseEntity.noContent().build(); // 204
    }

    // -------- helper methods --------

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
