package org.openmrs.module.idgen.webservices.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openmrs.module.idgen.webservices.services.IdentifierSourceServiceWrapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IdgenIdentifierSourceControllerTest {
	
	@InjectMocks
	private IdgenIdentifierSourceController controller;
	
	@Mock
	private IdentifierSourceServiceWrapper identifierSourceServiceWrapper;
	
	@Test
	public void shouldGetAllIdentifierSourcesOfPrimaryIdentifierType() throws Exception {
		List<org.openmrs.module.idgen.contract.IdentifierSource> identifierSources = Arrays
		        .asList(new org.openmrs.module.idgen.contract.IdentifierSource("uuid", "name", "GAN"));
		when(identifierSourceServiceWrapper.getAllIdentifierSourcesOfPrimaryIdentifierType()).thenReturn(identifierSources);
		
		String resultIdentifierResources = controller.getAllIdentifierSourcesOfPrimaryIdentifierType();
		
		assertTrue(resultIdentifierResources.contains("\"uuid\":\"uuid\""));
		assertTrue(resultIdentifierResources.contains("\"name\":\"name\""));
		assertTrue(resultIdentifierResources.contains("\"prefix\":\"GAN\""));
	}
}
