package org.eclipse.scout.widgets.client.ui.desktop.pages;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

/**
 * <h3>{@link NodePageWithChildTableChild}</h3>
 *
 * @author aho
 */
public class NodePageWithChildTableChild extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return "Node page with child table page";
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new PageWithTableTablePage().withChildPageClass(NodePageWithChildTableChild.class));
  }
}
