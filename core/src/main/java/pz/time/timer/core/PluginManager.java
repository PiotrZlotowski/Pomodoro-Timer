package pz.time.timer.core;

import pz.timer.api.core.Operation;
import pz.timer.api.plugin.IterationBreak;
import pz.timer.api.plugin.IterationEnd;
import pz.timer.api.plugin.IterationStart;
import pz.timer.api.plugin.TimerTick;

import java.util.*;

public class PluginManager {

    public Map<Class, Set<Operation>> retrievePlugins() {
        ServiceLoader<? extends Operation> pluginsLoader = ServiceLoader.load(Operation.class);

        Map<Class, Set<Operation>> plugins = new HashMap<>();

        plugins.put(TimerTick.class, new HashSet<>());
        plugins.put(IterationStart.class, new HashSet<>());
        plugins.put(IterationEnd.class, new HashSet<>());
        plugins.put(IterationBreak.class, new HashSet<>());

        for (Operation opLoader : pluginsLoader) {
            if (opLoader instanceof TimerTick) {
                plugins.get(TimerTick.class).add(opLoader);
            } else if (opLoader instanceof IterationStart) {
                plugins.get(IterationStart.class).add(opLoader);
            } else if (opLoader instanceof IterationEnd) {
                plugins.get(IterationEnd.class).add(opLoader);
            } else if (opLoader instanceof IterationBreak) {
                plugins.get(IterationBreak.class).add(opLoader);
            }
        }

        return plugins;
    }

}
