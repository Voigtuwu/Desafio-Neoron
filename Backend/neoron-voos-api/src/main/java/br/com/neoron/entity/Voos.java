package br.com.neoron.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Voos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID codigoVoo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "origem_id")
	private Aeroportos origem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "destino_id")
	private Aeroportos destino;

	private LocalDate dataPartida;
	private LocalDate dataChegada;
	private LocalDateTime horaPartida;
	private LocalDateTime horaChegada;

	public Voos() {

	}

	public Voos(UUID codigoVoo, Aeroportos origem, Aeroportos destino, LocalDate dataPartida, LocalDate dataChegada,
			LocalDateTime horaPartida, LocalDateTime horaChegada) {
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

	public LocalDateTime getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(LocalDateTime horaPartida) {
		this.horaPartida = horaPartida;
	}

	public LocalDateTime getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(LocalDateTime horaChegada) {
		this.horaChegada = horaChegada;
	}

}
