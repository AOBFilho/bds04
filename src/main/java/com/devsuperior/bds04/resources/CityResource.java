package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.CityInsertDTO;
import com.devsuperior.bds04.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {

    private final CityService cityService;

    @Autowired
    public CityResource(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        return ResponseEntity.ok(cityService.findAllSortedByName());
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityInsertDTO cityDTO){
        var dto = cityService.insert(cityDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
