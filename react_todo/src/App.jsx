import {BrowserRouter, Route, Routes } from "react-router-dom";
import TodoMain from "@/components/todos/TodoMain";
import LoginPage from "@/components/members/LoginPage";
import SignUpPage from "@/components/members/SignUpPage";

const App = () => {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<LoginPage />}/>
      <Route path="/members/:memberId/todos" element={<TodoMain />}/>
      <Route path="/signup" element={<SignUpPage/>}/>
    </Routes>
    </BrowserRouter>
  );
};

export default App;
