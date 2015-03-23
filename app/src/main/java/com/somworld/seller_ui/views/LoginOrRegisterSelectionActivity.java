package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.somworld.seller_ui.R;

import java.lang.ref.WeakReference;

public class LoginOrRegisterSelectionActivity extends Activity {
    private Button loginButton,registerButton = null;

    private static class LocalClickListener implements View.OnClickListener {
      LoginOrRegisterSelectionActivity mParent;
      LocalClickListener(LoginOrRegisterSelectionActivity parent) {
        mParent = parent;
      }

      @Override
      public void onClick(View view) {
        if(mParent != null) {
          switch (view.getId()) {
            case  R.id.login_button :
              mParent.onLoginButtonClicked();
              break;
            case R.id.register_button :
              mParent.onRegisterButtonClicked();;
              break;
            default:break;
          }
        }
      }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register_selection);
      loginButton = (Button)findViewById(R.id.login_button);
      registerButton = (Button)findViewById(R.id.register_button);

      WeakReference<LoginOrRegisterSelectionActivity> currentWeakReference = new WeakReference<LoginOrRegisterSelectionActivity>(this);
      LocalClickListener localClickListener = new LocalClickListener(currentWeakReference.get());

      loginButton.setOnClickListener(localClickListener);
      registerButton.setOnClickListener(localClickListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_or_register_selection, menu);
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

   private void onLoginButtonClicked(){
     Intent showLoginPage = new Intent(this,LoginActivity.class);
     startActivity(showLoginPage);
     finish();
   }

   private void onRegisterButtonClicked(){
     Intent showRegisterPagePage = new Intent(this,RegisterActivity1.class);
     startActivity(showRegisterPagePage);
     finish();
   }
}
