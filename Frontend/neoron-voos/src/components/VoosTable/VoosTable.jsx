import React, { useState } from "react";
import PropTypes from "prop-types";
import {
  Container,
  Table,
  TableHeader,
  TableBody,
  TableRow,
  TableData,
  Icon,
} from "./styles";
import { FaTrashAlt, FaEdit } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { TextField, TablePagination, Paper } from "@mui/material";

function VoosTable({ voos, onDelete }) {
  const navigate = useNavigate();

  // Estado para paginação
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  // Estado para pesquisa
  const [searchTerm, setSearchTerm] = useState("");

  // Função para mudar a página
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  // Função para mudar o número de linhas por página
  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0); // Reseta a página para 0 quando o número de linhas por página muda
  };

  // Função para filtrar os voos com base no termo de pesquisa
  const filteredVoos = voos.filter(
    (voo) =>
      voo.codigoVoo.toLowerCase().includes(searchTerm.toLowerCase()) ||
      voo.origem.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      voo.destino.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      voo.dataPartida.toLowerCase().includes(searchTerm.toLowerCase()) ||
      voo.horaPartida.toLowerCase().includes(searchTerm.toLowerCase()) ||
      voo.dataChegada.toLowerCase().includes(searchTerm.toLowerCase()) ||
      voo.horaChegada.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // Calculando os voos a serem exibidos na página atual
  const paginatedVoos = filteredVoos.slice(
    page * rowsPerPage,
    page * rowsPerPage + rowsPerPage
  );

  // Função para navegação à página de edição
  const handleEdit = (id) => {
    navigate(`/atualizar/${id}`);
  };

  // Função para exclusão de um voo
  const handleDelete = (id) => {
    if (window.confirm("Tem certeza que deseja excluir este voo?")) {
      onDelete(id);
    }
  };

  return (
    <Paper style={{ padding: "16px" }}>
      <TextField
        label="Pesquisar"
        variant="outlined"
        fullWidth
        margin="normal"
        onChange={(e) => setSearchTerm(e.target.value)}
        style={{ marginBottom: "16px" }}
      />
      <Container>
        <Table>
          <TableHeader>
            <TableRow>
              <TableData>Código</TableData>
              <TableData>Origem</TableData>
              <TableData>Destino</TableData>
              <TableData>Data Partida</TableData>
              <TableData>Hora Partida</TableData>
              <TableData>Data Chegada</TableData>
              <TableData>Hora Chegada</TableData>
              <TableData>Ações</TableData>
            </TableRow>
          </TableHeader>
          <TableBody>
            {paginatedVoos.map((voo) => (
              <TableRow key={voo.codigoVoo}>
                <TableData>{voo.codigoVoo}</TableData>
                <TableData>{voo.origem.nome}</TableData>
                <TableData>{voo.destino.nome}</TableData>
                <TableData>{voo.dataPartida}</TableData>
                <TableData>{voo.horaPartida}</TableData>
                <TableData>{voo.dataChegada}</TableData>
                <TableData>{voo.horaChegada}</TableData>
                <TableData>
                  <Icon onClick={() => handleEdit(voo.codigoVoo)}>
                    <FaEdit />
                  </Icon>
                  <Icon onClick={() => handleDelete(voo.codigoVoo)}>
                    <FaTrashAlt />
                  </Icon>
                </TableData>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Container>
      <TablePagination
        rowsPerPageOptions={[10, 25, 50]}
        component="div"
        count={filteredVoos.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}

// Definindo os tipos das props que o componente VoosTable deve receber
VoosTable.propTypes = {
  voos: PropTypes.arrayOf(
    PropTypes.shape({
      codigoVoo: PropTypes.string.isRequired,
      origem: PropTypes.shape({
        nome: PropTypes.string.isRequired,
      }).isRequired,
      destino: PropTypes.shape({
        nome: PropTypes.string.isRequired,
      }).isRequired,
      dataPartida: PropTypes.string.isRequired,
      horaPartida: PropTypes.string.isRequired,
      dataChegada: PropTypes.string.isRequired,
      horaChegada: PropTypes.string.isRequired,
    })
  ).isRequired,
  onDelete: PropTypes.func.isRequired,
};

export default VoosTable;
