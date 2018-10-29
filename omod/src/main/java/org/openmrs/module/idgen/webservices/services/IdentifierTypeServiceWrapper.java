package org.openmrs.module.idgen.webservices.services;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.idgen.contract.IdentifierType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IdentifierTypeServiceWrapper extends OpenmrsService {
	
	@Transactional(readOnly = true)
	List<IdentifierType> getPrimaryAndExtraIdentifierTypes();
}
