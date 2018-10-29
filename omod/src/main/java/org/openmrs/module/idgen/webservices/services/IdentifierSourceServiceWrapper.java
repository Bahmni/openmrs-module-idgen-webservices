package org.openmrs.module.idgen.webservices.services;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.idgen.contract.IdentifierSource;
import org.openmrs.util.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IdentifierSourceServiceWrapper extends OpenmrsService {
	
	@Authorized(value = { PrivilegeConstants.PRIV_MANAGE_IDENTIFIER_SEQUENCE }, requireAll = true)
	Long saveSequenceValue(long identifier, String sourceName) throws Exception;
	
	String getSequenceValue(String sourceName) throws Exception;
	
	String generateIdentifier(String sourceName, String comment) throws Exception;
	
	List<IdentifierSource> getAllIdentifierSources();
	
	List<IdentifierSource> getAllIdentifierSourcesOfPrimaryIdentifierType();
	
	String generateIdentifierUsingIdentifierSourceUuid(String identifierSourceUuid, String comment) throws Exception;
	
	String getSequenceValueUsingIdentifierSourceUuid(String uuid) throws Exception;
	
	Long saveSequenceValueUsingIdentifierSourceUuid(long identifier, String uuid) throws Exception;
	
}
