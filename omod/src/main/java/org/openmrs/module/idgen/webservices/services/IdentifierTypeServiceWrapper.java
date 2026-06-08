/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.idgen.webservices.services;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.idgen.contract.IdentifierType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IdentifierTypeServiceWrapper extends OpenmrsService {
	
	@Transactional(readOnly = true)
	List<IdentifierType> getPrimaryAndExtraIdentifierTypes();
}
