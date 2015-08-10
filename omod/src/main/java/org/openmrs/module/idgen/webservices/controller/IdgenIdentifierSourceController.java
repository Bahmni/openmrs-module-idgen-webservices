package org.openmrs.module.idgen.webservices.controller;

import org.openmrs.module.idgen.serialization.ObjectMapperRepository;
import org.openmrs.module.idgen.webservices.services.IdentifierSourceServiceWrapper;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/idgen")
public class IdgenIdentifierSourceController extends BaseRestController {

    @Autowired
    private IdentifierSourceServiceWrapper identifierSourceServiceWrapper;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String generateIdentifier(@RequestBody GenerateIdentifierRequest request) throws Exception {
        return identifierSourceServiceWrapper.generateIdentifier(request.getIdentifierSourceName(), request.getComment());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/identifiersources")
    @ResponseBody
    public String getAllIdentifierSources() throws IOException {
        List<org.openmrs.module.idgen.contract.IdentifierSource> result = identifierSourceServiceWrapper.getAllIdentifierSources();
        ObjectMapperRepository objectMapperRepository = new ObjectMapperRepository();
        return objectMapperRepository.writeValueAsString(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/latestidentifier")
    @ResponseBody
    public String getSequenceValue(@RequestParam(value = "sourceName") String sourceName) throws Exception {
        return identifierSourceServiceWrapper.getSequenceValue(sourceName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/latestidentifier")
    @ResponseBody
    public Long saveSequenceValue(@RequestBody SetLatestIdentifierRequest request) throws Exception {
        identifierSourceServiceWrapper.saveSequenceValue(request.getIdentifier(), request.getSourceName());
        return request.getIdentifier();
    }
}

