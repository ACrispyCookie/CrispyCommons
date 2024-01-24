package dev.acrispycookie.crispycommons.implementations.visuals.tablist.implementation;

import dev.acrispycookie.crispycommons.implementations.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleStringElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.StringElement;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Supplier;

public class SimpleTablistBuilder {

    private final List<StringElement> header = new ArrayList<>();
    private final List<StringElement> footer = new ArrayList<>();
    private final Set<Player> receivers = new HashSet<>();
    private SimpleTablist builtTablist;

    public SimpleTablistBuilder(List<StringElement> initialHeader, List<StringElement> initialFooter) {
        this.header.addAll(initialHeader);
        this.footer.addAll(initialFooter);
    }

    public SimpleTablistBuilder() {

    }

    public SimpleTablistBuilder addHeaderLine(String text) {
        this.header.add(new SimpleStringElement(text));
        return this;
    }

    public SimpleTablistBuilder addAnimatedHeaderLine(Collection<? extends String> frames, int period) {
        this.header.add(new StringElement(frames, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addAnimatedHeaderLine(Supplier<String> supplier, int period) {
        this.header.add(new StringElement(supplier, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addFooterLine(String text) {
        this.footer.add(new SimpleStringElement(text));
        return this;
    }

    public SimpleTablistBuilder addAnimatedFooterLine(Collection<? extends String> frames, int period) {
        this.footer.add(new StringElement(frames, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addAnimatedFooterLine(Supplier<String> supplier, int period) {
        this.footer.add(new StringElement(supplier, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addPlayer(Player p) {
        this.receivers.add(p);
        return this;
    }

    public SimpleTablistBuilder addPlayers(Set<Player> players) {
        this.receivers.addAll(players);
        return this;
    }

    public SimpleTablistBuilder setPlayers(Set<Player> players) {
        this.receivers.clear();
        this.receivers.addAll(players);
        return this;
    }

    public SimpleTablistBuilder removePlayer(Player player) {
        this.receivers.remove(player);
        return this;
    }

    public CrispyTablist build() {
        ArrayList<List<StringElement>> tabList = new ArrayList<>();
        tabList.add(header);
        tabList.add(footer);
        builtTablist = new SimpleTablist(tabList, receivers);
        return builtTablist;
    }
}
