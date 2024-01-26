package dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class NameTagData implements VisualData {

    private TextElement prefix;
    private TextElement suffix;
    private TextElement belowName;
    private TextElement aboveName;

    public NameTagData(TextElement prefix, TextElement suffix, TextElement belowName, TextElement aboveName) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.belowName = belowName;
        this.aboveName = aboveName;
    }

    public TextElement getPrefix() {
        return prefix;
    }

    public void setPrefix(TextElement prefix) {
        this.prefix = prefix;
    }

    public TextElement getSuffix() {
        return suffix;
    }

    public void setSuffix(TextElement suffix) {
        this.suffix = suffix;
    }

    public TextElement getBelowName() {
        return belowName;
    }

    public void setBelowName(TextElement belowName) {
        this.belowName = belowName;
    }

    public TextElement getAboveName() {
        return aboveName;
    }

    public void setAboveName(TextElement aboveName) {
        this.aboveName = aboveName;
    }
}
