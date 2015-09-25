/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.contacts.server.services.common.security;

import java.security.AllPermission;
import java.security.Permissions;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.server.services.common.security.AbstractAccessControlService;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;

@Order(-1)
public class AccessControlService extends AbstractAccessControlService {

  @Override
  protected Permissions execLoadPermissions() {
    Permissions permissions = new Permissions();
    permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"));

    //TODO [dwi]: Fill access control service
    permissions.add(new AllPermission());
    return permissions;
  }
}
