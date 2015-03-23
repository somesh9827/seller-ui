package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.common.NetworkUtils;
import com.somworld.seller_ui.views.common.ApplicationProgressDialog;
import com.somworld.seller_ui.views.common.NoInternetConnectionActivity;

import java.lang.ref.WeakReference;

public class HomeActivity extends Activity {
    TextView message;
    private ApplicationProgressDialog progressDialog;

    private static class LocalListener implements View.OnClickListener {
      private HomeActivity mParent
      private LocalListener(HomeActivity parent) {
        mParent = parent;
      }

      @Override
      public void onClick(View view) {
        if(mParent != null) {
          switch (view.getId()) {
            case R.id.home_reload :
              mParent.checkNetworkConnection();
              break;
            default:break;
          }
        }
      }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      message = (TextView)findViewById(R.id.information_message);
      Button reload = (Button)findViewById(R.id.home_reload);

      WeakReference<HomeActivity> currentWeakRef = new WeakReference<HomeActivity>(this);
      progressDialog = new ApplicationProgressDialog(currentWeakRef.get());
      progressDialog.setHeading(getString(R.string.ApplicationProgressBarTitle)).setContent(getString(R.string.checkingInterneteConnection));

      LocalListener listener = new LocalListener(currentWeakRef.get());

      reload.setOnClickListener(listener);
      //checkNetworkConnection();
    }

  private void checkNetworkConnection(){
    if(!NetworkUtils.isWiFiOr3GAvailable(this)) {
      message.setText("No Internet connection available !!");
      message.setVisibility(View.VISIBLE);
      //progressDialog.show();
      //Intent NoNetworkConnectionDialog = new Intent(this, NoInternetConnectionActivity.class);
      //overridePendingTransition(R.anim.anim_bottom_in,R.anim.anim_bottom_out);
      //startActivity(NoNetworkConnectionDialog);
    }
    else{
     message.setVisibility(View.GONE);
     //progressDialog.dismiss();
      }
  }

  private void checkLoginDetail() {

  }

  @Override
  protected void onResume() {
    super.onResume();
    // checkNetworkConnection();
  }

  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
