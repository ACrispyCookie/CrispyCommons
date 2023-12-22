package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.DynamicElement;

public abstract class DynamicTextElement extends TextElement {

    private final DynamicElement<String> dynamicElement;
    public abstract String getElement();

    public DynamicTextElement(String element) {
        super(element);
        this.dynamicElement = new DynamicElement<String>(element) {
            @Override
            public String getElement() {
                return DynamicTextElement.this.getElement();
            }
        };
    }

    public DynamicElement<String> getDynamicElement() {
        return dynamicElement;
    }
}
