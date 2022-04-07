package pl.misern.taskm.controller;

import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.AssignTaskCommand;
import pl.misern.taskm.event.command.EntryActionType;
import pl.misern.taskm.event.command.ExitApplicationCommand;
import pl.misern.taskm.event.command.LoadFileStateCommand;
import pl.misern.taskm.event.command.NewFileStateCommand;
import pl.misern.taskm.event.command.SaveFileStateCommand;
import pl.misern.taskm.event.command.ShowEntryDialogCommand;
import pl.misern.taskm.event.command.ShowTaskListDialogCommand;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.gui.menu.MenuCategory;
import pl.misern.taskm.gui.screen.TaskScreen;
import pl.misern.taskm.gui.screen.TeamScreen;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskmController extends ApplicationController {

    @Override
    public void registerEventListeners() {
        Window window = Window.getInstance();
        addPersonEventListener(window);
        addTaskEventListener(window);

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(0).addActionListener(action ->
                EventHandler.pushEvent(new NewFileStateCommand()));

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(1).addActionListener(action ->
                EventHandler.pushEvent(new LoadFileStateCommand()));

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(2).addActionListener(action ->
                EventHandler.pushEvent(new SaveFileStateCommand()));

        window.getApplicationMenuBar().getMenus().get(MenuCategory.FILE).getItem(3).addActionListener(action ->
                EventHandler.pushEvent(new ExitApplicationCommand()));
    }

    private void addPersonEventListener(Window window) {
        TeamScreen teamScreen = window.getMainScreen().getTeamScreen();
        teamScreen.getAddPerson().addActionListener(action ->
                EventHandler.pushEvent(new ShowEntryDialogCommand(new Person(), EntryActionType.SAVE)));
        teamScreen.getUpdatePerson().addActionListener(action ->
                EventHandler.pushEvent(new ShowEntryDialogCommand(teamScreen.getActualPerson(), EntryActionType.EDIT)));
        teamScreen.getTeamList().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = teamScreen.getTeamList().getSelectedIndex();
                    EventHandler.pushEvent(new ShowTaskListDialogCommand(index));
                }
            }
        });
    }

    private void addTaskEventListener(Window window) {
        TaskScreen taskScreen = window.getMainScreen().getTaskScreen();
        TeamScreen teamScreen = window.getMainScreen().getTeamScreen();

        taskScreen.getAddTask().addActionListener(action ->
                EventHandler.pushEvent(new ShowEntryDialogCommand(new Task(), EntryActionType.SAVE)));
        taskScreen.getAssignTask().addActionListener(action ->
                EventHandler.pushEvent(new AssignTaskCommand(taskScreen.getActualTask(), teamScreen.getTeamList())));
    }
}
