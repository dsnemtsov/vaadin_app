package ru.dimas224.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dimas224.ui.AudioPlayer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercise {

    private Integer id;
    private String name;
    private AudioPlayer audioPlayer;
    private long size;
}
