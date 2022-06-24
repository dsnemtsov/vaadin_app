package ru.dimas224.data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;
}
