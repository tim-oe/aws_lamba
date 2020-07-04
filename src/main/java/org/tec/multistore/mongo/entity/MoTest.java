package org.tec.multistore.mongo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id"})
@Document("multi")
@NoArgsConstructor
public class MoTest {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String value;
    private LocalDateTime createdOn;
}
