import React, { useEffect, useState } from "react";
import axiosInstance from '../../config/axiosConfig';
import VoosTable from "../../components/VoosTable/VoosTable";
import { Container, Title } from "./styles";

function VoosPage() {
  // Estado para armazenar a lista de voos
  const [voos, setVoos] = useState([]);

  // Função para buscar voos da API
  const fetchVoos = async () => {
    try {
      // Requisição para buscar todos os voos
      const response = await axiosInstance.get("/voos");
      // Atualiza o estado com os dados dos voos
      setVoos(response.data);
    } catch (error) {
      console.error("Erro ao buscar voos:", error);
    }
  };

  // Efeito colateral para buscar voos quando o componente for montado
  useEffect(() => {
    // Buscar voos ao montar o componente
    fetchVoos();

    // Atualizar a lista de voos a cada 5 minutos
    const intervalId = setInterval(fetchVoos, 300000); // 300000 ms = 5 minutos

    // Limpar o intervalo quando o componente for desmontado
    return () => clearInterval(intervalId);
  }, []); // O array vazio significa que o efeito será executado apenas uma vez, após a montagem do componente

  // Função para lidar com a exclusão de um voo
  const handleDelete = async (id) => {
    try {
      // Requisição para excluir o voo com o id especificado
      await axiosInstance.delete(`/voos/${id}`);
      // Atualiza o estado removendo o voo excluído da lista
      setVoos((prevVoos) => prevVoos.filter((voo) => voo.codigoVoo !== id));
    } catch (error) {
      console.error("Erro ao excluir voo:", error);
    }
  };

  return (
    <Container>
      <Title>Lista de Voos</Title>
      {/* Componente que exibe a tabela de voos */}
      <VoosTable voos={voos} onDelete={handleDelete} />
    </Container>
  );
}

export default VoosPage;
