package com.example.dell.customviewpractice;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by DELL on 10/11/2017.
 */

public class ValueSelector extends LinearLayout {
    private TextView noOfQuantityTV;
    private ImageView decrementIV,incrementIV;
    private LinearLayout root;
    private int quantity=0;
    private int maxValue;
    private int minValue;
    private boolean isIncreaseValuePressed = false;
    private boolean isDecreaseValuePressed = false;
    private Handler handler= new Handler();

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public ValueSelector(Context context) {
        super(context);
    }

    public ValueSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
    root = (LinearLayout) inflate(context,R.layout.customlayout,this);
        noOfQuantityTV = (TextView) root.findViewById(R.id.no_of_quantity);
        incrementIV= (ImageView) root.findViewById(R.id.increment_icon);
        decrementIV= (ImageView) root.findViewById(R.id.decrement_icon);

        incrementIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*value quantity defined in class level as variable*/
                incrementQuantity();

            }
        });
        incrementIV.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isIncreaseValuePressed = true;
                handler.post(new AutoIncrement());
                return false;
            }
        });
        incrementIV.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    isIncreaseValuePressed = false;
                }
                return false;
            }
        });

        /************************Decrement*************************/
        decrementIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*value quantity defined in class level as variable*/
                decrementQuantity();
            }
        });
        decrementIV.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isDecreaseValuePressed = true;
                handler.post(new AutoDecrement());
                return false;
            }
        });
        decrementIV.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    isDecreaseValuePressed=false;
                }
                return false;
            }
        });
    }

    private void decrementQuantity() {
        if (quantity>minValue){
            quantity--;
            noOfQuantityTV.setText(String.valueOf(quantity));
        }

    }

    private void incrementQuantity() {
        if (quantity<maxValue)
            quantity++;
        noOfQuantityTV.setText(String.valueOf(quantity));
    }

    private class AutoIncrement implements Runnable{

        @Override
        public void run() {
            if (isIncreaseValuePressed){
                incrementQuantity();
            }
            handler.postDelayed(this,100);
        }
    }
    private class AutoDecrement implements Runnable{

        @Override
        public void run() {
            if (isDecreaseValuePressed){

                decrementQuantity();
            }
            handler.postDelayed(this,100);
        }
    }


}
