/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.widgets.client.ui.desktop.pages;

import java.math.RoundingMode;
import java.sql.Date;
import java.util.Random;
import java.util.Set;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TreeMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractAlphanumericSortingStringColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.widgets.client.services.lookup.CompanyTypeLookupCall;
import org.eclipse.scout.widgets.client.ui.desktop.menu.AbstractViewSourceOnGitHubMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClassId("7720b10c-0ba3-40e8-9d91-4686d9066de8")
public class PageWithTableTablePage extends AbstractPageWithTable<PageWithTableTablePage.Table> {
  private static Logger LOG = LoggerFactory.getLogger(PageWithTableTablePage.class);

  protected final Random m_random = new Random();
  private Class<? extends IPage<?>> m_childPage;

  public PageWithTableTablePage() {
    super();
  }

  public PageWithTableTablePage(String name) {
    super();
    getCellForUpdate().setText(name);
  }

  public PageWithTableTablePage withChildPageClass(Class<? extends IPage<?>> childPage) {
    m_childPage = childPage;
    setLeaf(m_childPage == null);
    return this;
  }

  @Override
  protected IPage<?> execCreateChildPage(ITableRow row) {
    try {
      return m_childPage.newInstance();
    }
    catch (Exception e) {
      LOG.error("Could not instantiate child page", e);
    }
    return null;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PageWithTable");
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importTableData(loadDemoData());
  }

  protected Object[][] loadDemoData() {
    return new Object[][]{
        {"String 10", "String 11", m_random.nextLong(), m_random.nextInt(), 9768.3, new Date(System.currentTimeMillis()), false, 2, 1.0, 0.01, 1.0},
        {"String 11", "String 31", m_random.nextLong(), m_random.nextInt(), 8768.3, new Date(System.currentTimeMillis()), false, 2, 10.0, 0.10, 1.120},
        {"String 12", "String 0", m_random.nextLong(), m_random.nextInt(), 7768.3, new Date(System.currentTimeMillis()), false, 2, 100.0, 1.0, 1.123},
        {"String 13", "String 1", m_random.nextLong(), m_random.nextInt(), 5768.3, new Date(System.currentTimeMillis()), false, 2, 0.1, 0.001, 1.150},
        {"String 22", "String 1000", m_random.nextLong(), m_random.nextInt(), 13000.25, new Date(System.currentTimeMillis() + 86400000), true, 1, -1.0, -0.01, 1.151},
        {"String 23", "String .txt", m_random.nextLong(), m_random.nextInt(), 12000.25, new Date(System.currentTimeMillis() + 46400000), true, 1, -10.0, -0.1, 1.5},
        {"String 24", "String 1 mit 10", m_random.nextLong(), m_random.nextInt(), 11000.25, new Date(System.currentTimeMillis() + 56400000), true, 1, -100.0, -1.0, 1.51},
        {"String 25", "String 1 mit 3", m_random.nextLong(), m_random.nextInt(), 10000.25, new Date(System.currentTimeMillis() + 76400000), true, 1, -0.1, -0.001, 1.511},
        {"String 31", "String 1 mit 1020", m_random.nextLong(), m_random.nextInt(), 8131.7, new Date(System.currentTimeMillis() - 216000000), true, 3, 1.2, 0.012, 1.0},
        {"String 32", "String 1.txt", m_random.nextLong(), m_random.nextInt(), 8231.7, new Date(System.currentTimeMillis() - 216000000), true, 3, 1.02, 0.0102, -1.0},
        {"String 33", "String 2.txt", m_random.nextLong(), m_random.nextInt(), 8331.7, new Date(System.currentTimeMillis() - 216000000), true, 3, 1.002, 0.01002, -1.120},
        {"String 34", "String 19.txt", m_random.nextLong(), m_random.nextInt(), 8431.7, new Date(System.currentTimeMillis() - 216000000), true, 3, -1.2, -0.012, -1.123},
        {"String 35", "String 200.txt", m_random.nextLong(), m_random.nextInt(), 8531.7, new Date(System.currentTimeMillis() - 216000000), true, 3, -1.02, -0.0102, -1.150},
        {"String 36", "String 1", m_random.nextLong(), m_random.nextInt(), 8631.7, new Date(System.currentTimeMillis() - 216000000), true, 3, -1.002, -0.01002, -1.151},
        {"String 37", "String 2", m_random.nextLong(), m_random.nextInt(), 0.005, new Date(System.currentTimeMillis() - 216000000), true, 3, 1.0, 0.01, -1.5},
        {"String 38", "String 19 ", m_random.nextLong(), m_random.nextInt(), 0.006, new Date(System.currentTimeMillis() - 216000000), true, 3, 1.0, 0.01, -1.51},
        {"String 39", "String 200", m_random.nextLong(), m_random.nextInt(), 0.006, new Date(System.currentTimeMillis() - 216000000), true, 3, 1.0, 0.01, -1.511},
    };
  }

