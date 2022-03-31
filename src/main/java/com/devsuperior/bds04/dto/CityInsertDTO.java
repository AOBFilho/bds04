package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.City;

import javax.validation.constraints.NotBlank;

public class CityInsertDTO {

	@NotBlank(message = "Campo requerido")
	private String name;

	public CityInsertDTO(){}

	public CityInsertDTO(String name) {
		this.name = name;
	}

	public CityInsertDTO(City entity) {
		name = entity.getName();
	}

	public String getName() {
		return name;
	}
}
