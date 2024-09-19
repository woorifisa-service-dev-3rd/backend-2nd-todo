package com.fisa.backend.service.dto;

import com.fisa.backend.model.Category;
import com.fisa.backend.model.Member;
import com.fisa.backend.model.Todo;
import lombok.Getter;

//request dto 에는 기본생성자, getter가 있어야함
@Getter
public class AddTodoRequest {

    private String title;
    private String summary;
    private String category;

    private AddTodoRequest() {
    }

    public AddTodoRequest(String title, String summary, String category) {
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
