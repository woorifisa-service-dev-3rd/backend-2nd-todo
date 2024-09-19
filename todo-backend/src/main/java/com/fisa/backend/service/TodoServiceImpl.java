package com.fisa.backend.service;

import com.fisa.backend.DTO.AddTodoRequest;
import com.fisa.backend.DTO.UpdateTodoRequest;
import com.fisa.backend.model.Category;
import com.fisa.backend.model.Member;
import com.fisa.backend.model.Todo;
import com.fisa.backend.repository.CategoryRepository;
import com.fisa.backend.repository.MemberRepository;
import com.fisa.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional // 트랜잭션을 통해 데이터베이스 작업 안전하게 처리
// ACID 기능으로 예외가 발생했을 때 메서드 중간에 예외 발생하면 트랜잭션 롤백
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Todo> findByMemberId(Long memberId) {
        return todoRepository.findByMemberId(memberId);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo save(AddTodoRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + request.getMemberId()));

        // 요청에서 카테고리 이름을 사용해 Category 엔티티 조회
        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.getCategory()));

        // 빌더 패턴 통해 Todo 객체 생성
        // 필드를 하나씩 설정한 뒤 마지막에 build() 메서드 호출해서 객체 생성
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(category)
                .member(member)
                .build();
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Long id) {
        Todo deleteTodo = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(deleteTodo);
    }

    @Override
    public Todo findById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        return todoOptional.orElseThrow();
    }

    @Override
    public void update(UpdateTodoRequest request) {
        System.out.println("update!!!");
        Todo todo = todoRepository.findById(request.getId()).orElseThrow();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCategory(categoryRepository.findByName(request.getCategory()).orElse(null));
        todoRepository.save(todo);
    }

    @Override
    public List<Todo> findByMemberAndCategory(Long memberId, String category) {
        if (isNull(category) || category.equals("All")) {
            return todoRepository.findByMemberId(memberId);
        }
        return todoRepository.findByMemberIdAndCategoryName(memberId, category);
    }

}
