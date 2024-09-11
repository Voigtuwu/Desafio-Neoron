import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header/Header";
import VoosPage from "./pages/VoosPage";
import CadastrarVooPage from "./pages/CadastrarVooPage";
import AtualizarVooPage from "./pages/AtualizarVooPage";

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<VoosPage />} />
        <Route path="/voos" element={<VoosPage />} />
        <Route path="/cadastrar" element={<CadastrarVooPage />} />
        <Route path="/atualizar/:id" element={<AtualizarVooPage />} />
      </Routes>
    </Router>
  );
}

export default App;
