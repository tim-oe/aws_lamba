package org.tec.lambda.mariadb.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
@Entity
@Table(name = "my_test")
public class MyTest {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", updatable=false, nullable=false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="name", updatable=false, nullable=false)
    private String name;

    @Column(name="value", nullable=false)
    private String value;
}
