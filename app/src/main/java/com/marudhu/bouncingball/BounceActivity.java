package com.marudhu.bouncingball;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;



public class BounceActivity extends ActionBarActivity {

    CanvasView canvasView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null || canvasView == null) {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            canvasView = new CanvasView(BounceActivity.this,size.x,size.y);
        }
        setContentView(canvasView);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        canvasView.saveState();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bounce, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_Restart) {
            canvasView.restart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
