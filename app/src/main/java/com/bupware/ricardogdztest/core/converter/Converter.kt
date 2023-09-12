package com.bupware.ricardogdztest.core.converter

import com.bupware.ricardogdztest.core.DTO.Location
import com.bupware.ricardogdztest.core.DTO.Origin
import com.bupware.ricardogdztest.core.DTO.Character as CharacterDTO
import com.bupware.ricardogdztest.core.room.character.Character as CharacterLocal

object Converter {

    fun characterDtoToLocal(characterDto: CharacterDTO): CharacterLocal{
        return CharacterLocal(
            id = characterDto.id,
            name = characterDto.name,
            status = characterDto.status,
            species = characterDto.species,
            type = characterDto.type,
            gender = characterDto.gender,
            originName = characterDto.origin.name,
            originUrl = characterDto.origin.url,
            locationName = characterDto.location.name,
            locationUrl = characterDto.location.url,
            image = characterDto.image, //TODO ESTO A URI
            url = characterDto.url,
            created = characterDto.created
        )
    }

    fun characterLocalToDto(characterLocal: CharacterLocal): CharacterDTO{
        return CharacterDTO(
            id = characterLocal.id,
            name = characterLocal.name,
            status = characterLocal.status,
            species = characterLocal.species,
            type = characterLocal.type,
            gender = characterLocal.gender,
            origin = Origin(characterLocal.originName,characterLocal.originUrl),
            location = Location(characterLocal.locationName,characterLocal.locationUrl),
            image = characterLocal.image, //TODO REVISAR?
            episode = emptyList(),
            url = characterLocal.url,
            created = characterLocal.created
        )
    }

}