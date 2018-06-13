package com.mapper;

import com.dto.InnerDTORequest;
import com.dto.RequestDTO;
import com.dto.RequestDTO.Builder;
import com.mapper.SimpleMapper.BuilderFactory;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-06-13T14:59:22+0530",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
public class SimpleMapperImpl implements SimpleMapper {

    private final BuilderFactory builderFactory = new BuilderFactory();

    @Override
    public InnerDTORequest sourceToDestination(RequestDTO source) {
        if ( source == null ) {
            return null;
        }

        InnerDTORequest innerDTORequest = new InnerDTORequest();

        if ( source.getUsername() != null ) {
            innerDTORequest.setUsername( source.getUsername() );
        }
        if ( source.getPassword() != null ) {
            innerDTORequest.setPassword( source.getPassword() );
        }

        return innerDTORequest;
    }

    @Override
    public Builder destinationToSource(InnerDTORequest destination) {
        if ( destination == null ) {
            return null;
        }

        Builder builder = builderFactory.useRequestDTO();

        if ( destination.getUsername() != null ) {
            builder.setUsername( destination.getUsername() );
        }
        if ( destination.getPassword() != null ) {
            builder.setPassword( destination.getPassword() );
        }

        return builder;
    }
}
