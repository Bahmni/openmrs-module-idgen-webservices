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

package org.openmrs.module.idgen.webservices;

import org.openmrs.module.idgen.IdentifierSource;

public class Identifier {
    private String value;
    private IdentifierSource source;
    private String comment;

    public Identifier() {
    }

    public Identifier(String value, IdentifierSource source, String comment) {
        this.value = value;
        this.source = source;
        this.comment = comment;
    }

    public IdentifierSource getSource() {
        return source;
    }

    public void setSource(IdentifierSource source) {
        this.source = source;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}