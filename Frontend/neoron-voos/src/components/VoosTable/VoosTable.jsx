// src/components/VoosTable/VoosTable.jsx
import React from "react";
import PropTypes from "prop-types";
import { Table, TableHeader, TableBody, TableRow, TableData } from "./styles";

function VoosTable({ voos }) {
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
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
}

// Definindo as propriedades esperadas
VoosTable.propTypes = {
  voos: PropTypes.arrayOf(
    PropTypes.shape({
      codigoVoo: PropTypes.string.isRequired,
      origem: PropTypes.shape({
        nome: PropTypes.string.isRequired
      }).isRequired,
      destino: PropTypes.shape({
        nome: PropTypes.string.isRequired
      }).isRequired,
      dataPartida: PropTypes.string.isRequired,
      horaPartida: PropTypes.string.isRequired,
      dataChegada: PropTypes.string.isRequired,
      horaChegada: PropTypes.string.isRequired
    })
  ).isRequired
};

export default VoosTable;
