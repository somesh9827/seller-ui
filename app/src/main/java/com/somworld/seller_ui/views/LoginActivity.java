package com.somworld.seller_ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.dataService.DataServiceErrorResponse;
import com.somworld.seller_ui.dataService.DataServiceSuccessResponse;
import com.somworld.seller_ui.dataService.IDataServiceCallback;
import com.somworld.seller_ui.dataService.SellerDataManager;
import com.somworld.seller_ui.models.LoginDetail;
import com.somworld.seller_ui.models.dtos.LoginDetailDTO;

import java.lang.ref.WeakReference;

public class LoginActivity extends Activity implements IDataServiceCallback {


  private Button loginButton,cancelButton;
  private EditText usernameEditText,passwordEditText;

  private static class LocalClickListener implements View.OnClickListener{
    private LoginActivity mParent;

    LocalClickListener(LoginActivity parent){
      mParent = parent;
    }

    @Override
    public void onClick(View view) {
      if(mParent != null) {
        switch (view.getId()) {
          case R.id.login_button:
            mParent.onLoginButtonClicked();
            break;
          case R.id.cancel_button:
            mParent.onCancelButtonClicked();
            break;
          default:break;
        }
      }
    }
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      usernameEditText = (EditText)findViewById(R.id.user_name);
      passwordEditText = (EditText)findViewById(R.id.password);

      loginButton = (Button)findViewById(R.id.login_button);
      cancelButton = (Button)findViewById(R.id.cancel_button);

      WeakReference<LoginActivity> currentWeakReference = new WeakReference<LoginActivity>(this);
      LocalClickListener localClickListener = new LocalClickListener(currentWeakReference.get());

      loginButton.setOnClickListener(localClickListener);
      cancelButton.setOnClickListener(localClickListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    moveToParentActivity();

  }

  private void onLoginButtonClicked(){
    LoginDetailDTO loginDetailDTO = new LoginDetailDTO();
    loginDetailDTO.setUserName(usernameEditText.getText().toString());
    loginDetailDTO.setPassword(passwordEditText.getText().toString());
    WeakReference<LoginActivity> currentObjectWeakReference = new WeakReference<LoginActivity>(this);
    SellerDataManager sellerDataManager = new SellerDataManager(currentObjectWeakReference.get());
    sellerDataManager.login(loginDetailDTO);
  }

  private void onCancelButtonClicked(){
    Intent showDashBoard = new Intent(this,LoginOrRegisterSelectionActivity.class);
    startActivity(showDashBoard);
    overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    Toast.makeText(this,"cancelButtonClicked",Toast.LENGTH_LONG);
    finish();
  }

  private void moveToParentActivity(){
    Intent showDashBoard = new Intent(this,LoginOrRegisterSelectionActivity.class);
    startActivity(showDashBoard);
    overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
    finish();
  }

  @Override
  public void onDataServiceSuccess(DataServiceSuccessResponse dataServiceSuccessResponse) {
    Intent showDashBoard = new Intent(this,DashBoard.class);
    startActivity(showDashBoard);
  }

  @Override
  public void onDataServiceFail(DataServiceErrorResponse dataServiceErrorResponse) {
    Toast.makeText(this,dataServiceErrorResponse.getMessage(),Toast.LENGTH_LONG);
  }
}
