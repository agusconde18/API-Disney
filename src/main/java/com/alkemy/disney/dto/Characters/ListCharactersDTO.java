package com.alkemy.disney.dto.Characters;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCharactersDTO {

    private Long id;

    private String name;

    private String image;

}
