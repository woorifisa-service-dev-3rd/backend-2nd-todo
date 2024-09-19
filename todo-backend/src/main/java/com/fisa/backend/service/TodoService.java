package com.fisa.backend.service;

import com.fisa.backend.DTO.AddTodoRequest;
import com.fisa.backend.DTO.UpdateTodoRequest;
import com.fisa.backend.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findByMemberId(Long memberId);

    List<Todo> findAll();

    Todo save(AddTodoRequest request);

    void delete(Long id);

    Todo findById(Long id);

    void update(UpdateTodoRequest request);

    List<Todo> findByMemberAndCategory(Long memberId, String category);

}
