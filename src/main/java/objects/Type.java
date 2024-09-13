package objects;

import org.openqa.selenium.By;

public enum Type {
    ID {
        @Override
        public By buildBy(String locator) {
            return By.id(locator);
        }
    },
    NAME {
        @Override
        public By buildBy(String locator) {
            return By.name(locator);
        }
    },
    XPATH {
        @Override
        public By buildBy(String locator) {
            return By.xpath(locator);
        }
    },
    CSS {
        @Override
        public By buildBy(String locator) {
            return By.cssSelector(locator);
        }
    },
    CLASS_NAME {
        @Override
        public By buildBy(String locator) {
            return By.className(locator);
        }
    },
    TAG_NAME {
        @Override
        public By buildBy(String locator) {
            return By.tagName(locator);
        }
    },
    LINK_TEXT {
        @Override
        public By buildBy(String locator) {
            return By.linkText(locator);
        }
    },
    PARTIAL_LINK_TEXT {
        @Override
        public By buildBy(String locator) {
            return By.partialLinkText(locator);
        }
    };

    public abstract By buildBy(String locator);

}
