import React, { useState } from "react";
import TodoItem from './TodoItem'
import { useTodos } from '@/contexts/TodoContext'

const TodoList = ({memberId}) => {
  const todos = useTodos();

  const filterTodos = (todos, selectedCategory) =>
    selectedCategory === "ALL"
      ? todos
      : todos.filter((todo) => todo.category === selectedCategory);
      
  const filteredTodos = filterTodos(todos.data, todos.category);

  const todoList = filteredTodos.map((todo, index) => <TodoItem todo={todo} key={index} memberId={memberId}/>);

  return (
    <ul className="px-0 my-8">{todoList}</ul>
  );
};

export default TodoList;
