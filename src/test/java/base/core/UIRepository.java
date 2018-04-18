package base.core;

import base.ui.components.IUIComponent;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by @v.matviichenko
 */
public class UIRepository<T> {
    private static final Logger LOGGER = Logger.getLogger(UIRepository.class);

    private UIElement rootUIElement = null;
    private TreeNode<UIElement> root = new TreeNode<>(rootUIElement);
    public WebDriver driver;

    protected UIRepository(WebDriver driver) {
        this.driver = driver;
    }

    public TreeNode<UIElement> nodeByName(final String name) {
        Comparable<UIElement> searchCriteria = treeData -> {
            if (treeData == null)
                return 1;
            return treeData.getName().equals(name) ? 0 : 1;
        };

        if (root.findTreeNode(searchCriteria) == null) {
            throw new RuntimeException("Component with name " + name + " not found.");
        }

        return root.findTreeNode(searchCriteria);
    }

    public TreeNode<UIElement> addElement(final String parentName, final String name, final String path, final Class<? extends IUIComponent> component) {
        return addElement(nodeByName(parentName), name, path, component);
    }

    public TreeNode<UIElement> addElement(TreeNode node, final String name, final String path, final Class<? extends IUIComponent> component) {
        return addElement(node, name, path, component, false);
    }

    public TreeNode<UIElement> addElement(TreeNode node, final String name, final String path, final Class<? extends IUIComponent> component, boolean cached) {
        return node == null
                ? root.addChild(new UIElement(name, path, component, cached))
                : node.addChild(new UIElement(name, path, component, cached));
    }

//    public WebElement getWebElementByName(final String name) throws NoSuchElementException {
//        WebElement lastElement = null;
//        TreeNode<UIElement> node = nodeByName(name);
//        if (node == null)
//            throw new ElementNotFoundException(name, "", "");
//        Stack<UIElement> listElement = new Stack();
//        while (!node.isRoot()) { //get element from tree
//            listElement.add(node.data);
//            node = node.parent;
//        }
//        try {
//            while (!listElement.empty()) {
//                UIElement element = listElement.pop();
//                if (element.getElement() != null && element.isCached()) {
//                    lastElement = element.getElement();
//                    continue;
//                }
//
//                By locator = getComponent(element.getName()).getBy(element.getLocator());
//                if (lastElement == null) {
//                    if (driver.findElements(locator).size() < 1)
//                        throw new ElementNotFoundException(name, "", element.getLocator());
//                    else {
//                        lastElement = driver.findElements(locator).get(0);
//                        element.setElement(lastElement);
//                    }
//                } else {
//                    if (lastElement.findElements(locator).size() < 1) {
//                        throw new ElementNotFoundException(name, lastElement.toString(), element.getLocator());
//                    } else {
//                        lastElement = lastElement.findElements(locator).get(0);
//                        element.setElement(lastElement);
//                    }
//                }
//            }
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error("StaleElementReferenceException");
//            getWebElementByName(name);
//        }
//
//        return lastElement;
//    }

//    public WebElement waitForElement(final String name, final long timeOutInSeconds) {
//        LOGGER.info("waitForElement " + name);
//        final WebElement[] element = new WebElement[1];
//        Function<? super WebDriver, Object> isPresent = (ExpectedCondition<Object>) webDriver -> {
//            try {
//                element[0] = getWebElementByName(name);
//                return true;
//            } catch (NoSuchElementException | ElementNotFoundException e) {
//                return false;
//            }
//        };
//
//        new WebDriverWait(driver, timeOutInSeconds).until(isPresent);
//        return element[0];
//    }

//    public WebElement waitForElement(final String name) {
//        return waitForElement(name, 10);
//    }
//
//    public boolean isElementPresent(final String name) {
//        LOGGER.info("isElementPresent " + name);
//        try {
//            getWebElementByName(name);
//            return true;
//        } catch (Exception e) {
//            LOGGER.info("Element " + name + " is absent");
//            return false;
//        }
//    }

//    public WebElement waitForVisible(final String name) {
//        LOGGER.info("waitForVisible " + name);
//        return new WebDriverWait(driver, 30).until(ExpectedConditions
//                .visibilityOf(getWebElementByName(name)));
//    }

//    private IUIComponent getComponent(final String name) {
//        try {
//            return ComponentMultiton.getInstance(nodeByName(name).data.getComponent());
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public IUIComponent getWebComponent(final String name) {
//        LOGGER.info("getWebComponent : " + name);
//        long maxTimeWait = System.currentTimeMillis() + 30000;
//        while (true) {
//            try {
//                if (System.currentTimeMillis() > maxTimeWait) {
//                    throw new RuntimeException("Element " + name + " not found");
//                }
//                return getComponent(name).setWebElement(getWebElementByName(name));
//            } catch (ElementNotFoundException | InvalidElementStateException | StaleElementReferenceException e) {
//                // e.printStackTrace();
//            }
//        }
//    }
}
