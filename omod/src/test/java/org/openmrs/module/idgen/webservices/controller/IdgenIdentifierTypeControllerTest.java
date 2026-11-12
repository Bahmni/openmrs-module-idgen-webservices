package org.openmrs.module.idgen.webservices.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.contract.IdentifierSource;
import org.openmrs.module.idgen.contract.IdentifierType;
import org.openmrs.module.idgen.webservices.services.IdentifierTypeServiceWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class IdgenIdentifierTypeControllerTest {
	
	@InjectMocks
	private IdgenIdentifierTypeController controller;
	
	@Mock
	private IdentifierTypeServiceWrapper serviceWrapper;
	
	private MockedStatic<Context> contextMock;
	
	@Before
	public void before() throws Exception {
		initMocks(this);
		contextMock = mockStatic(Context.class);
	}
	
	@After
	public void after() {
		contextMock.close();
	}
	
	@Test
	public void shouldRespondWith401WhenNotAuthenticated() throws IOException {
		contextMock.when(() -> Context.isAuthenticated()).thenReturn(false);
		ResponseEntity<String> response = controller.getPrimaryAndExtraIdentifierTypes();
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
	
	@Test
	public void shouldRespondWith401WhenGetIdentifierTypePrivilegeIsNotGivenToUser() throws IOException {
		contextMock.when(() -> Context.isAuthenticated()).thenReturn(true);
		contextMock.when(() -> Context.hasPrivilege("Get Identifier Types")).thenReturn(false);
		ResponseEntity<String> response = controller.getPrimaryAndExtraIdentifierTypes();
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
	
	@Test
	public void ShouldReturnAllIdentifierType() throws Exception {
		contextMock.when(() -> Context.isAuthenticated()).thenReturn(true);
		contextMock.when(() -> Context.hasPrivilege("Get Identifier Types")).thenReturn(true);
		
		final IdentifierSource identifierSource = new IdentifierSource("source-uuid", "some name", "SOM");
		final IdentifierType bahmniIdentifierType = new IdentifierType("uuid", "bahmni", "description", ".*", true, true,
		        Arrays.asList(identifierSource));
		
		when(serviceWrapper.getPrimaryAndExtraIdentifierTypes()).thenReturn(Arrays.asList(bahmniIdentifierType));
		
		ResponseEntity<String> allIdentifierType = controller.getPrimaryAndExtraIdentifierTypes();
		
		List<IdentifierType> identifierTypes = new ObjectMapper().readValue(allIdentifierType.getBody(),
		    new TypeReference<List<IdentifierType>>() {});
		assertEquals(1, identifierTypes.size());
		assertEquals(bahmniIdentifierType.getUuid(), identifierTypes.get(0).getUuid());
		assertEquals(bahmniIdentifierType.getFormat(), identifierTypes.get(0).getFormat());
		assertEquals(bahmniIdentifierType.getName(), identifierTypes.get(0).getName());
		assertEquals(bahmniIdentifierType.getIdentifierSources().size(),
		    identifierTypes.get(0).getIdentifierSources().size());
		
	}
	
}
