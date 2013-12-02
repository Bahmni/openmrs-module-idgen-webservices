package org.openmrs.module.idgen.serialization;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.module.idgen.contract.IdentifierSource;

import java.io.IOException;
import java.util.List;

public class ObjectMapperRepository {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public String writeValueAsString(List<IdentifierSource> result) throws IOException {
        return objectMapper.writeValueAsString(result);
    }
}