/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.idgen.mapper;

import org.openmrs.module.idgen.SequentialIdentifierGenerator;
import org.openmrs.module.idgen.contract.IdentifierSource;

import java.util.ArrayList;
import java.util.List;

public class IdentifierSourceListMapper {
	
	public static List<org.openmrs.module.idgen.contract.IdentifierSource> map(
	        List<org.openmrs.module.idgen.IdentifierSource> identifierSourcesList) {
		List<org.openmrs.module.idgen.contract.IdentifierSource> result = new ArrayList<IdentifierSource>();
		for (org.openmrs.module.idgen.IdentifierSource identifierSource : identifierSourcesList) {
			String prefix = null;
			if (identifierSource instanceof SequentialIdentifierGenerator) {
				prefix = ((SequentialIdentifierGenerator) (identifierSource)).getPrefix();
			}
			result.add(new org.openmrs.module.idgen.contract.IdentifierSource(identifierSource.getUuid(),
			        identifierSource.getName(), prefix));
		}
		return result;
	}
}
