package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.CityInsertDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public List<CityDTO> findAllSortedByName(){
        return this.cityRepository.findAll(Sort.by("name"))
                .stream().map(city -> new CityDTO(city))
                .collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityInsertDTO cityDTO){
        var city = new City();
        city.setName(cityDTO.getName());
        return new CityDTO(this.cityRepository.save(city));
    }
}
