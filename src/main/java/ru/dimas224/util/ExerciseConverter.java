package ru.dimas224.util;

import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.json.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.dimas224.data.entity.Exercise;
import ru.dimas224.ui.AudioPlayer;

public class ExerciseConverter {

    public List<Exercise> convert(List<Resource> resources, RestClient client) {
        List<Exercise> result = new LinkedList<>();

        AtomicReference<Integer> i = new AtomicReference<>(1);

        resources.forEach(r -> {
            Exercise e = Exercise
                    .builder()
                    .id(i.getAndSet(i.get() + 1))
                    .name(r.getName())
                    .audioPlayer(setAudioPlayer(r, client))
                    .size(r.getSize())
                    .build();

            result.add(e);
        });

        return result;
    }

    private AudioPlayer setAudioPlayer(Resource r, RestClient client) {
        Set<String> downloadedFiles;

        try {
            downloadedFiles = getDownloadedExercises("src/main/resources/META-INF/resources/audio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!downloadedFiles.contains(r.getName())) {
            try {
                client.downloadPublicResource(
                        r.getPublicKey(),
                        r.getPath().getPath(),
                        new File("src/main/resources/META-INF/resources/audio/" + r.getName()),
                        null);
            } catch (IOException | ServerException e) {
                throw new RuntimeException(e);
            }
        }


        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.setSource("audio/" + r.getName());

        return audioPlayer;
    }

    private Set<String> getDownloadedExercises(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }
}
