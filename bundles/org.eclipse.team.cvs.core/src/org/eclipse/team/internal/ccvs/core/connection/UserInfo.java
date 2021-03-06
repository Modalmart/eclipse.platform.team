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
package org.eclipse.team.internal.ccvs.core.connection;

 
import org.eclipse.team.internal.ccvs.core.IUserInfo;

/**
 * @version 	1.0
 * @author
 */
public class UserInfo implements IUserInfo {

	private String username;
	private String password;
	private boolean isUsernameMutable;
	
	protected UserInfo(String username, String password, boolean isUsernameMutable) {
		this.username = username;
		this.password = password;
		this.isUsernameMutable = isUsernameMutable;
	}
	
	/*
	 * @see IUserInfo#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}
	
	/*
	 * @see IUserInfo#isUsernameMutable()
	 */
	public boolean isUsernameMutable() {
		return isUsernameMutable;
	}

	/*
	 * @see IUserInfo#setPassword(String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * @see IUserInfo#setUsername(String)
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
