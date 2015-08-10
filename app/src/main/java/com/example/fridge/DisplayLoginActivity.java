package com.example.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.fridge.webservice.RESTfulService;
import com.example.fridge.webservice.ServiceGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DisplayLoginActivity extends ActionBarActivity {
    private ArrayList<ProductDetails> userProducts = new ArrayList<>();
    TabHost tabHost;
    UserDetails user;
    public RESTfulService webservice = ServiceGenerator.createRESTfulService();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_login);

        Intent intent = getIntent();
        ArrayList<String> items = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE); // Get the message from the intent
        setContentView(R.layout.activity_display_login);

        user = (UserDetails)intent.getSerializableExtra("UserDetails");

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("availableProducts");
        tabSpec.setContent(R.id.tabAvailableProducts);
        tabSpec.setIndicator("Available products");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("missingProducts");
        tabSpec.setContent(R.id.tabMissingProducts);
        tabSpec.setIndicator("Missing products");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("unavailableProducts");
        tabSpec.setContent(R.id.tabUnavailableProducts);
        tabSpec.setIndicator("Unavailable products");
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                switch(tabId)
                {
                    case "availableProducts":
                        onMissing();
                        break;
                    case "missingProducts":
                        onMissing();
                        break;
                    case "unavailableProducts":
                        onMissing();
                        break;
                }
            }
        });

        TextView hello = (TextView) findViewById(R.id.hello_text);
        String text = "Hello " + items.get(0) + "! :)";
        hello.setText(text);
        items.remove(0);
        setProducts(items);
        items = setPrintableProducts(items); // *********
        //items = sortProductsByName();
        //sortProductsByExpiryDate();
        //items = sortProductsByType();
        ListView itemsList = (ListView) findViewById(R.id.available_products_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, items); //****
        itemsList.setAdapter(arrayAdapter);

        /*
        TableLayout t1;
        TableLayout tl = (TableLayout) findViewById(R.id.missing_table);

        TableRow tr_head = new TableRow(this);
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView label_name = new TextView(this);
        label_name.setId(20);
        label_name.setText("Name");
        label_name.setTextColor(Color.WHITE);
        label_name.setPadding(5, 5, 5, 5);
        tr_head.addView(label_name);// add the column to the table row here

        TextView label_type = new TextView(this);
        label_type.setId(21);// define id that must be unique
        label_type.setText("Type"); // set the text for the header
        label_type.setTextColor(Color.WHITE); // set the color
        label_type.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_type); // add the column to the table row here

        TextView label_date = new TextView(this);
        label_date.setId(22);// define id that must be unique
        label_date.setText("Date"); // set the text for the header
        label_date.setTextColor(Color.WHITE); // set the color
        label_date.setPadding(5, 5, 5, 5); // set the padding (if required)

        tr_head.addView(label_date); // add the column to the table row here

        tl.addView(tr_head, new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        */
    }


    public void onMissing() {

        webservice.getMissings(user, new Callback<UserProductsDetails>() {
            @Override
            public void success(UserProductsDetails serviceResponse, Response response) {
                ListView itemsList = (ListView) findViewById(R.id.unavailable_products_list);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, serviceResponse.getProducts()); //****
                itemsList.setAdapter(arrayAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
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
        ListView itemsList = (ListView) findViewById(R.id.available_products_list);
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

    private void setProducts(ArrayList<String> products) throws PatternSyntaxException
    { // Exeption !!!
        for (int i = 0; i < products.size(); i++)
        {
            String[] product = products.get(i).split(",");
            ProductDetails prod = new ProductDetails();
            prod.setProductName(product[0]);

            Date date = null;
            try
            {
                String serverDate = product[1];
                String [] serverDateSplit = serverDate.split(".000Z");
                serverDate = serverDateSplit[0];
                String pattern = "yyyy-MM-dd'T'HH:mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                date = format.parse(serverDate);
                //prod.setExpiryDate(date);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }

            prod.setProductType(product[2]);
            userProducts.add(prod);
        }
    }

    private ArrayList<String> setPrintableUserProducts(ArrayList<ProductDetails> products) throws PatternSyntaxException { // Exeption !!!
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < products.size(); i++) {
            String item = products.get(i).getProductName() + "\n Expiry: "
                    + products.get(i).getExpiryDate() + "\nType: " + products.get(i).getProductType();
            items.add(item);
        }

        return items;
    }

    private ArrayList<String> setPrintableProducts(ArrayList<String> products) throws PatternSyntaxException { // Exeption !!!
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < products.size(); i++) {
            String[] product = products.get(i).split(",");
            String item = product[0] + "\nExpiry: " + product[1] + "\nType: " + product[2] +"\n";
            items.add(item);
        }

        return items;
    }

    private ArrayList<String> sortProductsByName() {
        Collections.sort(userProducts, ProductDetails.ProductNameComparator);
        return setPrintableUserProducts(userProducts);
    }

    private ArrayList<String> sortProductsByExpiryDate() {
        //Collections.sort(userProducts, ProductDetails.ProductExpiryDateComparator);
        return setPrintableUserProducts(userProducts);
    }

    private ArrayList<String> sortProductsByType() {
        Collections.sort(userProducts, ProductDetails.ProductTypeComparator);
        return setPrintableUserProducts(userProducts);
    }

}