/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.compare.examples.xml;

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import org.eclipse.jface.action.*;


/**
 * Drop down menu to select a particular id mapping scheme
 */
class ChooseMatcherDropDownAction extends Action implements IMenuCreator {

	private XMLStructureViewer fViewer;

	public ChooseMatcherDropDownAction(XMLStructureViewer viewer) {
		fViewer = viewer;
		setText(XMLCompareMessages.ChooseMatcherDropDownAction_text); 
		setImageDescriptor(XMLPlugin.getDefault().getImageDescriptor("obj16/smartmode_co.gif")); //$NON-NLS-1$
		setToolTipText(XMLCompareMessages.ChooseMatcherDropDownAction_tooltip); 
		setMenuCreator(this);
	}

	@Override
	public void dispose() {
		// nothing to do
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}

	@Override
	public Menu getMenu(Control parent) {
		XMLPlugin plugin= XMLPlugin.getDefault();
		Menu menu= new Menu(parent);
		addActionToMenu(menu, new SelectMatcherAction(XMLStructureCreator.USE_UNORDERED, fViewer));
		addActionToMenu(menu, new SelectMatcherAction(XMLStructureCreator.USE_ORDERED, fViewer));
		new MenuItem(menu, SWT.SEPARATOR);
		HashMap IdMaps = plugin.getIdMaps();
		HashMap IdMapsInternal = plugin.getIdMapsInternal();

		Set keySetIdMaps = IdMaps.keySet();
		Set keySetIdMapsInternal = IdMapsInternal.keySet();
		ArrayList internalIdMapsAL= new ArrayList();
		for (Iterator iter_internal = keySetIdMapsInternal.iterator(); iter_internal.hasNext(); ) {
			String idmap_name = (String)iter_internal.next();
			internalIdMapsAL.add(idmap_name);
		}
		Object[] internalIdMapsA= internalIdMapsAL.toArray();
		Arrays.sort(internalIdMapsA);
		for (int i= 0; i < internalIdMapsA.length; i++) {
			addActionToMenu(menu, new SelectMatcherAction((String)internalIdMapsA[i], fViewer));
		}
		new MenuItem(menu, SWT.SEPARATOR);

		ArrayList userIdMapsAL= new ArrayList();
		for (Iterator iter_idmaps = keySetIdMaps.iterator(); iter_idmaps.hasNext(); ) {
			String idmap_name = (String)iter_idmaps.next();
			userIdMapsAL.add(idmap_name);
		}
		
		HashMap OrderedElements= plugin.getOrderedElements();
		Set keySetOrdered= OrderedElements.keySet();
		for (Iterator iter_orderedElements= keySetOrdered.iterator(); iter_orderedElements.hasNext();) {
			String idmap_name= (String) iter_orderedElements.next();
			if (!keySetIdMaps.contains(idmap_name)) {
				userIdMapsAL.add(idmap_name);
			}
		}

		Object[] userIdMapsA= userIdMapsAL.toArray();
		Arrays.sort(userIdMapsA);
		for (int i= 0; i < userIdMapsA.length; i++) {
			addActionToMenu(menu, new SelectMatcherAction((String)userIdMapsA[i], fViewer));
		}
		
		return menu;
	}

	protected void addActionToMenu(Menu parent, Action action) {
		ActionContributionItem item= new ActionContributionItem(action);
		item.fill(parent, -1);
	}

	@Override
	public void run() {
		fViewer.contentChanged();
	}
}
