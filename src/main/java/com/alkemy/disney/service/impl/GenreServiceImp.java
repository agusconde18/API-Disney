package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.ErrorMessages;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
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
    GenreMapper genreMapper = GenreMapper.INSTANCE;

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
        Optional<Genre> res = genreRepository.findById(id);
        if(res.isPresent()){
            Genre genreToUpdate = res.get();
            if(!genre.getName().isEmpty()){
                genre.setId(genreToUpdate.getId());
                Genre updateGenre = genreMapper.DTOToGenre(genre);
                genreRepository.save(updateGenre);
                return genreMapper.GenreToDTO(updateGenre);
            }
        }
        throw new NotFound(ErrorMessages.GENRE_NOT_FOUND);
    }

    @Override
    public List<GenreDTO> getAll(){
        return genreRepository.findAll()
                .stream().map(genreMapper::GenreToDTO)
                .collect(Collectors.toList());
    }
}
