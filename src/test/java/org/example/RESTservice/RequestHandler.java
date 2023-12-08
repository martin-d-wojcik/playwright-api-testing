package org.example.RESTservice;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.example.model.ActorModel;

public class RequestHandler {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext browserContext;
    private static APIRequestContext apiRequestContext;

    public RequestHandler() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    private static void initialiseContext() {
        browserContext = browser.newContext();
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        apiRequestContext = browserContext.request();
    }

    public APIResponse postRequest(String url, Object object) {
        initialiseContext();

        return apiRequestContext.post(url,
                RequestOptions.create()
                        .setData(object));
    }

    public APIResponse putRequestActor(String url, ActorModel actorModel) {
        initialiseContext();

        return apiRequestContext.put(url,
                RequestOptions.create()
                        .setData(actorModel));
    }

    public APIResponse deleteRequest(String url) {
        initialiseContext();

        return apiRequestContext.delete(url);
    }

    public APIResponse getRequest(String url) {
        initialiseContext();

        return apiRequestContext.get(url);
    }
}
