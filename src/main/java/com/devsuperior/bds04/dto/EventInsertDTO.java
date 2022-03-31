package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.Event;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class EventInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Campo requerido")
	private String name;
	@FutureOrPresent(message = "A data do evento não pode ser passada")
	private LocalDate date;
	private String url;
	@NotNull(message="Campo requerido")
	private Long cityId;

	public EventInsertDTO(Long id, String name, LocalDate date, String url, Long cityId) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.url = url;
		this.cityId = cityId;
	}

	public EventInsertDTO(Event entity) {
		id = entity.getId();
		name = entity.getName();
		date = entity.getDate();
		url = entity.getUrl();
		cityId = entity.getCity().getId();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getUrl() {
		return url;
	}

	public Long getCityId() {
		return cityId;
	}
}
