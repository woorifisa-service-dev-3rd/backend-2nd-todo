package com.fisa.backend.controller;

import com.fisa.backend.DTO.AddTodoRequest;
import com.fisa.backend.DTO.UpdateTodoRequest;
import com.fisa.backend.model.Member;
import com.fisa.backend.model.Todo;
import com.fisa.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/todos")
@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{memberId}")
    public String listTodos(@PathVariable Long memberId, @RequestParam(required = false) String category, Model model) {
        System.out.println(category);
        System.out.println(memberId);
        List<Todo> todos = todoService.findByMemberAndCategory(memberId, category);
        model.addAttribute("todos", todos);
        model.addAttribute("selectedCategory", category);
        return "todos/todo";
    }

    @GetMapping("/add/{memberId}")
    public String showAddForm(@PathVariable Long memberId, Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("memberId", memberId);
        return "todos/add-todo";
    }

    @PostMapping("/{memberId}")
    public String addTodos(@PathVariable Long memberId, @ModelAttribute AddTodoRequest request, Model model) {
        request.setMemberId(memberId);
        Todo newTodo = todoService.save(request);
        model.addAttribute("todo", newTodo);
        return "redirect:/todos/" + memberId;
    }

    @PostMapping("/delete/{memberId}/{id}")
    public String deleteTodos(@PathVariable Long memberId, @PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos/" + memberId;
    }

    @GetMapping("/update/{memberId}/{id}")
    public String showUpdateForm(@PathVariable Long memberId, @PathVariable Long id, Model model) {
        Todo updateTodo = todoService.findById(id);
        model.addAttribute("todo", updateTodo);
        model.addAttribute("memberId", memberId);
        return "todos/update-todo";
    }

    @PostMapping("/update/{memberId}/{id}")
    public String updateTodos(@PathVariable Long memberId, @PathVariable Long id, @ModelAttribute UpdateTodoRequest request) {
        System.out.println("memberId: " + memberId);
        System.out.println("id: " + id);
        request.setId(id);
        request.setMemberId(memberId);
        todoService.update(request);
        return "redirect:/todos/" + memberId;
    }

}