package com.marudhu.bouncingball;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by marudhu on 20/8/14.
 */
public class CanvasView extends View{

    Paint paint;
    Canvas canvas;
    int width;
    int height;

    int radius;
    int currentX,currentY;
    int ballColor ;
    Random rand;

    Boolean canRun;

    int xDirection,yDirection;
    AnimatorSet set;

    public CanvasView(Context context,int width,int height) {
        super(context);
        paint =new Paint();
        this.width = width;
        this.height =height;

        this.currentX = 1;
        this.currentY = 1;

        xDirection = -1;
        yDirection = -1;

        this.radius = 30;
        this.canRun = true;

        this.ballColor = Color.RED;
        rand = new Random();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#6e7ef4"));
        canvas.drawPaint(paint);


        paint.setColor(this.ballColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(currentX, currentY, radius, paint);

        if (currentX >= getWidth() || currentX <= 0){
            xDirection = -xDirection;
        }

        if (currentY >= getHeight() || currentY <=0) {
            yDirection = -yDirection;
        }

        currentX += randInt(10,30) * xDirection;
        currentY += randInt(10,30) * yDirection;

        if(canRun) {
            post(r);
            if(set != null && set.isRunning()) set.cancel();
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if ( canRun && //if the ball is moving
                ( (currentX - radius ) <= event.getX()  && //and with in boundaries
                ( currentX + radius ) >= event.getX() ) &&
                ( (currentY - radius) <=event.getY())   &&
                  (currentY + radius) >=event.getY() ) {

                canRun = false; //set running to false
                Toast.makeText(getContext(),"Hey you caught the ball",Toast.LENGTH_SHORT).show(); //show you won

            View restartButton = getRootView().findViewById(R.id.action_Restart);

            set = (AnimatorSet)AnimatorInflater.loadAnimator(getContext(), R.animator.blink_background);
            set.setTarget(restartButton);
            set.start();


        }

        return false;
    }

    public void restart(){
        canRun = true;
        invalidate();
    }



    public int randInt(int min, int max) {

        return 10;
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.


        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

//        int randomNum = rand.nextInt((max - min) + 1) + min;
//
//        return randomNum;
    }

    
}
