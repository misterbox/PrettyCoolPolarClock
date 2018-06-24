package com.theskyegriffin.prettycoolpolarclock.Arcs;

import java.util.ArrayList;

public class TestArcFactory implements IArcFactory {
    private ArrayList<Arc> arcs;

    public TestArcFactory(ArrayList<Arc> arcs) {
        this.arcs = arcs;
    }

    public ArrayList<Arc> buildArcs() {
        return arcs;
    }
}
