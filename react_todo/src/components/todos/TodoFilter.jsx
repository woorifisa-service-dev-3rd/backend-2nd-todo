import { TODO_CATEGORY_ICON } from '@/constants/icon'
import { useTodos, useTodosDispatch } from '@/contexts/TodoContext';
import { useState, useEffect } from 'react';

const TodoFilter = ({ memberId }) => {

  const [selectedCategory, setSelectedCategory] = useState('ALL');
  const dispatch = useTodosDispatch();

  const filterTodos = (event) => {
    const category = event.target.value;
    setSelectedCategory(category);
    dispatch({ type: 'FILTER', selectedCategory: category });
    fetchTodos(category);
  }

  const fetchTodos = async (category = 'ALL') => {
    try {
      const response = await fetch(`http://localhost:8080/api/members/${memberId}/todos?category=${category}`);
      const data = await response.json();
      console.log("data: ", data);
      dispatch({type: 'SET_TODOS', todos: data});
    } catch (error) {
      console.error('Error fetching todos:', error);
    }
  };

  useEffect(() => {
    fetchTodos();
  }, [memberId]);

  return (
    <select className="p-2 text-gray-100 bg-gray-800 rounded"
            data-cy="todo-filter"
            value={selectedCategory} onChange={filterTodos}
            >
      <option value="ALL">All</option>
      <option value="TODO">{TODO_CATEGORY_ICON.TODO} Todo</option>
      <option value="PROGRESS">{TODO_CATEGORY_ICON.PROGRESS} In progress</option>
      <option value="DONE">{TODO_CATEGORY_ICON.DONE} Done</option>
    </select>
  )
}

export default TodoFilter