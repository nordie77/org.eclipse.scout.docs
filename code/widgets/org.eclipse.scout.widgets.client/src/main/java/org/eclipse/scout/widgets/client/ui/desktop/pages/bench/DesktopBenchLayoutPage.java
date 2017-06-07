package org.eclipse.scout.widgets.client.ui.desktop.pages.bench;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.widgets.client.ui.desktop.pages.IFormPage;
import org.eclipse.scout.widgets.client.ui.forms.IPageForm;

/**
 * <h3>{@link DesktopBenchLayoutPage}</h3>
 *
 * @author aho
 */
public class DesktopBenchLayoutPage extends AbstractPageWithNodes implements IFormPage {
  @Override
  protected String getConfiguredTitle() {
    return "Bench Layout";
  }

  @Override
  public String getText() {
    return getConfiguredTitle();
  }

  @Override
  public Class<? extends IPageForm> getFormType() {
    return null;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new FixedFooterNodePage());
    pageList.add(new FixedCenterColumnNodePage());
    pageList.add(new FixedTopNodePage());
    pageList.add(new ConfigurableColumnBenchLayoutNodePage());
    pageList.add(new ConfigurableRowBenchLayoutNodePage());
    pageList.add(new NullLayoutDataNodePage());
    pageList.add(new FourPartsLayoutDataNodePage());
    pageList.add(new CachedBenchLayoutNodePage());
  }

}
