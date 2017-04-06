package com.example.batteryfuelgauge;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private Button mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mButton = (Button) findViewById(R.id.button);
    mButton.setOnClickListener(this);
  }

  @Override
  public void onClick(final View v) {
    v.setEnabled(false);
    final ProgressDialog dialog =
        ProgressDialog.show(this, "", "Loading. Wait 3 seconds ...", true);

    v.postDelayed(new Runnable() {
      @Override
      public void run() {
        dialog.dismiss();
        v.setEnabled(true);
      }
    }, 3000);
  }
}
