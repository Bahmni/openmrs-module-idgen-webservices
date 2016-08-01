package org.openmrs.module.idgen.webservices.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openmrs.module.idgen.contract.IdentifierSource;
import org.openmrs.module.idgen.contract.IdentifierType;
import org.openmrs.module.idgen.webservices.services.IdentifierTypeServiceWrapper;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
public class IdgenIdentifierTypeControllerTest {
    @InjectMocks
    private IdgenIdentifierTypeController controller;

    @Mock
    private IdentifierTypeServiceWrapper serviceWrapper;

    @Test
    public void ShouldReturnAllIdentifierType() throws Exception {
        final IdentifierSource identifierSource = new IdentifierSource("source-uuid", "some name", "SOM");
        final IdentifierType bahmniIdentifierType = new IdentifierType("uuid", "bahmni", "description", ".*", true, true, Arrays.asList(identifierSource));

        when(serviceWrapper.getPrimaryAndExtraIdentifierTypes()).thenReturn(Arrays.asList(bahmniIdentifierType));

        String allIdentifierType = controller.getPrimaryAndExtraIdentifierTypes();

        List<IdentifierType> identifierTypes = new ObjectMapper().readValue(allIdentifierType, new TypeReference<List<IdentifierType>>() {
        });
        assertEquals(1, identifierTypes.size());
        assertEquals(bahmniIdentifierType.getUuid(), identifierTypes.get(0).getUuid());
        assertEquals(bahmniIdentifierType.getFormat(), identifierTypes.get(0).getFormat());
        assertEquals(bahmniIdentifierType.getName(), identifierTypes.get(0).getName());
        assertEquals(bahmniIdentifierType.getIdentifierSources().size(), identifierTypes.get(0).getIdentifierSources().size());

    }


}
