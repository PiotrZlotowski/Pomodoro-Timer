package pz.timer.api.core;

import pz.timer.api.core.Panel;

// TODO: Definicja dwóch kontekstów, dodanie dla każdego osobnego interfejsu, przeniesienie ich do pakietu kontekst
public abstract class Context {

    private Panel panel;

    public Context(Panel panel) {
        this.panel = panel;
    }

    public Panel getPanel() {
        return panel;
    }
}
