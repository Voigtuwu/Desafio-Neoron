package br.com.neoron.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name = "origem_id")
	private Aeroportos origem;
	
	@ManyToOne
	@JoinColumn(name = "destino_id")
	private Aeroportos destino;
	
	private LocalDate data;
	private LocalDateTime hora;
	
	
	public Voos() {
		
	}

	public Voos(UUID codigoVoo, Aeroportos origem, Aeroportos destino, LocalDate data, LocalDateTime hora) {
		this.codigoVoo = codigoVoo;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}
	
}
