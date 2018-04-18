package base.core;

import base.ui.components.IUIComponent;
import org.openqa.selenium.WebElement;

/**
 * Created by @v.matviichenko
 */
public class UIElement<T> {
        private String name;
        private String locator;
        private Class<? extends IUIComponent> component;
        private boolean cached;
        private WebElement element;


        public UIElement(final String name, String locator, Class<? extends IUIComponent> component) {
            this.name = name;
            this.locator = locator;
            this.component = component;
        }

        public UIElement(final String name, String locator, Class<? extends IUIComponent> component, boolean cached) {
            this.name = name;
            this.locator = locator;
            this.component = component;
            this.cached = cached;
        }

        public boolean isCached() {
            return cached;
        }

        public void setCached(boolean cached) {
            this.cached = cached;
        }

        public UIElement get() {
            return this;
        }

        public String getName() {
            return this.name;
        }

        public String getLocator() {
            return this.locator;
        }

        public Class<? extends IUIComponent> getComponent() {
            return this.component;
        }

        public WebElement getElement() {
            return element;
        }

        public void setElement(WebElement element) {
            this.element = element;
        }
}
