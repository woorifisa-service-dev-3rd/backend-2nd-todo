package com.fisa.backend.service;

import com.fisa.backend.exception.TodoException;
import com.fisa.backend.model.Category;
import com.fisa.backend.model.Member;
import com.fisa.backend.model.Todo;
import com.fisa.backend.repository.CategoryRepository;
import com.fisa.backend.repository.MemberRepository;
import com.fisa.backend.repository.TodoRepository;
import com.fisa.backend.service.dto.AddTodoRequest;
import com.fisa.backend.service.dto.TodoResponse;
import com.fisa.backend.service.dto.UpdateTodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoRestService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public void add(Long memberId, AddTodoRequest request) {
        Member member = findMemberById(memberId);
        Category category = findCategoryById(request);
        Todo todo = request.toTodo(member, category);
        todoRepository.save(todo);
    }

    private Category findCategoryById(AddTodoRequest request) {
        return categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new TodoException(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다."));
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new TodoException(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."));
    }

    public void delete(Long id) {
        Todo deleteTodo = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(deleteTodo);
    }

    public void update(Long id, Long memberId, UpdateTodoRequest request) {
        Member member = findMemberById(memberId);
        Category category = categoryRepository.findByName(request.getCategory()).orElseThrow();
        Todo todo = request.toTodo(member, category);
        todo.setId(id);
        todoRepository.save(todo);
    }

    public List<TodoResponse> show(Long memberId, String category) {
        List<Todo> todos = findByMemberAndCategory(memberId, category);
        return todos.stream().map(todo -> TodoResponse.from(todo)).collect(Collectors.toList());
    }

    public List<Todo> findByMemberAndCategory(Long memberId, String category) {
        if (isNull(category) || category.equals("ALL")) {
            return todoRepository.findByMemberId(memberId);
        }
        return todoRepository.findByMemberIdAndCategoryName(memberId, category);
    }
}
