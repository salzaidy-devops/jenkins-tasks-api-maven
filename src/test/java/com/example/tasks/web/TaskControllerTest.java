package com.example.tasks.web;

import com.example.tasks.TasksApiApplication;
import com.example.tasks.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TasksApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test void health() throws Exception {
        mvc.perform(get("/api/tasks/health")).andExpect(status().isOk()).andExpect(content().string("OK"));
    }
    @Test void all() throws Exception {
        mvc.perform(get("/api/tasks")).andExpect(status().isOk());
    }
    @Test void oneFound() throws Exception {
        mvc.perform(get("/api/tasks/1")).andExpect(status().isOk());
    }
    @Test void oneNotFound() throws Exception {
        mvc.perform(get("/api/tasks/99")).andExpect(status().isNotFound());
    }
    @Test void add() throws Exception {
        Task t = new Task(9,"Read reports", false);
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(t)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title").value("Read reports"));
    }
    @Test void toggle() throws Exception {
        mvc.perform(post("/api/tasks/1/toggle")).andExpect(status().isOk());
    }
    @Test void countOpen() throws Exception {
        mvc.perform(get("/api/tasks/open/count")).andExpect(status().isOk());
    }
}
