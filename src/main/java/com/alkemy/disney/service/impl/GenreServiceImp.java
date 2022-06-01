package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.ErrorMessages;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import javassist.NotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class GenreServiceImp implements GenreService {

    GenreRepository genreRepository;
    @Autowired
    GenreMapper genreMapper;

    @Autowired
    public GenreServiceImp(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDTO save(GenreDTO genreDTO){
        Genre genreToSave = genreMapper.DTOToGenre(genreDTO);
        genreRepository.save(genreToSave);
        return genreMapper.GenreToDTO(genreToSave);
    }

    @Override
    public GenreDTO update(GenreDTO genre, Long id) throws NotFound {
        Genre res = this.genreRepository.findById(id)
                .orElseThrow(() -> new NotFound(ErrorMessages.GENRE_NOT_FOUND));
                Genre updateGenre = genreMapper.refreshValues(res, genre);
                genreRepository.save(updateGenre);
                return genreMapper.GenreToDTO(updateGenre);
    }

    @Override
    public List<GenreDTO> getAll(){
        return genreRepository.findAll()
                .stream().map(genreMapper::GenreToDTO)
                .collect(Collectors.toList());
    }
}
