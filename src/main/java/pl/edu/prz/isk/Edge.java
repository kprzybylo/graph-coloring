package pl.edu.prz.isk;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Edge {
    Integer source;
    Integer destination;
}
