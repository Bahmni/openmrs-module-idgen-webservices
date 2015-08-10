package org.openmrs.module.idgen.webservices.services;

import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.SequentialIdentifierGenerator;
import org.openmrs.module.idgen.serialization.ObjectMapperRepository;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class IdentifierSourceServiceWrapperImpl extends BaseOpenmrsService implements IdentifierSourceServiceWrapper {
    @Override
    public Long saveSequenceValue(long identifier, String sourceName) throws Exception {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        IdentifierSource identifierSource = getIdentifierSource(sourceName);
        identifierSourceService.saveSequenceValue((SequentialIdentifierGenerator)identifierSource, identifier);
        return identifier;
    }

    @Override
    public String getSequenceValue(String sourceName) throws Exception {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        IdentifierSource identifierSource = getIdentifierSource(sourceName);
        Long sequenceValue = identifierSourceService.getSequenceValue((SequentialIdentifierGenerator) identifierSource);
        return sequenceValue.toString();
    }

    @Override
    public String generateIdentifier(String sourceName, String comment) throws Exception {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        IdentifierSource identifierSource = getIdentifierSource(sourceName);
        return identifierSourceService.generateIdentifier(identifierSource, comment);
    }

    @Override
    public List<org.openmrs.module.idgen.contract.IdentifierSource> getAllIdentifierSources() {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        List<IdentifierSource> allIdentifierSources = identifierSourceService.getAllIdentifierSources(false);

        List<org.openmrs.module.idgen.contract.IdentifierSource> result = new ArrayList<org.openmrs.module.idgen.contract.IdentifierSource>();

        for (IdentifierSource identifierSource : allIdentifierSources) {
            String prefix = null;
            if (identifierSource instanceof SequentialIdentifierGenerator) {
                prefix = ((SequentialIdentifierGenerator) (identifierSource)).getPrefix();
            }
            result.add(new org.openmrs.module.idgen.contract.IdentifierSource(identifierSource.getUuid(), identifierSource.getName(), prefix));
        }
        return result;
    }

    private IdentifierSource getIdentifierSource(String sourceName) throws Exception {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        List<IdentifierSource> allIdentifierSources = identifierSourceService.getAllIdentifierSources(false);
        for (IdentifierSource identifierSource : allIdentifierSources) {
            if (identifierSource.getName().equals(sourceName)) {
                return identifierSource;
            }
        }
        throw new Exception("No matching Identifier source found for: " + sourceName);
    }
}
