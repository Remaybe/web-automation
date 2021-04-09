package browser.factory;

public class BrowserFactory {
    public Browser createBrowser(BrowserType type) {
        Browser browser = null;

        switch (type) {
            case CHROME:
                browser =  new ChromeBrowser();
                break;
            case FIREFOX:
                browser = new FirefoxBrowser();
                break;
        }

        return browser;
    }
}
