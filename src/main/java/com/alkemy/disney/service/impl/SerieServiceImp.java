package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Serie;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.mapper.SerieMapper;
import com.alkemy.disney.repository.CharacterDatRepository;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.repository.SerieRepository;
import com.alkemy.disney.service.SerieService;
import lombok.NoArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.disney.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class SerieServiceImp implements SerieService {
    SerieRepository serieRepository;
    GenreRepository genreRepository;
    CharacterDatRepository characterDatRepository;

    SerieMapper serieMapper = SerieMapper.INSTANCE;

    @Autowired
    CharacterMapper characterMapper;

    @Autowired
    public SerieServiceImp(SerieRepository serieRepository, GenreRepository genreRepository, CharacterDatRepository characterDatRepository){
        this.serieRepository = serieRepository;
        this.genreRepository = genreRepository;
        this.characterDatRepository = characterDatRepository;
    }


    @Override
    public SerieDTO save(SeriePostDTO serie) throws DateFormatException{
        formatSeriesDate(serie);
        Serie newSerie = serieMapper.PostSerieDTOToSerie(serie);
        newSerie.setGenre(genreRepository.getById(newSerie.getGenre().getId()));
        serieRepository.save(newSerie);
        return serieMapper.seriesToDTO(newSerie);
    }

    @Override
    public void delete(Long id) {
        Optional<Serie> res = serieRepository.findById(id);
        if(res.isPresent()) {
            Serie serieToDelete = res.get();
            serieRepository.delete(serieToDelete);
        }
    }

    @Override
    public SerieDTO update(Long id, SeriePostDTO serie) throws NotFound, DateFormatException{
        Optional<Serie> res = serieRepository.findById(id);
        if(res.isPresent()){
            Serie serieToUpdate = res.get();
            serie.setId(serieToUpdate.getId());
            formatSeriesDate(serie);

            Serie updateSerie = serieMapper.PostSerieDTOToSerie(serie);
            updateSerie.setCharacters(serieToUpdate.getCharacters());
            updateSerie.setGenre(serieToUpdate.getGenre());

            serieRepository.save(updateSerie);
            return serieMapper.seriesToDTO(updateSerie);
        }
        throw new NotFound(ErrorMessages.SERIE_NOT_FOUND);
    }

    @Override
    public SerieDTO updateCharacters(Long id, Long characterId) throws NotFound{
        Optional<Serie> res = serieRepository.findById(id);
        if(res.isPresent()){
            Serie serieToUpdate = res.get();
            Optional<CharacterDat> charRes = characterDatRepository.findById(characterId);
            if (charRes.isPresent()){
                CharacterDat character = charRes.get();
                Set<CharacterDat> updatedCharacters = serieToUpdate.getCharacters();
                updatedCharacters.add(character);
                serieToUpdate.setCharacters(updatedCharacters);
                serieRepository.save(serieToUpdate);
                return serieMapper.seriesToDTO(serieToUpdate);
            }
            throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
        }
        throw new NotFound(ErrorMessages.SERIE_NOT_FOUND);
    }

    @Override
    public SerieDTO updateNewCharacter(Long id, PostCharactersDTO newChar) throws NotFound{
        /*
        otra forma?:
            CharactersDTO charToSave = CharactersService.newCharacter(newChar);
            return updateCharacter(id, charToSave.getId());
         */
        Optional<Serie> res = serieRepository.findById(id);
        if(res.isPresent()){
            Serie serieToUpdate = res.get();
            CharacterDat charToSave = characterMapper.PostCharactersDToCharacterDat(newChar);
            characterDatRepository.save(charToSave);
            serieToUpdate.getCharacters().add(charToSave);
            serieRepository.save(serieToUpdate);
            return serieMapper.seriesToDTO(serieToUpdate);
        }
        throw new NotFound(ErrorMessages.SERIE_NOT_FOUND);
    }

    @Override
    public SerieDTO deleteCharacter(Long id, Long characterId) throws NotFound{
        Optional<Serie> res = serieRepository.findById(id);
        if (res.isPresent()){
            Serie serieToUpdate = res.get();
            Optional<CharacterDat> charRes = characterDatRepository.findById(characterId);
            if(charRes.isPresent()){
                CharacterDat character = charRes.get();
                Set<CharacterDat> updatedCharacters = serieToUpdate.getCharacters();
                updatedCharacters.remove(character);
                serieToUpdate.setCharacters(updatedCharacters);
                serieRepository.save(serieToUpdate);
                return serieMapper.seriesToDTO(serieToUpdate);
            }
            throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
        }
        throw new NotFound(ErrorMessages.SERIE_NOT_FOUND);
    }

    @Override
    public SerieDTO getSerieDetails(Long id) throws NotFound{
        Optional<Serie> res = serieRepository.findById(id);
        if(res.isPresent()){
            Serie serieDetails = res.get();
            return serieMapper.seriesToDTO(serieDetails);
        }
        throw new NotFound(ErrorMessages.SERIE_NOT_FOUND);
    }

    @Override
    public List<SerieListDTO> getAllSeries() {
        return serieRepository.findAll()
                .stream().map( serieMapper::seriesToDTOList)
                .collect(Collectors.toList());
    }

    private void formatSeriesDate(SeriePostDTO serie) throws DateFormatException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try{
            serie.setReleaseDate(formatter.parse(serie.getDate()));
        } catch (ParseException e){
            throw new DateFormatException(ErrorMessages.ERROR_DATE, e.getErrorOffset());
        }
    }
}
