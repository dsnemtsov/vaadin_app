package ru.dimas224.util;

import com.yandex.disk.rest.json.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import ru.dimas224.data.entity.Exercise;

public class ExerciseConverter {

    public static List<Exercise> convert(List<Resource> resources) {
        List<Exercise> result = new LinkedList<>();

        AtomicReference<Integer> i = new AtomicReference<>(1);

        resources.forEach(r -> {
            Exercise e = Exercise
                    .builder()
                    .id(i.getAndSet(i.get() + 1))
                    .name(r.getName())
                    .mediaType(r.getMediaType())
                    .size(r.getSize())
                    .build();

            result.add(e);
        });

        return result;
    }
}
