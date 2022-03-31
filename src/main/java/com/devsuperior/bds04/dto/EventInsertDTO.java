package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.Event;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class EventInsertDTO{

	@NotBlank(message = "Campo requerido")
	private String name;
	@FutureOrPresent(message = "A data do evento não pode ser passada")
	private LocalDate date;
	private String url;
	@NotNull(message="Campo requerido")
	private Long cityId;

	public EventInsertDTO() {}

	public EventInsertDTO(String name, LocalDate date, String url, Long cityId) {
		this.name = name;
		this.date = date;
		this.url = url;
		this.cityId = cityId;
	}

	public EventInsertDTO(Event entity) {
		name = entity.getName();
		date = entity.getDate();
		url = entity.getUrl();
		cityId = entity.getCity().getId();
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
