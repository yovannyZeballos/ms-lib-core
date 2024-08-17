package pe.com.yzm.core.model;

import lombok.*;

/**
 * Wrapper is a generic model class that wraps any type of data.
 * It includes a field for data of type T.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Wrapper<T> {

    /**
     * The data of type T.
     */
    private T data;
}