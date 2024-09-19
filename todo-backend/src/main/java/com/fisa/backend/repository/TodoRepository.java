package com.fisa.backend.repository;

import com.fisa.backend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByMemberId(Long memberId);
    List<Todo> findByMemberIdAndCategoryName(Long memberId, String category);
}
