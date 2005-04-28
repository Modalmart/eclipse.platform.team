/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.team.internal.ccvs.ui.merge;


import java.util.Date;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.ui.CVSUIPlugin;
import org.eclipse.team.internal.ccvs.ui.ICVSUIConstants;
import org.eclipse.team.internal.ccvs.ui.model.CVSTagElement;
import org.eclipse.ui.model.IWorkbenchAdapter;

public class TagElement implements IWorkbenchAdapter, IAdaptable {
	CVSTag tag;
	public TagElement(CVSTag tag) {
		this.tag = tag;
	}
	public Object[] getChildren(Object o) {
		return new Object[0];
	}
	public Object getAdapter(Class adapter) {
		if (adapter == IWorkbenchAdapter.class) return this;
		return null;
	}
	public ImageDescriptor getImageDescriptor(Object object) {
		if (tag.getType() == CVSTag.BRANCH || tag == CVSTag.DEFAULT) {
			return CVSUIPlugin.getPlugin().getImageDescriptor(ICVSUIConstants.IMG_TAG);
		} else if (tag.getType() == CVSTag.DATE){
			return CVSUIPlugin.getPlugin().getImageDescriptor(ICVSUIConstants.IMG_DATE);
		}else {
			return CVSUIPlugin.getPlugin().getImageDescriptor(ICVSUIConstants.IMG_PROJECT_VERSION);
		}
	}
	public String getLabel(Object o) {
		if(tag.getType() == CVSTag.DATE){
			Date date = tag.asDate();
			if (date != null){
				return CVSTagElement.toDisplayString(date);
			}
		}
		return tag.getName();
	}
	public Object getParent(Object o) {
		return null;
	}
	public CVSTag getTag() {
		return tag;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return tag.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TagElement) {
			return tag.equals(((TagElement)obj).getTag());
		}
		return super.equals(obj);
	}
}