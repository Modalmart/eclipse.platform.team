/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.team.internal.ccvs.core.client;

 
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.internal.ccvs.core.CVSException;

public class ModuleExpansionHandler extends ResponseHandler {

	/*
	 * @see ResponseHandler#getResponseID()
	 */
	public String getResponseID() {
		return "Module-expansion";//$NON-NLS-1$
	}

	/*
	 * @see ResponseHandler#handle(Session, String, IProgressMonitor)
	 */
	public void handle(Session session, String expansion, IProgressMonitor monitor)
		throws CVSException {
			
		session.addModuleExpansion(expansion);
	}

}
