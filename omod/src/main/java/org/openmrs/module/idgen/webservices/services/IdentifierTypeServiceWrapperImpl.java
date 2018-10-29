package org.openmrs.module.idgen.webservices.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openmrs.PatientIdentifierType;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.contract.IdentifierType;
import org.openmrs.module.idgen.mapper.IdentifierSourceListMapper;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.openmrs.module.idgen.webservices.IdgenWsConstants;

public class IdentifierTypeServiceWrapperImpl extends BaseOpenmrsService implements IdentifierTypeServiceWrapper {
	
	public List<IdentifierType> getPrimaryAndExtraIdentifierTypes() {
		IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
		AdministrationService administrationService = Context.getAdministrationService();
		Map<PatientIdentifierType, List<IdentifierSource>> identifierSourcesByType = identifierSourceService
		        .getIdentifierSourcesByType(false);
		String primaryIdentifierTypeUuid = administrationService.getGlobalProperty(IdgenWsConstants.GP_PRIMARY_IDTYPE);
		String extraIdentifierTypeUuid = administrationService.getGlobalProperty(IdgenWsConstants.GP_EXTRA_IDTYPES);
		final String[] extraIdentifierTypeUuids = extraIdentifierTypeUuid != null ? extraIdentifierTypeUuid.split(",")
		        : new String[] {};
		return mapToContractObject(identifierSourcesByType, primaryIdentifierTypeUuid,
		    Arrays.asList(extraIdentifierTypeUuids));
	}
	
	private List<IdentifierType> mapToContractObject(
	        Map<PatientIdentifierType, List<IdentifierSource>> identifierSourcesByType, String primaryIdentifierTypeUuid,
	        List<String> extraIdentifierTypeUuids) {
		List<IdentifierType> identifierTypes = new ArrayList<IdentifierType>();
		Set<PatientIdentifierType> patientIdentifierTypes = identifierSourcesByType.keySet();
		for (PatientIdentifierType patientIdentifierType : patientIdentifierTypes) {
			boolean isPrimary = primaryIdentifierTypeUuid.equals(patientIdentifierType.getUuid());
			if (isPrimary || extraIdentifierTypeUuids.contains(patientIdentifierType.getUuid())) {
				List<org.openmrs.module.idgen.contract.IdentifierSource> identifierSources = IdentifierSourceListMapper
				        .map(identifierSourcesByType.get(patientIdentifierType));
				IdentifierType identifierType = new IdentifierType(patientIdentifierType.getUuid(),
				        patientIdentifierType.getName(), patientIdentifierType.getDescription(),
				        patientIdentifierType.getFormat(), patientIdentifierType.getRequired(), isPrimary,
				        identifierSources);
				identifierTypes.add(identifierType);
			}
		}
		return identifierTypes;
	}
}
