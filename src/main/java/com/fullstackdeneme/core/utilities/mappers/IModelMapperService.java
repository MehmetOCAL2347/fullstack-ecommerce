package com.fullstackdeneme.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forResponses();
    ModelMapper forRequests();
}
