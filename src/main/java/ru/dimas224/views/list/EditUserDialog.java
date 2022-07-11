package ru.dimas224.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import java.util.Set;
import ru.dimas224.data.entity.Role;

public class EditUserDialog extends Dialog{
    private final Button saveBtn = new Button("Добавить");
    private final Button cancelBtn = new Button("Закрыть", e -> close());
    private TextField id = new TextField("ID");
    private TextField username = new TextField("Имя пользователя");
    private TextField email = new TextField("Email");
    private final ComboBox<Role> roles = new ComboBox<>("Роль");


    public EditUserDialog(Set<Role> roles) {
        addClassName("edit-user-dialog");

        this.roles.setItems(roles);
        this.roles.setItemLabelGenerator(Enum::toString);

        add(id, username, email, this.roles);

        setHeaderTitle("Управление пользователем");
        getFooter().add(cancelBtn);
        getFooter().add(saveBtn);
    }
}
