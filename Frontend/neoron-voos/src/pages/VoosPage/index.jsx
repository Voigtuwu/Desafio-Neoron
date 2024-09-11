// src/pages/VoosPage/VoosPage.js
import React, { useEffect, useState } from "react";
import axiosInstance from '../../config/axiosConfig'
import VoosTable from "../../components/VoosTable/VoosTable";
import { Container, Title } from "./styles";

function VoosPage() {
  const [voos, setVoos] = useState([]);

  useEffect(() => {
    const fetchVoos = async () => {
      try {
        const response = await axiosInstance.get("/voos");
        setVoos(response.data);
      } catch (error) {
        console.error("Erro ao buscar voos:", error);
      }
    };

    fetchVoos();
  }, []);

  return (
    <Container>
      <Title>Lista de Voos</Title>
      <VoosTable voos={voos} />
    </Container>
  );
}

export default VoosPage;
