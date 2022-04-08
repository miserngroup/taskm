package pl.misern.taskm.controller;

import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.ChangeLanguageCommand;
import pl.misern.taskm.event.command.ExitApplicationCommand;
import pl.misern.taskm.event.command.save.LoadFileStateCommand;
import pl.misern.taskm.event.command.save.NewFileStateCommand;
import pl.misern.taskm.event.command.save.SaveFileStateCommand;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.gui.menu.MenuCategory;

public class TaskmController extends ApplicationController {

    @Override
    public void registerEventListeners() {
        Window window = Window.getInstance();

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(0).addActionListener(action ->
                EventHandler.pushEvent(new NewFileStateCommand()));

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(1).addActionListener(action ->
                EventHandler.pushEvent(new LoadFileStateCommand()));

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(2).addActionListener(action ->
                EventHandler.pushEvent(new SaveFileStateCommand()));

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(3).addActionListener(action ->
                EventHandler.pushEvent(new ExitApplicationCommand()));
    }

    @Override
    public void changeViewLanguage() {
        EventHandler.pushEvent(new ChangeLanguageCommand(currentLocale));
    }
}
