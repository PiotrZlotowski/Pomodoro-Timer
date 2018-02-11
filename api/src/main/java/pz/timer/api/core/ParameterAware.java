package pz.timer.api.core;

public interface ParameterAware<T> {

    void handleCommandLineParameters(T argument);
}
