package org.eclipse.scout.widgets.client.ui.desktop.pages.bench;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.ui.desktop.bench.layout.BenchColumnData;
import org.eclipse.scout.rt.client.ui.desktop.bench.layout.BenchLayoutData;
import org.eclipse.scout.rt.client.ui.desktop.bench.layout.FlexboxLayoutData;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;

@ClassId("850d9375-2e4e-4735-8f6c-cbcab38bd16a")
public class FourPartsLayoutDataNodePage extends AbstractBenchLayoutPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return "Four Parts Layout";
  }

  @Override
  protected BenchLayoutData getConfiguredBenchLayoutData() {
    return new BenchLayoutData()
        .withWest(new BenchColumnData().withNorth(new FlexboxLayoutData().withRelative(false).withInitial(260)));
  }

  @Override
  protected void execCreatePageForms(List<IForm> formList) {

    // west column
    NorthWestForm nwf = new NorthWestForm();
//    nwf.setEnabledGranted(false);
    nwf.start();
    formList.add(nwf);
    SouthWestForm swf = new SouthWestForm();
//    swf.setEnabledGranted(false);
    swf.setTitle("Initial");
    swf.start();
    formList.add(swf);

    // east column
    NorthEastForm nef = new NorthEastForm();
//    nef.setEnabledGranted(false);
    nef.start();
    formList.add(nef);
    SouthEastForm sef = new SouthEastForm();
//    sef.setEnabledGranted(false);
    sef.start();
    formList.add(sef);

  }

  @ClassId("15cd2b6d-9d0d-4e31-ba32-665fd4ac09ef")
  private class NorthWestForm extends AbstractConfigureBenchLayoutForm<FlexboxLayoutData> {

    private int counter = 1;

    @Override
    protected String getConfiguredDisplayViewId() {
      return VIEW_ID_NW;
    }

    @Override
    protected FlexboxLayoutData getLayoutDataForUpdate() {
      return new FlexboxLayoutData();
    }

    @Override
    protected void storeLayoutData(FlexboxLayoutData layoutData) {
      BenchLayoutData benchLayoutData = getDesktop().getBenchLayoutData().copy();
      benchLayoutData.getWest().withNorth(layoutData);
      getDesktop().setBenchLayoutData(benchLayoutData);
    }

    @Override
    public void onButtonClick() {
      final SouthWestForm swf = new SouthWestForm();
      swf.setTitle("xx");
      swf.setClosable(true);
      swf.start();
      ModelJobs.schedule(new IRunnable() {

        @Override
        public void run() throws Exception {
          swf.setTitle("Dynamic" + counter++);
        }
      }, ModelJobs.newInput(ClientRunContexts.copyCurrent()).withExecutionTrigger(Jobs.newExecutionTrigger()
          .withStartIn(5, TimeUnit.SECONDS)));
    }

  }

  @ClassId("9159e2a7-3f05-433a-822c-6e5132b40ddb")
  private class SouthWestForm extends AbstractConfigureBenchLayoutForm<FlexboxLayoutData> {
    @Override
    protected String getConfiguredDisplayViewId() {
      return VIEW_ID_SW;
    }

    @Override
    protected FlexboxLayoutData getLayoutDataForUpdate() {
      return new FlexboxLayoutData();
    }

    @Override
    protected void storeLayoutData(FlexboxLayoutData layoutData) {
      BenchLayoutData benchLayoutData = getDesktop().getBenchLayoutData().copy();
      benchLayoutData.getWest().withSouth(layoutData);
      getDesktop().setBenchLayoutData(benchLayoutData);
    }
  }

  @ClassId("1d54d2eb-4848-4656-9904-ffe0d2b76d1f")
  private class NorthEastForm extends AbstractConfigureBenchLayoutForm<FlexboxLayoutData> {
    @Override
    protected String getConfiguredDisplayViewId() {
      return VIEW_ID_NE;
    }

    @Override
    protected FlexboxLayoutData getLayoutDataForUpdate() {
      return new FlexboxLayoutData();
    }

    @Override
    protected void storeLayoutData(FlexboxLayoutData layoutData) {
      BenchLayoutData benchLayoutData = getDesktop().getBenchLayoutData().copy();
      benchLayoutData.getEast().withNorth(layoutData);
      getDesktop().setBenchLayoutData(benchLayoutData);
    }

  }

  @ClassId("b058c75e-c9f0-4f20-8722-1a7056a42d9e")
  private class SouthEastForm extends AbstractConfigureBenchLayoutForm<FlexboxLayoutData> {
    @Override
    protected String getConfiguredDisplayViewId() {
      return VIEW_ID_SE;
    }

    @Override
    protected FlexboxLayoutData getLayoutDataForUpdate() {
      return new FlexboxLayoutData();
    }

    @Override
    protected void storeLayoutData(FlexboxLayoutData layoutData) {
      BenchLayoutData benchLayoutData = getDesktop().getBenchLayoutData().copy();
      benchLayoutData.getEast().withSouth(layoutData);
      getDesktop().setBenchLayoutData(benchLayoutData);
    }
  }

}
