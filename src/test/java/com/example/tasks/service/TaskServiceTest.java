package com.example.tasks.service;

import com.example.tasks.model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class TaskServiceTest {
    @Test void hasDefaults() {
        TaskService s = new TaskService();
        assertTrue(s.all().size() >= 2);
    }
    @Test void add() {
        TaskService s = new TaskService();
        Task t = new Task(5,"Practice CI", false);
        assertEquals(5, s.add(t).id());
    }
    @Test void getMissing() {
        TaskService s = new TaskService();
        assertTrue(s.get(99).isEmpty());
    }
    @Test void toggle() {
        TaskService s = new TaskService();
        Task t = s.toggle(1);
        assertNotNull(t);
        assertTrue(t.done());
    }
    @Test void toggleMissingThrows() {
        TaskService s = new TaskService();
        assertThrows(NoSuchElementException.class, () -> s.toggle(123));
    }
    @Test void countOpen() {
        TaskService s = new TaskService();
        assertTrue(s.countOpen() >= 0);
    }
}
