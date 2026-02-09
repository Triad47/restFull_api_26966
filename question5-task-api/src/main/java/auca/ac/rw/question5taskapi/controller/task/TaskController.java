package auca.ac.rw.question5taskapi.controller.task;

import auca.ac.rw.question5taskapi.model.task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    // Sample tasks (helps for testing)
    public TaskController() {
        tasks.add(new Task(1L, "Finish Assignment", "Complete Spring Boot practical", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(2L, "Buy groceries", "Milk, Bread, Eggs", true, "LOW", "2026-02-10"));
        tasks.add(new Task(3L, "Study for quiz", "Revise REST controllers", false, "MEDIUM", "2026-02-12"));
        tasks.add(new Task(4L, "Clean room", "Organize desk and books", false, "LOW", "2026-02-11"));
    }

    // GET /api/tasks - Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks); // 200
    }

    // GET /api/tasks/{taskId} - Get task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task found = findById(taskId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(found); // 200
    }

    // GET /api/tasks/status?completed=true/false - Get tasks by completion status
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam boolean completed) {
        List<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == completed) {
                results.add(t);
            }
        }
        return ResponseEntity.ok(results); // 200
    }

    // GET /api/tasks/priority/{priority} - Get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getByPriority(@PathVariable String priority) {
        List<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority() != null && t.getPriority().equalsIgnoreCase(priority)) {
                results.add(t);
            }
        }
        return ResponseEntity.ok(results); // 200
    }

    // POST /api/tasks - Create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        if (newTask.getTaskId() == null) {
            newTask.setTaskId(getNextId());
        }
        tasks.add(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask); // 201
    }

    // PUT /api/tasks/{taskId} - Update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updated) {
        Task found = findById(taskId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        found.setTitle(updated.getTitle());
        found.setDescription(updated.getDescription());
        found.setCompleted(updated.isCompleted());
        found.setPriority(updated.getPriority());
        found.setDueDate(updated.getDueDate());

        return ResponseEntity.ok(found); // 200
    }

    // PATCH /api/tasks/{taskId}/complete - Mark task as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markCompleted(@PathVariable Long taskId) {
        Task found = findById(taskId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        found.setCompleted(true);
        return ResponseEntity.ok(found); // 200
    }

    // DELETE /api/tasks/{taskId} - Delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        Task found = findById(taskId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        tasks.remove(found);
        return ResponseEntity.noContent().build(); // 204
    }

    // -------- helper methods --------

    private Task findById(Long id) {
        for (Task t : tasks) {
            if (t.getTaskId() != null && t.getTaskId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private long getNextId() {
        long max = 0;
        for (Task t : tasks) {
            if (t.getTaskId() != null && t.getTaskId() > max) {
                max = t.getTaskId();
            }
        }
        return max + 1;
    }
}
