package pz.timer.api.core;

public interface Operation<T> {

    T performOperation(Context context);
}
