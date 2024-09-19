package com.fisa.backend.service.dto;

import com.fisa.backend.model.Category;
import com.fisa.backend.model.Member;
import com.fisa.backend.model.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTodoRequest {

    private Long id;
    private String title;
    private String summary;
    private String category;

    public UpdateTodoRequest() {
    }

    public UpdateTodoRequest(Long id, String title, String summary, String category) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.category = category;
    }

    public Todo toTodo(Member member, Category category) {
        return Todo.builder()
                .member(member)
                .category(category)
                .title(title)
                .description(summary).build();
    }
}
