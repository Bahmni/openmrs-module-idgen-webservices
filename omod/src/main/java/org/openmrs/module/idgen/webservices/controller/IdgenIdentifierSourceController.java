package org.openmrs.module.idgen.webservices.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/idgen")
public class IdgenIdentifierSourceController extends BaseRestController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String generateIdentifier(@RequestBody GenerateIdentifierRequest request) {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        List<IdentifierSource> allIdentifierSources = identifierSourceService.getAllIdentifierSources(false);
        for (IdentifierSource identifierSource : allIdentifierSources) {
            if (identifierSource.getName().equals(request.getIdentifierSourceName())) {
                return identifierSourceService.generateIdentifier(identifierSource, request.getComment());
            }
        }
        return null;
    }

}

