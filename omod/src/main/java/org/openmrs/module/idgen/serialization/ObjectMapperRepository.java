package org.openmrs.module.idgen.serialization;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.openmrs.module.idgen.contract.IdentifierSource;
import org.openmrs.module.idgen.contract.IdentifierType;

import java.io.IOException;
import java.util.List;

public class ObjectMapperRepository<E> {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public String writeValueAsString(List<E> result) throws IOException {
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.writeValueAsString(result);
    }
}