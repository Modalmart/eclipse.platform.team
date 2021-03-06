/*******************************************************************************
 * Copyright (c) 2005, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.compare.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.compare.internal.core.patch.LineReader;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.junit.Assert;

public class LineReaderTest extends TestCase {

	public void testReadEmpty() {
		LineReader lr= new LineReader(getReader("empty.txt")); //$NON-NLS-1$
		List inLines= lr.readLines();
		Assert.assertEquals(0, inLines.size());
	}

	public void testReadNormal() {
		LineReader lr= new LineReader(getReader("normal.txt")); //$NON-NLS-1$
		List inLines= lr.readLines();
		Assert.assertEquals(3, inLines.size());
		Assert.assertEquals("[1]\n", convertLineDelimeters(inLines.get(0))); //$NON-NLS-1$
		Assert.assertEquals("[2]\n", convertLineDelimeters(inLines.get(1))); //$NON-NLS-1$
		Assert.assertEquals("[3]\n", convertLineDelimeters(inLines.get(2))); //$NON-NLS-1$
	}

	private String convertLineDelimeters(Object object) {
		String line = (String)object;
		if (line.endsWith("\r\n"))
			return line.substring(0, line.length() - 2) + "\n";
		return line;
	}

	public void testReadUnterminatedLastLine() {
		LineReader lr= new LineReader(getReader("unterminated.txt")); //$NON-NLS-1$
		List inLines= lr.readLines();
		Assert.assertEquals(3, inLines.size());
		Assert.assertEquals("[1]\n", convertLineDelimeters(inLines.get(0))); //$NON-NLS-1$
		Assert.assertEquals("[2]\n", convertLineDelimeters(inLines.get(1))); //$NON-NLS-1$
		Assert.assertEquals("[3]", inLines.get(2)); //$NON-NLS-1$
	}

	private BufferedReader getReader(String name) {
		IPath path = new Path("linereaderdata/" + name);
		URL url;
		try {
			url = new URL(CompareTestPlugin.getDefault().getBundle().getEntry("/"), path.toString());
			InputStream resourceAsStream = url.openStream();
			InputStreamReader reader2 = new InputStreamReader(resourceAsStream);
			return new BufferedReader(reader2);
		} catch (MalformedURLException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		return null;
	}
}
