// src/pages/AtualizarVooPage/AtualizarVooPage.js
import React, { useState, useEffect } from "react";
import axiosInstance from '../../config/axiosConfig';
import { useNavigate, useParams } from "react-router-dom";
import { Container, Title, Form, Label, Select, Input, Button } from "./styles";

function AtualizarVooPage() {
  const { id } = useParams();
  const [origens, setOrigens] = useState([]);
  const [destinos, setDestinos] = useState([]);
  const [formData, setFormData] = useState({
    origem: "",
    destino: "",
    dataPartida: "",
    horaPartida: "",
    dataChegada: "",
    horaChegada: "",
  });
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAeroportos = async () => {
      try {
        const response = await axiosInstance.get("/aeroportos");
        setOrigens(response.data);
        setDestinos(response.data);
      } catch (error) {
        console.error("Erro ao buscar aeroportos:", error);
      }
    };

    const fetchVoo = async () => {
      try {
        const response = await axiosInstance.get(`/voos/${id}`);
        const vooData = response.data;
        setFormData({
          origem: vooData.origem.id,
          destino: vooData.destino.id,
          dataPartida: vooData.dataPartida,
          horaPartida: vooData.horaPartida,
          dataChegada: vooData.dataChegada,
          horaChegada: vooData.horaChegada,
        });
      } catch (error) {
        console.error("Erro ao buscar voo:", error);
      }
    };

    fetchAeroportos();
    fetchVoo();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const formatLocalDateTime = (date, time) => {
    if (!date || !time) return "";
    return `${date}T${time}`;
  };

  const validateForm = () => {
    const { origem, destino, dataPartida, horaPartida, dataChegada, horaChegada } = formData;
    if (!origem || !destino || !dataPartida || !horaPartida || !dataChegada || !horaChegada) {
      return "Todos os campos são obrigatórios.";
    }
    return "";
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    try {
      const formDataWithIds = {
        origem: { id: formData.origem },
        destino: { id: formData.destino },
        dataPartida: formData.dataPartida,
        horaPartida: formatLocalDateTime(formData.dataPartida, formData.horaPartida),
        dataChegada: formData.dataChegada,
        horaChegada: formatLocalDateTime(formData.dataChegada, formData.horaChegada),
      };

      await axiosInstance.put(`/voos/${id}`, formDataWithIds);
      setMessage("Voo atualizado com sucesso!");
      setError("");
      navigate("/voos");
    } catch (error) {
      console.error("Erro ao atualizar voo:", error);
      if (error.response && error.response.data) {
        setMessage("");
        setError(parseErrorMessage(error.response.data));
      } else {
        setMessage("");
        setError("Erro ao atualizar voo. Tente novamente.");
      }
    }
  };

  const parseErrorMessage = (errorData) => {
    if (errorData.message) {
      return errorData.message;
    }

    if (errorData.errors) {
      if (errorData.errors.minDifference) {
        return "Cada voo deve ter no mínimo 30 minutos de diferença do outro.";
      }
      if (errorData.errors.duplicateDestination) {
        return "Não podem haver 2 voos para o mesmo destino no mesmo dia.";
      }
    }

    return "Erro desconhecido. Tente novamente.";
  };

  return (
    <Container>
      <Title>Atualizar Voo</Title>
      <Title>Código do Voo: {id}</Title>
      <Form onSubmit={handleSubmit}>
        <div>
          <Label htmlFor="origem">Origem:</Label>
          <Select
            id="origem"
            name="origem"
            value={formData.origem}
            onChange={handleChange}
          >
            <option value="">Selecione um aeroporto</option>
            {origens.map((aeroporto) => (
              <option key={aeroporto.id} value={aeroporto.id}>
                {aeroporto.nome}
              </option>
            ))}
          </Select>
        </div>

        <div>
          <Label htmlFor="destino">Destino:</Label>
          <Select
            id="destino"
            name="destino"
            value={formData.destino}
            onChange={handleChange}
          >
            <option value="">Selecione um aeroporto</option>
            {destinos.map((aeroporto) => (
              <option key={aeroporto.id} value={aeroporto.id}>
                {aeroporto.nome}
              </option>
            ))}
          </Select>
        </div>

        <div>
          <Label htmlFor="dataPartida">Data de Partida:</Label>
          <Input
            type="date"
            id="dataPartida"
            name="dataPartida"
            value={formData.dataPartida}
            onChange={handleChange}
          />
        </div>

        <div>
          <Label htmlFor="horaPartida">Hora de Partida:</Label>
          <Input
            type="time"
            id="horaPartida"
            name="horaPartida"
            value={formData.horaPartida}
            onChange={handleChange}
          />
        </div>

        <div>
          <Label htmlFor="dataChegada">Data de Chegada:</Label>
          <Input
            type="date"
            id="dataChegada"
            name="dataChegada"
            value={formData.dataChegada}
            onChange={handleChange}
          />
        </div>

        <div>
          <Label htmlFor="horaChegada">Hora de Chegada:</Label>
          <Input
            type="time"
            id="horaChegada"
            name="horaChegada"
            value={formData.horaChegada}
            onChange={handleChange}
          />
        </div>

        <Button type="submit">Atualizar</Button>
      </Form>
      {message && <p>{message}</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </Container>
  );
}

export default AtualizarVooPage;
