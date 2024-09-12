import React, { useState, useEffect } from "react";
import axiosInstance from '../../config/axiosConfig';
import { useNavigate } from "react-router-dom";
import { Container, Title, Form, Label, Select, Input, Button } from "./styles";

function CadastrarVooPage() {
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

    fetchAeroportos();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const validateForm = () => {
    const { origem, destino, dataPartida, horaPartida, dataChegada, horaChegada } = formData;
    if (!origem || !destino || !dataPartida || !horaPartida || !dataChegada || !horaChegada) {
      return "Todos os campos são obrigatórios.";
    }
    return "";
  };

  const formatDate = (date) => {
    if (!date) return "";
    // Supondo que date está no formato yyyy-MM-dd
    return date; // Não precisa alterar, pois o formato yyyy-MM-dd é aceito pelo backend
  };
  
  const formatDateTime = (date, time) => {
    if (!date || !time) return "";
    return `${date}T${time}:00`; // Formato yyyy-MM-dd'T'HH:mm:ss
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
        dataPartida: formatDate(formData.dataPartida), // Formato yyyy-MM-dd
        horaPartida: formatDateTime(formData.dataPartida, formData.horaPartida), // Formato yyyy-MM-dd'T'HH:mm:ss
        dataChegada: formatDate(formData.dataChegada), // Formato yyyy-MM-dd
        horaChegada: formatDateTime(formData.dataChegada, formData.horaChegada), // Formato yyyy-MM-dd'T'HH:mm:ss
      };
  
      await axiosInstance.post("/voos", formDataWithIds);
      setMessage("Voo cadastrado com sucesso!");
      setError("");
      navigate("/voos");
    } catch (error) {
      console.error("Erro ao cadastrar voo:", error);
      setMessage("");
      setError(parseErrorMessage(error.response?.data));
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
      <Title>Cadastrar Novo Voo</Title>
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

        <Button type="submit">Cadastrar</Button>
      </Form>
      {message && <p>{message}</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </Container>
  );
}

export default CadastrarVooPage;
