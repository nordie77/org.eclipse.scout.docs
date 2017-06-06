package org.eclipse.scout.widgets.client.ui.desktop.pages;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link PagesNodePage}</h3>
 *
 * @author aho
 */
public class PagesNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Pages");
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new PageWithTableTablePage());
    pageList.add(new NodePageWithChildTableChild());
  }
}
