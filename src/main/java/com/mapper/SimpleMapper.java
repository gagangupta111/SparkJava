package com.mapper;

import com.dto.InnerDTORequest;
import com.dto.RequestDTO;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                SimpleMapper.BuilderFactory.class
        },
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface SimpleMapper {
    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);

    InnerDTORequest sourceToDestination(RequestDTO source);
    RequestDTO.Builder destinationToSource(InnerDTORequest destination);

    class BuilderFactory {

        RequestDTO.Builder useRequestDTO() {
            return RequestDTO.newBuilder();
        }

    }

}
