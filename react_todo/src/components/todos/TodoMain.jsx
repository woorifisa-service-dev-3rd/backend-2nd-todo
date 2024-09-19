import DefaultLayout from "@/layouts/DefaultLayout";
import TodoBody from "@/components/todos/TodoBody";
import TodoHeader from "@/components/todos/TodoHeader";
import { TodoProvider } from "@/contexts/TodoContext";
import NewModal from "@/components/ui/NewModal";
import TodoForm from "@/components/todos/TodoForm";
import TodoFilter from "@/components/todos/TodoFilter";
import TodoList from "@/components/todos/TodoList";
import { useLocation } from "react-router-dom";

const TodoMain = () => {
  const location = useLocation();
  const memberId = location.state?.memberId;

  return (
    <DefaultLayout>
      <header className="flex flex-col items-center">
        <img className="animate-bounce ml-4" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Thought%20Balloon.png" alt="Thought Balloon" width="50" height="50" />
        <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Animals/Seal.png" alt="Seal" width="75" height="75" />
      </header>

      <section className="max-w-xl m-4 mx-auto">
        <TodoProvider>

          <TodoHeader>
            <NewModal>
              <NewModal.Open>
                <button className="px-6 py-2 font-semibold text-gray-100 bg-green-500 border-none rounded cursor-pointer">
                  New Add Todo
                </button>
              </NewModal.Open>
              <NewModal.Dialog>
                <TodoForm memberId={memberId}>New Todo</TodoForm>
              </NewModal.Dialog>
            </NewModal>
            <TodoFilter memberId={memberId}/>
          </TodoHeader>

          <TodoBody>
            <TodoList memberId={memberId}/>
          </TodoBody>

        </TodoProvider>
      </section>
    </DefaultLayout>
  );
};

export default TodoMain
