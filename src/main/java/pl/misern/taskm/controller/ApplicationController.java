package pl.misern.taskm.controller;


import pl.misern.taskm.event.EventHandler;

import java.util.Locale;

public abstract class ApplicationController {

    protected final EventHandler eventHandler = new EventHandler();
    protected Locale currentLocale = Locale.getDefault();

    protected ApplicationController() {
        registerEventListeners();
        eventHandler.registerQueueExecutor();
        changeViewLanguage();
    }

    public abstract void registerEventListeners();

    public abstract void changeViewLanguage();
}
