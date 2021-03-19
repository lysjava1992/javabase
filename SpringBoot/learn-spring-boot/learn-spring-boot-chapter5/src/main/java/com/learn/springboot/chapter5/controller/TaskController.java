package com.learn.springboot.chapter5.controller;

import com.learn.springboot.chapter5.task.CustomSchedulingConfigurer;
import com.learn.springboot.chapter5.task.model.Task;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final CustomSchedulingConfigurer scheduleConfig;

    @Autowired
    public TaskController( CustomSchedulingConfigurer scheduleConfig) {
        this.scheduleConfig = scheduleConfig;
    }

    @PostMapping("/task")
    public String task(@RequestBody Task task) {
        scheduleConfig.editTask(task);
        return task.getState().name();
    }

    @GetMapping("/task")
    public List<Task> doGet() {
        return scheduleConfig.getTaskList();
    }
}
