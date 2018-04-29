package com.theskyegriffin.polarclocklibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.theskyegriffin.polarclocklibrary.Arcs.Arc;
import com.theskyegriffin.polarclocklibrary.Arcs.DaysArc;
import com.theskyegriffin.polarclocklibrary.Arcs.DaysOfWeekArc;
import com.theskyegriffin.polarclocklibrary.Arcs.HoursArc;
import com.theskyegriffin.polarclocklibrary.Arcs.MinutesArc;
import com.theskyegriffin.polarclocklibrary.Arcs.MonthsArc;
import com.theskyegriffin.polarclocklibrary.Arcs.SecondsArc;

import java.util.ArrayList;
import java.util.Calendar;

public class PolarClockView extends View {
    private int monthsArcColor;
    private int daysArcColor;
    private int daysOfWeekArcColor;
    private int hoursArcColor;
    private int minutesArcColor;
    private int secondsArcColor;
    private int radius;
    private ArrayList<Arc> arcs;

    public PolarClockView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        GetAttributeValues(context, attributeSet);
        InitializeDependencies();
    }

    private void GetAttributeValues(Context context, AttributeSet attributeSet) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PolarClockView, 0, 0);

        try {
            monthsArcColor = attributes.getInteger(R.styleable.PolarClockView_monthsArcColor, 0);
            daysArcColor = attributes.getInteger(R.styleable.PolarClockView_daysArcColor, 0);
            daysOfWeekArcColor = attributes.getInteger(R.styleable.PolarClockView_daysOfWeekArcColor, 0);
            hoursArcColor = attributes.getInteger(R.styleable.PolarClockView_hoursArcColor, 0);
            minutesArcColor = attributes.getInteger(R.styleable.PolarClockView_minutesArcColor, 0);
            secondsArcColor = attributes.getInteger(R.styleable.PolarClockView_secondsArcColor, 0);
            radius = attributes.getInteger(R.styleable.PolarClockView_radius, 0);
        }
        finally {
            attributes.recycle();
        }
    }

    private void InitializeDependencies() {
        arcs = new ArrayList<Arc>();
        arcs.add(new SecondsArc(this, radius, secondsArcColor));
        arcs.add(new MinutesArc(this, radius, minutesArcColor));
        arcs.add(new HoursArc(this, radius, hoursArcColor));
        arcs.add(new DaysOfWeekArc(this, radius, daysOfWeekArcColor));
        arcs.add(new DaysArc(this, radius, daysArcColor));
        arcs.add(new MonthsArc(this, radius, monthsArcColor));
    }

    public void updateCurrentTime(Calendar currentDateTime) {
        for (Arc arc : arcs) {
            arc.updateCurrentTime(currentDateTime);
        }
    }

    public void startAnimation() {
        for (Arc arc : arcs) {
            arc.startAnimation();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int heightMidpoint = this.getMeasuredHeight() / 2;
        int widthMidpoint = this.getMeasuredWidth() / 2;

        for (Arc arc : arcs) {
            arc.draw(canvas, heightMidpoint, widthMidpoint);
        }
    }
}
