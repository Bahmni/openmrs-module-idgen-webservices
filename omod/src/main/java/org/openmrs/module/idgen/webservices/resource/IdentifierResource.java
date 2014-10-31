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
import org.openmrs.module.idgen.webservices.Identifier;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

/**
 * {@link org.openmrs.module.webservices.rest.web.annotation.Resource} for {@link org.openmrs.module.idgen.webservices.Identifier}, supporting standard CRUD operations
 */
@Resource(name = RestConstants.VERSION_1 + "/identifier", supportedClass = Identifier.class, supportedOpenmrsVersions = {"1.9.*", "1.10.*"})
public class IdentifierResource extends DelegatingCrudResource<Identifier> {

    private IdentifierSourceService identifierSourceService;

    public IdentifierResource(IdentifierSourceService identifierSourceService) {
        this.identifierSourceService = identifierSourceService;
    }

    public IdentifierResource() {
        this(Context.getService(IdentifierSourceService.class));
    }

    @Override
    public Identifier getByUniqueId(String uniqueId) {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    protected void delete(Identifier identifier, String reason, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public Identifier newDelegate() {
        return new Identifier();
    }

    @PropertySetter("source")
    public void setIdentifierSource(Identifier identifier, String source) {
        IdentifierSource identifierSource = identifierSourceService.getIdentifierSourceByUuid(source);
        identifier.setSource(identifierSource);
    }

    @Override
    public Identifier save(Identifier identifier) {
        String identifierValue = identifierSourceService.generateIdentifier(identifier.getSource(), identifier.getComment());
        return new Identifier(identifierValue, identifier.getSource(), identifier.getComment());
    }

    @Override
    public void purge(Identifier identifier, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addProperty("value");
        description.addProperty("source");
        description.addProperty("comment");
        return description;
    }
    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("source");
        description.addProperty("comment");
        return description;
    }
}