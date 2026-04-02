/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.idgen.webservices.controller;

public class GenerateIdentifierRequest {
	
	private String identifierSourceName;
	
	private String comment;
	
	public GenerateIdentifierRequest() {
	}
	
	public GenerateIdentifierRequest(String identifierSourceName, String comment) {
		this.identifierSourceName = identifierSourceName;
		this.comment = comment;
	}
	
	public String getIdentifierSourceName() {
		return identifierSourceName;
	}
	
	public void setIdentifierSourceName(String identifierSourceName) {
		this.identifierSourceName = identifierSourceName;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
