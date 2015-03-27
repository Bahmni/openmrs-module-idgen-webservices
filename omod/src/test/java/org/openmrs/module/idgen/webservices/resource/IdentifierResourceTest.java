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

package org.openmrs.module.idgen.webservices.resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openmrs.module.idgen.webservices.Identifier;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.service.IdentifierSourceService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class IdentifierResourceTest {

    @Mock
    private IdentifierSourceService identifierSourceService;
    @Mock
    private IdentifierSource identifierSource;
    private IdentifierResource identifierResource;

    @Before
    public void setUp() {
        initMocks(this);
        identifierResource = new IdentifierResource(identifierSourceService);
    }

    @Test
    public void setIdentifierSource_shouldSetSourceBasedOnUuid() throws Exception {
        String identifierSourceUuid = UUID.randomUUID().toString();
        Identifier identifier = new Identifier();
        when(identifierSourceService.getIdentifierSourceByUuid(identifierSourceUuid)).thenReturn(identifierSource);

        identifierResource.setIdentifierSource(identifier, identifierSourceUuid);

        assertEquals(identifierSource, identifier.getSource());
    }

    @Test
    public void save_shouldGenerateNewIdentifier() throws Exception {
        Identifier identifier = new Identifier();
        identifier.setSource(identifierSource);
        identifier.setComment("Testing id");
        when(identifierSourceService.generateIdentifier(identifierSource, identifier.getComment())).thenReturn("OPD1");

        Identifier generatedIdentifier = identifierResource.save(identifier);

        assertEquals("OPD1", generatedIdentifier.getValue());
        assertEquals(identifier.getSource(), generatedIdentifier.getSource());
        assertEquals(identifier.getComment(), generatedIdentifier.getComment());
    }
}