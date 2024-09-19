package com.fisa.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
@Setter
public class Todo extends BaseEntity{
    private String title;
    private String description;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @Builder
    public Todo(Long id, String title, String description, Member member, Category category) {
        super(id);
        this.title = title;
        this.description = description;
        this.member = member;
        this.category = category;
    }
}
