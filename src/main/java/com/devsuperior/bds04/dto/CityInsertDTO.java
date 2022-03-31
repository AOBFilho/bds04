package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.City;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class CityInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Campo requerido")
	private String name;

	public CityInsertDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CityInsertDTO(City entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
