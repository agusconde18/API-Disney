package com.alkemy.disney.dto.Characters;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharactersDTOSet {
    private Long id;

    private String name;

    private Long age;

    private String image;

    private Float weight;

    private String story;

}
