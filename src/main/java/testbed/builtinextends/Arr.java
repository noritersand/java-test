package testbed.builtinextends;

import java.util.ArrayList;

/**
 * TODO ArrayList를 확장한 뭔가를 만들다 말았음
 *
 * @param <T>
 */
public class Arr<T> extends ArrayList<T> {
    /**
     * Appends the specified element to the end of this list.
     *
     * @param t element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(T t) {
        return super.add(t);
    }
}
