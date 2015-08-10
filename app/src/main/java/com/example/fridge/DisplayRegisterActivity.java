package com.example.fridge;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.fridge.webservice.RESTfulService;
import com.example.fridge.webservice.ServiceGenerator;
import com.example.fridge.webservice.ServiceResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DisplayRegisterActivity extends ActionBarActivity {

    public RESTfulService webservice = ServiceGenerator.createRESTfulService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_register);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_register, menu);
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

    public void onRegister(View view) {

        ArrayList<String> postString = setRegisterRequestString();

        webservice.registerAccount(postString, new Callback<ServiceResponse>() {
           @Override
           public void success(ServiceResponse serviceResponse, Response response) {


           }

           @Override
           public void failure(RetrofitError error) {
               error.printStackTrace();
           }
       });
    }


    private ArrayList<String> setRegisterRequestString() {
        EditText name = (EditText) findViewById(R.id.register_name_text);
        EditText password = (EditText) findViewById(R.id.register_password_text);
        EditText groupId = (EditText) findViewById(R.id.register_group_id_text);
        String nameText = name.getText().toString();
        String passwordText = password.getText().toString();
        String groupIdText = groupId.getText().toString();

        ArrayList<String> registerDetails = new ArrayList<>();
        registerDetails.add(nameText);
        registerDetails.add(passwordText);
        registerDetails.add(groupIdText);

        return registerDetails;
    }


    private String getRegisterPostResponse(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String bufferedStrChunk = null;

        while((bufferedStrChunk = bufferedReader.readLine()) != null){
            stringBuilder.append(bufferedStrChunk);
        }

        return  stringBuilder.toString();
    }
}
