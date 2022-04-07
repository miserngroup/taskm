package pl.misern.taskm.controller;


import pl.misern.taskm.event.EventHandler;

public abstract class ApplicationController {

    protected final EventHandler eventHandler = new EventHandler();

    protected ApplicationController() {
        registerEventListeners();
        eventHandler.registerQueueExecutor();
    }

    public abstract void registerEventListeners();
}
