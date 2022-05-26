package com.alkemy.disney.dto.Characters;

import com.alkemy.disney.entity.Film;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ListCharactersDTO {

    private Long id;

    private String name;

    private String image;

}
