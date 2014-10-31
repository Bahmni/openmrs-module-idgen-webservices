package org.openmrs.module.idgen.webservices.resource;

/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

/**
 * {@link org.openmrs.module.webservices.rest.web.annotation.Resource} for {@link org.openmrs.module.idgen.IdentifierSource}, supporting standard CRUD operations
 */
@Resource(name = RestConstants.VERSION_1 + "/identifiersource", supportedClass = IdentifierSource.class, supportedOpenmrsVersions = {"1.9.*", "1.10.*"})
public class IdentifierSourceResource extends DelegatingCrudResource<IdentifierSource> {

    public IdentifierSourceResource() {
        allowedMissingProperties.add("prefix");
    }

    @Override
    public IdentifierSource getByUniqueId(String uniqueId) {
        return Context.getService(IdentifierSourceService.class).getIdentifierSourceByUuid(uniqueId);
    }

    @Override
    protected void delete(IdentifierSource identifierSource, String reason, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public IdentifierSource newDelegate() {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public IdentifierSource save(IdentifierSource identifierSource) {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public void purge(IdentifierSource identifierSource, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    protected PageableResult doGetAll(RequestContext context) throws ResponseException {
        IdentifierSourceService identifierSourceService = Context.getService(IdentifierSourceService.class);
        List<IdentifierSource> identifierSources = identifierSourceService.getAllIdentifierSources(context.getIncludeAll());
        return new NeedsPaging<IdentifierSource>(identifierSources, context);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("uuid");
        description.addProperty("name");
        description.addProperty("prefix");
        description.addSelfLink();
        return description;
    }
}
