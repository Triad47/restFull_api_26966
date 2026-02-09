package auca.ac.rw.question2studentapi.controller.student;

import auca.ac.rw.question2studentapi.model.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    // In-memory list (no DB)
    private final List<Student> students = new ArrayList<>();

    // Create at least 5 sample students (different majors + GPAs)
    public StudentController() {
        students.add(new Student(1L, "Alice", "Niyonsenga", "alice@auca.ac.rw", "Computer Science", 3.8));
        students.add(new Student(2L, "Brian", "Mukamana", "brian@auca.ac.rw", "Information Technology", 3.2));
        students.add(new Student(3L, "Chantal", "Uwimana", "chantal@auca.ac.rw", "Computer Science", 3.6));
        students.add(new Student(4L, "David", "Habimana", "david@auca.ac.rw", "Business", 2.9));
        students.add(new Student(5L, "Esther", "Irakoze", "esther@auca.ac.rw", "Software Engineering", 3.5));
    }

    // GET /api/students - Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students); // 200
    }

    // GET /api/students/{studentId} - Get student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student found = findStudentById(studentId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(found); // 200
    }

    // GET /api/students/major/{major} - Get all students by major (path variable)
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> results = new ArrayList<>();

        for (Student s : students) {
            if (s.getMajor() != null && s.getMajor().equalsIgnoreCase(major)) {
                results.add(s);
            }
        }

        return ResponseEntity.ok(results); // 200
    }

    // GET /api/students/filter?gpa={minGpa} - Filter students with GPA >= min
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam("gpa") Double minGpa) {
        List<Student> results = new ArrayList<>();

        for (Student s : students) {
            if (s.getGpa() != null && s.getGpa() >= minGpa) {
                results.add(s);
            }
        }

        return ResponseEntity.ok(results); // 200
    }

    // POST /api/students - Register a new student
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student newStudent) {
        // Beginner-friendly: generate ID if missing
        if (newStudent.getStudentId() == null) {
            newStudent.setStudentId(getNextStudentId());
        }

        students.add(newStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent); // 201
    }

    // PUT /api/students/{studentId} - Update student information
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody Student updatedStudent
    ) {
        Student found = findStudentById(studentId);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        // Update fields (simple & beginner-friendly)
        found.setFirstName(updatedStudent.getFirstName());
        found.setLastName(updatedStudent.getLastName());
        found.setEmail(updatedStudent.getEmail());
        found.setMajor(updatedStudent.getMajor());
        found.setGpa(updatedStudent.getGpa());

        return ResponseEntity.ok(found); // 200
    }

    // ---------------- Helper Methods ----------------

    private Student findStudentById(Long id) {
        for (Student s : students) {
            if (s.getStudentId() != null && s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private long getNextStudentId() {
        long max = 0;
        for (Student s : students) {
            if (s.getStudentId() != null && s.getStudentId() > max) {
                max = s.getStudentId();
            }
        }
        return max + 1;
    }
}
