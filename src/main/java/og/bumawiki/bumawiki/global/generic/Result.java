package og.bumawiki.bumawiki.global.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private int length;
    private T data;
}
