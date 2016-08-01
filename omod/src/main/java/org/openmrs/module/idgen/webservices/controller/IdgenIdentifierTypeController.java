package org.openmrs.module.idgen.webservices.controller;

import org.openmrs.module.idgen.contract.IdentifierType;
import org.openmrs.module.idgen.serialization.ObjectMapperRepository;
import org.openmrs.module.idgen.webservices.services.IdentifierTypeServiceWrapper;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/idgen/identifiertype")
public class IdgenIdentifierTypeController {

    @Autowired
    IdentifierTypeServiceWrapper identifierTypeServiceWrapper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getPrimaryAndExtraIdentifierTypes() throws IOException {

        final List<IdentifierType> allIdentifierType = identifierTypeServiceWrapper.getPrimaryAndExtraIdentifierTypes();
        ObjectMapperRepository objectMapperRepository = new ObjectMapperRepository();
        return objectMapperRepository.writeValueAsString(allIdentifierType);
    }
}
