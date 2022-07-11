package ru.dimas224.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dimas224.ui.AudioPlayer;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private AudioPlayer audioPlayer;
    private long size;
}
