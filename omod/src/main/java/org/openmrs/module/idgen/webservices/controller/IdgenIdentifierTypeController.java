package org.openmrs.module.idgen.webservices.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.contract.IdentifierType;
import org.openmrs.module.idgen.serialization.ObjectMapperRepository;
import org.openmrs.module.idgen.webservices.IdgenWsConstants;
import org.openmrs.module.idgen.webservices.services.IdentifierTypeServiceWrapper;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/" + IdgenWsConstants.PATH_IDGEN_IDTYPE)
public class IdgenIdentifierTypeController {
	
	public final static String encoding = Charset.forName("UTF-8").toString();

	public final static String contentType = "application/json;charset=" + encoding;
	
	@Autowired
	IdentifierTypeServiceWrapper identifierTypeServiceWrapper;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getPrimaryAndExtraIdentifierTypes() throws IOException {
		if (!Context.isAuthenticated() || !Context.hasPrivilege("Get Identifier Types")) {
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}
		
		final List<IdentifierType> allIdentifierType = identifierTypeServiceWrapper.getPrimaryAndExtraIdentifierTypes();
		ObjectMapperRepository objectMapperRepository = new ObjectMapperRepository();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.set("Content-Type", contentType);
		return new ResponseEntity<String>(objectMapperRepository.writeValueAsString(allIdentifierType), headers,
		        HttpStatus.OK);
	}
}
