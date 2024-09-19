import {BrowserRouter, Route, Routes } from "react-router-dom";
import TodoMain from "@/components/todos/TodoMain";
import LoginPage from "@/components/members/LoginPage";
import SignupPage from "@/components/members/SignupPage";

const App = () => {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<LoginPage />}/>
      <Route path="/members/:memberId/todos" element={<TodoMain />}/>
      <Route path="/signup" element={<SignupPage/>}/>
    </Routes>
    </BrowserRouter>
  );
};

export default App;
