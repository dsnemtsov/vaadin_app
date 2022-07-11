package ru.dimas224.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import ru.dimas224.util.Constants;
import ru.dimas224.util.Role;

public class EditUserDialog extends Dialog implements Constants {
    private final Button saveBtn = new Button(BTN_ADD);
    private final Button cancelBtn = new Button(BTN_CLOSE, e -> close());

    private final TextField id = new TextField(TABLE_ID);
    private final TextField username = new TextField(TABLE_USERNAME);
    private final TextField email = new TextField(TABLE_EMAIL);

    private final ComboBox<Role> boxRoles = new ComboBox<>(TABLE_ROLE);


    public EditUserDialog() {
        addClassName("edit-user-dialog");

        configRoles();
        configDialog();
    }

    private void configRoles() {
        Set<Role> roles = new HashSet<>(Arrays.asList(Role.values()));

        boxRoles.setItems(roles);
        boxRoles.setItemLabelGenerator(Enum::toString);
    }

    private void configDialog() {
        VerticalLayout userForm = new VerticalLayout(id, username, email, boxRoles);

        add(userForm);

        setHeaderTitle(HEADER_USER);

        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        getFooter().add(cancelBtn);
        getFooter().add(saveBtn);
    }
}
