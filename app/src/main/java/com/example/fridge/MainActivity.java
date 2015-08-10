package com.example.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fridge.webservice.RESTfulService;
import com.example.fridge.webservice.ServiceGenerator;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.fridge.GetItems";
    public UserDetails user = new UserDetails();
    public RESTfulService webservice = ServiceGenerator.createRESTfulService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



    public void onLogin(View view) {
        setPostRequestString();

        webservice.loginAccount(user, new Callback<UserProductsDetails>()
        {
            @Override
            public void success(UserProductsDetails serviceResponse, Response response)
            {
                Intent intent = new Intent(getApplicationContext(), DisplayLoginActivity.class);
                ArrayList<String> items = null;
                try
                {
                    items = ConvertServerResponseToString(serviceResponse);
                    user.setGroupID(serviceResponse.getGroupID());
                    user.setUserID(serviceResponse.getUserID());
                    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    //Date firstExpiryDate = serviceResponse.getProducts().get(1).getExpiryDate();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                if (null != items) {
                    intent.putStringArrayListExtra(EXTRA_MESSAGE, items);
                    intent.putExtra("UserDetails",user);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), serviceResponse.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                error.printStackTrace();
            }
        });
    }

    private void setPostRequestString()
    {
        EditText userName = (EditText) findViewById(R.id.user_name_text);
        EditText userPassword = (EditText) findViewById(R.id.password_text);
        String userNameText = userName.getText().toString();
        String userPasswordText = userPassword.getText().toString();

        user.setUserName(userNameText);
        user.setUserPassword(userPasswordText);
    }

    private String getPostResponse(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String bufferedStrChunk = null;

        while((bufferedStrChunk = bufferedReader.readLine()) != null){
            stringBuilder.append(bufferedStrChunk);
        }

        return  stringBuilder.toString();
    }


//    private ArrayList<String> JsonParser(String jsonObj) throws JSONException {
//        ArrayList<String> items = new ArrayList<String>();
//        JSONObject reader = new JSONObject(jsonObj);
//        if (reader.getInt("code") == 1) {
//            String userName = reader.getString("name");
//            items.add(userName);
//            JSONArray products = reader.getJSONArray("products");
//            for (int i = 0; i < products.length(); i++) {
//                StringBuilder item = new StringBuilder();
//                JSONObject product = products.getJSONObject(i);
//                item.append(product.getString("name"));
//                item.append(",");
//                item.append(product.getString("exp"));
//                item.append(",");
//                item.append(product.getString("type"));
//                items.add(item.toString());
//            }
//            return items;
//        }
//
//        return null;
//    }

    private ArrayList<String> ConvertServerResponseToString(UserProductsDetails jsonObj) throws JSONException {
        ArrayList<String> items = new ArrayList<>();
        if (jsonObj.getCode() == 1) {
            String userName = jsonObj.getUser();
            items.add(userName);
            for (int i = 0; i < jsonObj.getProducts().size(); i++) {
                StringBuilder item = new StringBuilder();
                item.append(jsonObj.getProducts().get(i).getProductName());
                item.append(",");
                item.append(jsonObj.getProducts().get(i).getExpiryDate());
                item.append(",");
                item.append(jsonObj.getProducts().get(i).getProductType());
                items.add(item.toString());
            }
            return items;
        }

        return null;
    }

//    private ArrayList<String> JsonParser(String jsonObj) throws JSONException {
//        ArrayList<String> items = new ArrayList<String>();
//        JSONObject reader = new JSONObject(jsonObj);
//        if (reader.getInt("code") == 1) {
//            String userName = reader.getString("name");
//            items.add(userName);
//            JSONArray products = reader.getJSONArray("products");
//            for (int i = 0; i < products.length(); i++) {
//                StringBuilder item = new StringBuilder();
//                JSONObject product = products.getJSONObject(i);
//                item.append(product.getString("name"));
//                item.append(", expiry date: ");
//                item.append(product.getString("exp"));
//                item.append("\ntype: ");
//                item.append(product.getString("type"));
//                items.add(item.toString());
//            }
//            return items;
//        }
//
//        return null;
//    }

    public void onRegister(View view) {
        //Toast.makeText(getBaseContext(), "onRegister test", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),DisplayRegisterActivity.class);
        startActivity(intent);


    }
}
