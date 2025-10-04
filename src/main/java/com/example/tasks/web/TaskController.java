package com.example.tasks.web;

import com.example.tasks.model.Task;
import com.example.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) { this.service = service; }

    @GetMapping public List<Task> all() { return service.all(); }

    @GetMapping("/{id}")
    public ResponseEntity<Task> one(@PathVariable long id) {
        return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> add(@Valid @RequestBody Task t) {
        return ResponseEntity.ok(service.add(t));
    }

    @PostMapping("/{id}/toggle")
    public Task toggle(@PathVariable long id) {
        return service.toggle(id);
    }

    @GetMapping("/open/count")
    public long countOpen() { return service.countOpen(); }

    @GetMapping("/health")
    public String health() { return "OK"; }
}
