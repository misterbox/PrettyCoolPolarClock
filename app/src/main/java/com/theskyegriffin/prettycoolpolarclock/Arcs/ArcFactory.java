package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Color;

import java.util.ArrayList;

public class ArcFactory implements IArcFactory {
    @Override
    public ArrayList<Arc> buildArcs() {
        ArrayList<Arc> arcs = new ArrayList<Arc>();
        arcs.add(new SecondsArc(450, Color.parseColor("#D26767")));

        return arcs;
    }
}