  public class Table extends AbstractTable {

    public SmartColumn getSmartColumn() {
      return getColumnSet().getColumnByClass(SmartColumn.class);
    }

    public BooleanColumn getBooleanColumn() {
      return getColumnSet().getColumnByClass(BooleanColumn.class);
    }

    public DateColumn getDateColumn() {
      return getColumnSet().getColumnByClass(DateColumn.class);
    }

    public BigDecimalColumn getBigDecimalColumn() {
      return getColumnSet().getColumnByClass(BigDecimalColumn.class);
    }

    public IntegerColumn getIntegerColumn() {
      return getColumnSet().getColumnByClass(IntegerColumn.class);
    }

    public LongColumn getLongColumn() {
      return getColumnSet().getColumnByClass(LongColumn.class);
    }

    public StringColumn getStringColumn() {
      return getColumnSet().getColumnByClass(StringColumn.class);
    }

    public AlphanumericSortingStringColumn getAlphanumericSortingStringColumn() {
      return getColumnSet().getColumnByClass(AlphanumericSortingStringColumn.class);
    }

    public PercentColumn getPercentColumn() {
      return getColumnSet().getColumnByClass(PercentColumn.class);
    }

    public MultiplierColumn getMultiplierColumn() {
      return getColumnSet().getColumnByClass(MultiplierColumn.class);
    }

    public RoundingModeColumn getRoundingModeColumn() {
      return getColumnSet().getColumnByClass(RoundingModeColumn.class);
    }

    @Order(10)
    public class StringColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("StringColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 120;
      }
    }

