package com.mapper;

import com.dto.InnerDTORequest;
import com.dto.RequestDTO;

public interface SimpleMapper {

    InnerDTORequest sourceToDestination(RequestDTO source);
    RequestDTO destinationToSource(InnerDTORequest destination);

}
