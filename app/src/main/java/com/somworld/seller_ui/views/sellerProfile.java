package com.somworld.seller_ui.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.somworld.seller_ui.R;

public class sellerProfile extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_seller_profile);

    setValue(R.id.shop_name, R.string.shop_name);
    setValue(R.id.owner_name, R.string.owner_name);
    setValue(R.id.working_days, R.string.working_days);
    setValue(R.id.working_hours, R.string.working_hours);
    setValue(R.id.shop_phone_number, R.string.shop_phone_number);
    setValue(R.id.shop_address, R.string.shop_address);
  }

  private void setValue(int id, int stringId) {
    TextView textElement = (TextView) findViewById(id);
    textElement.setText(getString(stringId));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_seller_profile) {
      return false;
    }
    return super.onOptionsItemSelected(item);
  }

}