    @Order(15)
    public class AlphanumericSortingStringColumn extends AbstractAlphanumericSortingStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("StringColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 120;
      }
    }

    @Order(20)
    public class LongColumn extends AbstractLongColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("LongColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(30)
    public class IntegerColumn extends AbstractIntegerColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("IntegerColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 130;
      }
    }

    @Order(40)
    public class BigDecimalColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("BigDecimalColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }
    }

    @Order(50)
    public class DateColumn extends AbstractDateColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("DateColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(60)
    public class BooleanColumn extends AbstractBooleanColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("BooleanColumn");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(70)
    public class SmartColumn extends AbstractSmartColumn<Long> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("SmartColumn");
      }

      @Override
      protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
        return CompanyTypeLookupCall.class;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(80)
    public class PercentColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("PercentColumn");
      }

      @Override
      protected boolean getConfiguredPercent() {
        return true;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(90)
    public class MultiplierColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("MultiplierColumn");
      }

      @Override
      protected int getConfiguredMultiplier() {
        return -100;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(100)
    public class RoundingModeColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("RoundingModeColumn");
      }

      @Override
      protected RoundingMode getConfiguredRoundingMode() {
        return RoundingMode.CEILING;
      }

      @Override
      protected int getConfiguredWidth() {
        return 80;
      }
    }

    @Order(1000)
    @ClassId("7a34d641-e8b0-49b0-8c88-7c6e84bf1239")
    public class TableEmptyMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return "Table Empty";
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.hashSet(TableMenuType.EmptySpace);
      }

      @Override
      protected void execAction() {
        MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
      }
    }

    @Order(2000)
    @ClassId("e41efafa-e433-429f-a310-cd2630756680")
    public class TableSingleMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return "Table Single";
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.hashSet(TableMenuType.SingleSelection);
      }

      @Override
      protected void execAction() {
        MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
      }
    }

    @Order(3000)
    @ClassId("e704008e-548e-41a7-8bf0-f92970740afe")
    public class TableMultiMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return "Table multi";
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.hashSet(TableMenuType.MultiSelection);
      }

      @Override
      protected void execAction() {
        MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
      }
    }

    @Order(4000)
    @ClassId("3d337674-9cbe-4f62-8fd9-d8232066ccbe")
    public class TableHierachicalMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return "Table hierarchical";
      }

      @Order(1000)
      @ClassId("05953c27-1129-4fd9-9df6-7cd97786e7d0")
      public class TableHierarchicalEmptyMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Table hierarchical Emtpy";
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.EmptySpace);
        }

        @Override
        protected void execAction() {
          MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
        }
      }

      @Order(2000)
      @ClassId("3014e65c-38de-4d51-b383-ea2f0765180a")
      public class TableHierarchicalSingleMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Table hierarchical single";
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.SingleSelection);
        }

        @Override
        protected void execAction() {
          MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
        }
      }

      @Order(3000)
      @ClassId("b2c1ed43-4e7a-4b98-baa7-27eef7b3c1d1")
      public class TableHierarchicalMutliMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return "Table hierarchical Multi";
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.hashSet(TableMenuType.MultiSelection);
        }

        @Override
        protected void execAction() {
          MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
        }
      }

    }

  }

  @Order(-1000)
  @ClassId("e8b2f53a-87d8-4f53-af85-474c2ca6df3e")
  public class PageMenuSingle extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return "Page Single";
    }

    @Override
    protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      return CollectionUtility.hashSet(TreeMenuType.SingleSelection);
    }

    @Override
    protected void execAction() {
      MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
    }
  }

  @Order(-9000)
  @ClassId("7f692055-dabc-4077-bc48-2b61a53e9091")
  public class PageMenuMulti extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return "Page Multi";
    }

    @Override
    protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      return CollectionUtility.hashSet(TreeMenuType.MultiSelection);
    }

    @Override
    protected void execAction() {
      MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
    }
  }

  @Order(-9000)
  @ClassId("dc90d63a-77c6-4ae3-add8-5ac566accf22")
  public class PageMenuEmpty extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return "Page Empty";
    }

    @Override
    protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      return CollectionUtility.hashSet(TreeMenuType.EmptySpace);
    }

    @Override
    protected void execAction() {
      MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
    }
  }

  @Order(10)
  public class HierarchicalPageMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return "Hierarchical Page Menu";
    }

    @Order(10)
    public class SubSingleMenu extends AbstractMenu {
      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(
            TreeMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return "TreeSubSingle";
      }

      @Override
      protected void execAction() {
        MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
      }

    }

    @Order(30)
    public class SubEmptySpaceMenu extends AbstractMenu {
      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(
            TreeMenuType.EmptySpace);
      }

      @Override
      protected String getConfiguredText() {
        return "TreeSubEmpty";
      }

      @Override
      protected void execAction() {
        MessageBoxes.createOk().withHeader("Menu action").withBody(getConfiguredText() + " action.").show();
      }
    }
  }

  @Order(10000)
  public class ViewSourceOnGitHubMenu extends AbstractViewSourceOnGitHubMenu {

    @Override
    protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      return CollectionUtility.<IMenuType> hashSet(
          TreeMenuType.EmptySpace,
          TableMenuType.SingleSelection,
          TableMenuType.MultiSelection,
          TableMenuType.EmptySpace);
    }

    @Override
    protected Class<?> provideSourceClass() {
      return PageWithTableTablePage.class;
    }
  }
}
