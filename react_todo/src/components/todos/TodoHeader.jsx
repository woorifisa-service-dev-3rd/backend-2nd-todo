const TodoHeader = ({ children }) => {
  return (
    <div className="flex items-center justify-between mb-2" id="task-control">
      {children}
    </div>
  );
};

export default TodoHeader;
