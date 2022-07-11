package ru.dimas224.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Set;
import ru.dimas224.data.entity.Role;
import ru.dimas224.data.entity.User;

@PageTitle("Users")
@Route(value = "")
public class MainView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class, false);
    TextField filterText = new TextField();

    public MainView() {
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();

        grid.addColumn(User::getId).setHeader("ID").setSortable(true);
        grid.addColumn(User::getUsername).setHeader("Username").setSortable(true);
        grid.addColumn(User::getEmail).setHeader("Email").setSortable(true);
        grid.addColumn(User::getRole).setHeader("Role").setSortable(true);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getToolbar() {
        Dialog userDialog = new EditUserDialog(Set.of(Role.ADMIN, Role.USER));

        filterText.setPlaceholder("Поиск...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addUserBtn = new Button("Добавить", e -> userDialog.open());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addUserBtn, userDialog);
        toolbar.addClassName("toolbar");

        return toolbar;
    }


}
