package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.menu.InvalidMenuConfiguration;
import dev.acrispycookie.crispycommons.utility.menu.PageInfo;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

public class PagedMenuData extends MenuData {

    private final PageInfo pageInfo;
    private final DynamicSection section;

    public PagedMenuData(DynamicSection section, PageInfo pageInfo, Collection<CrispyMenu.MenuProperty> properties) throws InvalidMenuConfiguration {
        super(pageInfo.getDefaultPage(), new ArrayList<>(), properties);
        if (pageInfo.getColumns() != 9 && pageInfo.getColumns() != 3) {
            throw new InvalidMenuConfiguration("Invalid number of columns in page!");
        }
        this.pageInfo = pageInfo;
        this.section = section;
    }

    public DynamicSection getDynamicSection() {
        return section;
    }

    public BiFunction<Integer, Integer, String> getTitle() {
        return pageInfo.getPageTitle();
    }

    public int getColumns() {
        return pageInfo.getColumns();
    }

    public int getRows() {
        return pageInfo.getRows();
    }

    public int getStartIndex() {
        return pageInfo.getStartIndex();
    }

    public int getEndIndex() {
        return pageInfo.getEndIndex();
    }

    public BiFunction<Player, CrispyItemStack, Boolean> getOnPlayerItemClick() {
        return pageInfo.getOnPlayerItemClick();
    }

    public void addPage(MenuPage page) {
        this.pages.add(page);
    }

    @Override
    public void assertReady() {
        if (getTitle() == null) {
            throw new GuiNotReadyException("The title wasn't set for the page menu.");
        }
        if (getStartIndex() == -1) {
            throw new GuiNotReadyException("The start index for the dynamic section on the page menu was not set.");
        }
        if (getEndIndex() == -1) {
            throw new GuiNotReadyException("The end index for the dynamic section on the page menu was not set.");
        }
        if (getDynamicSection() == null) {
            throw new GuiNotReadyException("The dynamic section on the page menu was not set.");
        }
        super.assertReady();
    }
}
