package com.dudeofawesome.flyerboard;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;


public class PostActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

    }

    private void backToMain () {
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
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

    public void uploadFlyer(View button) {
        // Do click handling here for the Upload Flyer Button

        /* Warning: Right now, the user must input the exact
         * path + flyerName + extension to retrieve the file.
         */
        final EditText flyerNameField = (EditText) findViewById(R.id.EditTextFlyerName);
        String flyerName = flyerNameField.getText().toString();

        final EditText userNameField = (EditText) findViewById(R.id.EditTextUserName);
        String userName = userNameField.getText().toString();

        final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);
        String email = emailField.getText().toString();

        final EditText bodyField = (EditText) findViewById(R.id.EditTextUploadFlyerBody);
        String flyerDetails = bodyField.getText().toString();

        // Retrieve flyer file from local memory
        String path = Environment.getExternalStorageDirectory().toString() + flyerName;
        Log.d("File", "Path: " + path);
        File file = new File(path);

        // Send flyer + flyer info to BACKEND DATABASE
        addFlyerToDatabase(file, flyerName, userName, email, flyerDetails);
    }

    public void addFlyerToDatabase(File file,
                                   String flyerName,
                                   String userName,
                                   String email,
                                   String flyerDetails) {

        // Place into backend database? And later display in feed?
    }
}
