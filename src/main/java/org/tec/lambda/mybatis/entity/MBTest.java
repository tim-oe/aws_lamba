package org.tec.lambda.mybatis.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
public class MBTest {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String value;
}
