import "./App.css";
import { Routes, Route } from "react-router-dom";
import Main from "pages/Main";
import Header from "pages/Header";

function App() {
  return (
    <>
      <Routes>
        <Route element={<Header />}>
          <Route path="/" element={<Main />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
