package ru.dimas224.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.ResourcesArgs;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.Resource;
import java.io.IOException;
import java.util.List;
import ru.dimas224.data.entity.Exercise;
import ru.dimas224.util.Constants;
import ru.dimas224.util.ExerciseConverter;

@PageTitle("Упражнения")
@Route(value = "/exercises")
public class ExercisesView extends VerticalLayout implements Constants {
    private Grid<Exercise> grid = new Grid<>(Exercise.class, false);
    private TextField filterText = new TextField();

    private RestClient client = new RestClient(new Credentials("", ""));
    private List<Resource> externalExercises;
    private List<Exercise> internalExercises;
    private ExerciseConverter converter = new ExerciseConverter();

    public ExercisesView() {
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        add(getToolbar(),
                new H2("Список упражнений"),
                grid);
    }

    private void configureGrid() {
        grid.addClassName("exercise-grid");
        grid.setSizeFull();

        grid.addColumn(Exercise::getId).setHeader("№").setSortable(true);
        grid.addColumn(Exercise::getName).setHeader("Название").setSortable(true);
        grid.addComponentColumn(Exercise::getAudioPlayer).setHeader("Плеер").setSortable(true);
        grid.addColumn(Exercise::getSize).setHeader("Размер").setSortable(true);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        internalExercises = getExternalExercises();

        grid.setItems(internalExercises);
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Поиск...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button homeBtn = new Button("Домой", e -> getUI().ifPresent(ui -> ui.navigate("")));
        homeBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(filterText, homeBtn);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private List<Exercise> getExternalExercises() {
        Resource resource;
        try {
            resource = client.listPublicResources(new ResourcesArgs
                    .Builder()
                    .setLimit(1000)
                    .setPublicKey(EXERCISES_URL)
                    .build());
        } catch (IOException | ServerIOException e) {
            throw new RuntimeException(e);
        }

        externalExercises = resource
                .getResourceList()
                .getItems()
                .stream()
                .filter(i -> i
                        .getName()
                        .endsWith("m4a")).toList();

        return converter.convert(externalExercises, client);
    }
}
