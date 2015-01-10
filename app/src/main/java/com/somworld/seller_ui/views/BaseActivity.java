package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.somworld.seller_ui.R;

/**
 * Created by somesh.shrivastava on 10/01/15.
 */
public class BaseActivity extends Activity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_seller_profile) {
            Intent sellerProfileIntent = new Intent(this,sellerProfile.class);
            startActivity(sellerProfileIntent);
            return true;
        }
        else if (id == R.id.action_dash_board) {
            Intent sellerProfileIntent = new Intent(this,DashBoard.class);
            startActivity(sellerProfileIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return true;
    }

}
