package ru.dimas224.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.dimas224.ui.EditUserDialog;
import ru.dimas224.util.Constants;
import ru.dimas224.data.entity.User;

@PageTitle("Users")
@Route(value = "")
public class MainView extends VerticalLayout implements Constants {
    private final Grid<User> grid = new Grid<>(User.class, false);
    private final TextField filterText = new TextField();
    private Dialog userDialog;

    public MainView() {
        addClassName("main-view");
        setSizeFull();

        configureGrid();
        configureDialog();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();

        grid.addColumn(User::getId).setHeader(TABLE_ID).setSortable(true);
        grid.addColumn(User::getUsername).setHeader(TABLE_USERNAME).setSortable(true);
        grid.addColumn(User::getEmail).setHeader(TABLE_EMAIL).setSortable(true);
        grid.addColumn(User::getRole).setHeader(TABLE_ROLE).setSortable(true);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureDialog() {
        userDialog = new EditUserDialog();
    }

    private Component getToolbar() {

        filterText.setPlaceholder(FILTER_PLACEHOLDER);
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addUserBtn = new Button(BTN_ADD, e -> userDialog.open());
        addUserBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button getExercisesBtn = new Button(BTN_EXERCISES, e -> getUI().ifPresent(ui -> ui.navigate(URL_EXERCISES)));
        getExercisesBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(
                filterText,
                addUserBtn,
                getExercisesBtn,
                userDialog);

        toolbar.addClassName("toolbar");

        return toolbar;
    }
}
