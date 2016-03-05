package org.nanjing.knightingal.wificonnector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_su)
    Button btnSu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        btnSu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean retval = false;
                try {
                    Process localProcess = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(localProcess.getOutputStream());
                    // DataInputStream osRes = new DataInputStream(localProcess.getInputStream());

                    //os.writeBytes("id\n");
                    os.writeBytes("setprop service.adb.tcp.port 5555\n");
                    os.writeBytes("stop adbd\n");
                    os.writeBytes("start adbd\n");


                    os.flush();



                    // String currUid = osRes.readLine();
                    // boolean exitSu = false;
                    // if (currUid == null) {
                    //     retval = false;
                    //     exitSu = false;
                    //     Log.d("ROOT", "Cant get root access or denied by user");

                    // } else if (currUid.contains("uid=0")) {
                    //     retval = true;
                    //     exitSu = true;
                    //     Log.d("ROOT", "Root access granted");
                    // } else {
                    //     retval = false;
                    //     exitSu = true;
                    //     Log.d("ROOT", "Root access rejected" + currUid);
                    // }

                    // if (exitSu) {
                    //     os.writeBytes("exit\n");
                    //    os.flush();
                    // }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
