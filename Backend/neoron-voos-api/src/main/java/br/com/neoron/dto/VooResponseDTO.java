package br.com.neoron.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.neoron.entity.Aeroportos;

public class VooResponseDTO {

	
	// DTO de resposta para formatação das datas
	private UUID codigoVoo;
	private Aeroportos origem;
	private Aeroportos destino;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPartida;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataChegada;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaPartida;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaChegada;

	public VooResponseDTO() {

	}

	public VooResponseDTO(UUID codigoVoo, Aeroportos origem, Aeroportos destino, LocalDate dataPartida, LocalDate dataChegada,
			LocalTime horaPartida, LocalTime horaChegada) {
		super();
		this.codigoVoo = codigoVoo;
		this.origem = origem;
		this.destino = destino;
		this.dataPartida = dataPartida;
		this.dataChegada = dataChegada;
		this.horaPartida = horaPartida;
		this.horaChegada = horaChegada;
	}

	public UUID getCodigoVoo() {
		return codigoVoo;
	}

	public void setCodigoVoo(UUID codigoVoo) {
		this.codigoVoo = codigoVoo;
	}

	public Aeroportos getOrigem() {
		return origem;
	}

	public void setOrigem(Aeroportos origem) {
		this.origem = origem;
	}

	public Aeroportos getDestino() {
		return destino;
	}

	public void setDestino(Aeroportos destino) {
		this.destino = destino;
	}

	public LocalDate getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(LocalDate dataPartida) {
		this.dataPartida = dataPartida;
	}

	public LocalDate getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(LocalDate dataChegada) {
		this.dataChegada = dataChegada;
	}

	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}

	public LocalTime getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(LocalTime horaChegada) {
		this.horaChegada = horaChegada;
	}
}
