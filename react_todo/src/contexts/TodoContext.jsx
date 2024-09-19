import { createContext, useContext, useReducer, useEffect } from "react";

export const TodoContext = createContext();
export const TodoDispatchContext = createContext();

export const TodoProvider = ({ memberId, children }) => {
    const [todos, dispatch] = useReducer(reducer, { data: [], category: 'ALL' });

    useEffect(() => {
        const fetchTodos = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/members/${memberId}/todos`);
                const data = await response.json();
                dispatch({ type: 'SET_TODOS', todos: data }); // 받아온 todos를 reducer에 전달
            } catch (error) {
                console.error('Error fetching todos:', error);
            }
        };

        if (memberId) {
            fetchTodos();
        }
    }, [memberId]);

    return (
        <TodoContext.Provider value={todos}>
            <TodoDispatchContext.Provider value={dispatch}>
                {children}
            </TodoDispatchContext.Provider>
        </TodoContext.Provider>
    );
};

export const useTodos = () => useContext(TodoContext);
export const useTodosDispatch = () => useContext(TodoDispatchContext);

const reducer = (todos, action) => {
    const { data, category } = todos;

    switch (action.type) {
        case 'SET_TODOS':
            return { data: action.todos, category };
        case 'ADD': 
            const { newTodo } = action;
            return { data: [...data, newTodo], category };
        case 'UPDATE':
            const { updateTodo } = action;
            const updatedTodos = data.map(todo => 
                todo.id === updateTodo.id ? { ...updateTodo } : todo
            );
            return { data: updatedTodos, category };
        case 'DELETE': 
            const { id } = action;
            const deletedTodos = data.filter(todo => todo.id !== id);
            return { data: deletedTodos, category };
        case 'FILTER':  
            return { data, category: action.selectedCategory };
        default:
            return todos;
    }
};
