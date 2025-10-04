package com.example.tasks.service;

import com.example.tasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    private final Map<Long, Task> db = new LinkedHashMap<>();
    public TaskService() {
        db.put(1L, new Task(1,"Learn Jenkins", false));
        db.put(2L, new Task(2,"Write tests", true));
    }
    public List<Task> all() { return new ArrayList<>(db.values()); }
    public Optional<Task> get(long id) { return Optional.ofNullable(db.get(id)); }
    public Task add(Task t) {
        if (db.containsKey(t.id())) throw new IllegalArgumentException("exists");
        db.put(t.id(), t);
        return t;
    }
    public Task toggle(long id) {
        Task t = db.get(id);
        if (t == null) throw new NoSuchElementException();
        Task nt = new Task(t.id(), t.title(), !t.done());
        db.put(id, nt);
        return nt;
    }
    public long countOpen() {
        return db.values().stream().filter(t -> !t.done()).count();
    }
}
