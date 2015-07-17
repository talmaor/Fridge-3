package com.example.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.PatternSyntaxException;


public class DisplayLoginActivity extends ActionBarActivity {
    private ArrayList<ProductDetails> userProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_login);
        Intent intent = getIntent();
        ArrayList<String> items = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE); // Get the message from the intent
        setContentView(R.layout.activity_display_login);

        TextView hello = (TextView) findViewById(R.id.hello_text);
        String text = "Hello " + items.get(0) + "! :)";
        hello.setText(text);
        items.remove(0);
        setProducts(items);
        items = setPrintableProducts(items); // *********
        //items = sortProductsByName();
        //sortProductsByExpiryDate();
        //items = sortProductsByType();
        ListView itemsList = (ListView) findViewById(R.id.items_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, items); //****
        itemsList.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
        ListView itemsList = (ListView) findViewById(R.id.items_list);
        ArrayAdapter arrayAdapter;
        switch (item.getItemId()) {
            case R.id.action_sort_by_name:
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, sortProductsByName());
                itemsList.setAdapter(arrayAdapter);
                return true;
            case R.id.action_sort_by_exp_date:
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, sortProductsByExpiryDate());
                itemsList.setAdapter(arrayAdapter);
                return true;
            case R.id.action_sort_by_type:
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, sortProductsByType());
                itemsList.setAdapter(arrayAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setProducts(ArrayList<String> products) throws PatternSyntaxException { // Exeption !!!
        for (int i = 0; i < products.size(); i++) {
            String[] product = products.get(i).split(",");
            ProductDetails prod = new ProductDetails();
            prod.setProductName(product[0]);
            prod.setExpiryDate(product[1]);
            prod.setProductType(product[2]);
            userProducts.add(prod);
        }
    }

    private ArrayList<String> setPrintableUserProducts(ArrayList<ProductDetails> products) throws PatternSyntaxException { // Exeption !!!
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < products.size(); i++) {
            String item = "name: " + products.get(i).getProductName() + ", expiry date: "
                    + products.get(i).getExpiryDate() + "\ntype: " + products.get(i).getProductType();
            items.add(item);
        }

        return items;
    }

    private ArrayList<String> setPrintableProducts(ArrayList<String> products) throws PatternSyntaxException { // Exeption !!!
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < products.size(); i++) {
            String[] product = products.get(i).split(",");
            String item = "name: " + product[0] + ", expiry date: " + product[1] + "\ntype: " + product[2];
            items.add(item);
        }

        return items;
    }

    private ArrayList<String> sortProductsByName() {
        Collections.sort(userProducts, ProductDetails.ProductNameComparator);
        return setPrintableUserProducts(userProducts);
    }

    private ArrayList<String> sortProductsByExpiryDate() {
        Collections.sort(userProducts, ProductDetails.ProductExpiryDateComparator);
        return setPrintableUserProducts(userProducts);
    }

    private ArrayList<String> sortProductsByType() {
        Collections.sort(userProducts, ProductDetails.ProductTypeComparator);
        return setPrintableUserProducts(userProducts);
    }

}