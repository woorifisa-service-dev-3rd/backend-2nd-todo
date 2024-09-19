import React, { useState } from "react";
import IconButton from "@/components/ui/IconButton";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
import Modal from "@/components/ui/Modal";
import TodoForm from "./TodoForm";

const TodoItem = ({ todo, onAdd, onUpdate, memberId }) => {
  const [openModal, open] = useState(false);
  const closeModal = () => open(false);
  const [todos, setTodos] = useState(todo);

  function del() {
    if(window.confirm('삭제 하시겠습니까?')) {
      fetch(`http://localhost:8080/api/delete/members/${memberId}/todos/${todos.id}`, {
        method: 'DELETE',
      }).then(res=>{
        if(res.ok) {
          window.location.reload();
        }
      }).catch(error => {
        console.error('Error deleting todo:', error);
      })
    }
  }

  return (
    <li className="flex gap-4 justify-between my-4 py-4 px-4 border-[1px] bg-gray-700 rounded-md shadow-xl">
      <div>
        <span className="text-lg font-medium text-gray-300">
          {TODO_CATEGORY_ICON[todo.category]} {todo.category}
        </span>
        <div>
          <h2
            data-test="title"
            className="mb-0 text-lg font-bold text-gray-100 uppercase"
          >
            {todo.title}
          </h2>
          <p className="mt-2 text-base text-gray-200">{todo.summary}</p>
        </div>
      </div>
      <div className="flex items-center gap-1">
        <IconButton icon={"✏️"} onClick={() => open(true)} />
        <IconButton
          textColor="text-red-300"
          icon={"🗑"}
          onClick={del}
        />
      </div>
      {/* Modal 호출 부분 */}
      {openModal && 
        <Modal onClose={closeModal}>
          <TodoForm
            onAdd={onAdd}
            onClose={closeModal}
            todo={todo}
            onUpdate={onUpdate}
            memberId={memberId}
          >
            Update Todo
          </TodoForm>
        </Modal>
      }
    </li>
  );
};

export default TodoItem;
