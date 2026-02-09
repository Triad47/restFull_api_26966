package auca.ac.rw.question6userprofileapi.controller.userprofile;

import auca.ac.rw.question6userprofileapi.model.userprofile.ApiResponse;
import auca.ac.rw.question6userprofileapi.model.userprofile.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    // In-memory storage
    private final List<UserProfile> users = new ArrayList<>();

    // Sample data (for testing)
    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe",
                24, "Rwanda", "Software developer", true));
        users.add(new UserProfile(2L, "alice_k", "alice@example.com", "Alice Keza",
                20, "Rwanda", "Student", false));
        users.add(new UserProfile(3L, "mike_ug", "mike@example.com", "Mike Okello",
                30, "Uganda", "Data analyst", true));
    }

    // -------------------- BASIC CRUD --------------------

    // CREATE: POST /api/users
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile newUser) {
        if (newUser.getUserId() == null) {
            newUser.setUserId(getNextId());
        }
        users.add(newUser);

        ApiResponse<UserProfile> response =
                new ApiResponse<>(true, "User profile created successfully", newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
    }

    // READ ALL: GET /api/users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response =
                new ApiResponse<>(true, "All user profiles", users);

        return ResponseEntity.ok(response); // 200
    }

    // READ ONE: GET /api/users/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        UserProfile found = findById(userId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User profile not found", null));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "User profile found", found));
    }

    // UPDATE: PUT /api/users/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(
            @PathVariable Long userId,
            @RequestBody UserProfile updated
    ) {
        UserProfile found = findById(userId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User profile not found", null));
        }

        found.setUsername(updated.getUsername());
        found.setEmail(updated.getEmail());
        found.setFullName(updated.getFullName());
        found.setAge(updated.getAge());
        found.setCountry(updated.getCountry());
        found.setBio(updated.getBio());
        found.setActive(updated.isActive());

        return ResponseEntity.ok(new ApiResponse<>(true, "User profile updated successfully", found));
    }

    // DELETE: DELETE /api/users/{userId}
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable Long userId) {
        UserProfile found = findById(userId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User profile not found", null));
        }

        users.remove(found);
        return ResponseEntity.status(HttpStatus.NO_CONTENT) // 204
                .body(new ApiResponse<>(true, "User profile deleted successfully", null));
    }

    // -------------------- SEARCH ENDPOINTS --------------------

    // Search by username: GET /api/users/search/username?username=john_doe
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@RequestParam String username) {
        List<UserProfile> results = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)) {
                results.add(u);
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results by username", results));
    }

    // Search by country: GET /api/users/search/country?country=Rwanda
    @GetMapping("/search/country")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@RequestParam String country) {
        List<UserProfile> results = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getCountry() != null && u.getCountry().equalsIgnoreCase(country)) {
                results.add(u);
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results by country", results));
    }

    // Search by age range: GET /api/users/search/age?min=18&max=25
    @GetMapping("/search/age")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam int min,
            @RequestParam int max
    ) {
        List<UserProfile> results = new ArrayList<>();
        for (UserProfile u : users) {
            if (u.getAge() >= min && u.getAge() <= max) {
                results.add(u);
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results by age range", results));
    }

    // -------------------- ACTIVATE / DEACTIVATE --------------------

    // Activate: PATCH /api/users/{userId}/activate
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        UserProfile found = findById(userId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User profile not found", null));
        }

        found.setActive(true);
        return ResponseEntity.ok(new ApiResponse<>(true, "User activated successfully", found));
    }

    // Deactivate: PATCH /api/users/{userId}/deactivate
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        UserProfile found = findById(userId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User profile not found", null));
        }

        found.setActive(false);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deactivated successfully", found));
    }

    // -------------------- Helper methods --------------------

    private UserProfile findById(Long id) {
        for (UserProfile u : users) {
            if (u.getUserId() != null && u.getUserId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    private long getNextId() {
        long max = 0;
        for (UserProfile u : users) {
            if (u.getUserId() != null && u.getUserId() > max) {
                max = u.getUserId();
            }
        }
        return max + 1;
    }
}
