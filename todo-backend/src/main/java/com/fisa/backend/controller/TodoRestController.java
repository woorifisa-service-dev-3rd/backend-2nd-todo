package com.fisa.backend.controller;

import com.fisa.backend.service.TodoRestService;
import com.fisa.backend.service.dto.AddTodoRequest;
import com.fisa.backend.service.dto.TodoResponse;
import com.fisa.backend.service.dto.UpdateTodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
//resource -> todo
//resource/resource를 식별할 수 있는 식별자
//todos/todoId
//todos/memberId/todoId
//특정 todo를 조회하는 api를 만든다고 생각해봐
//todos/memberId/todoId
//members/memberId/todos/todoId
public class TodoRestController {

    private final TodoRestService todoRestService;
    private static final String REDIRECT_URI = "/api/members/%d/todos";

    @PostMapping("members/{memberId}/todos")
    public ResponseEntity<Void> add(@PathVariable Long memberId, @RequestBody AddTodoRequest request) {
        todoRestService.add(memberId, request);
        return ResponseEntity.created(URI.create(String.format(REDIRECT_URI, memberId))).build();
    }

    @DeleteMapping("/delete/members/{memberId}/todos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long memberId, @PathVariable Long id) {
        todoRestService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/members/{memberId}/todos/{id}")
    public ResponseEntity<Void> update(@PathVariable Long memberId, @PathVariable Long id, @RequestBody UpdateTodoRequest updateTodoRequest) {
        System.out.println("memberId: " + memberId);
        todoRestService.update(id, memberId, updateTodoRequest);
        return ResponseEntity.created(URI.create(String.format(REDIRECT_URI, memberId))).build();
    }

    @GetMapping("/members/{memberId}/todos")
    public List<TodoResponse> filtering(@PathVariable Long memberId, @RequestParam(defaultValue = "ALL") String category) {
        return todoRestService.show(memberId, category);
    }


}
