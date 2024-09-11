// src/components/VoosTable/VoosTable.jsx
import React from "react";
import PropTypes from "prop-types";
import {
  Table,
  TableHeader,
  TableBody,
  TableRow,
  TableData,
  Icon,
} from "./styles";
import { FaTrashAlt, FaEdit } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

function VoosTable({ voos, onDelete }) {
  const navigate = useNavigate();

  const handleEdit = (id) => {
    navigate(`/atualizar/${id}`);
  };

  const handleDelete = (id) => {
    if (window.confirm("Tem certeza que deseja excluir este voo?")) {
      onDelete(id);
    }
  };

  return (
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
        {voos.map((voo) => (
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
  );
}

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
