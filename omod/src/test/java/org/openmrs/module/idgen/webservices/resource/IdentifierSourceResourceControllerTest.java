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

import org.junit.Before;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;

public class IdentifierSourceResourceControllerTest extends MainResourceControllerTest {

    @Before
    public void setUp() throws Exception {
        executeDataSet("identfierSource.xml");
    }

    @Override
    public String getURI() {
        return "identifiersource";
    }

    @Override
    public String getUuid() {
        return "a01e36e1-a2a1-11e3-af88-005056821db0";
    }

    @Override
    public long getAllCount() {
        return 1;
    }
}