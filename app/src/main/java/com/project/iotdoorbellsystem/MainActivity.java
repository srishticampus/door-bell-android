package com.project.iotdoorbellsystem;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText ipEt;
    private WebView webViewOne;
    private Button temperatureStatusBt;

    private String ipAddress;
    private Button startBt;
    private Button stopBt;

    // define the number of times to call the method
    int numTimesToCallMethod = 5;

    // create a new handler
    Handler handler = new Handler();

    // define a boolean flag to control whether the method should continue to be called
    boolean shouldCallMethod = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        WebSettings webSettings = webViewOne.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        temperatureStatusBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (ipEt.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please Enter The Ip Address", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    ipAddress = ipEt.getText().toString();
//                    webViewDisplayOne(ipAddress, "distance");
//
//                }
//
//
//            }
//        });


        // create a new runnable that calls the method
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                //  myMethod(); // replace "myMethod" with the name of the method you want to call

                // check the flag to determine whether to continue calling the method or not
                if (shouldCallMethod) {
                    handler.postDelayed(this, 5000); // 5000 milliseconds = 5 seconds

                    if (ipEt.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter The Ip Address", Toast.LENGTH_SHORT).show();
                    } else {

                        ipAddress = ipEt.getText().toString();
                        webViewDisplayOne(ipAddress, "distance");

                    }
                }
            }
        };

        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shouldCallMethod = true;

                // post the runnable to the handler with a delay of 5 seconds
                for (int i = 0; i < numTimesToCallMethod; i++) {
                    handler.postDelayed(runnable, i * 5000); // 5000 milliseconds = 5 seconds
                }
            }
        });
        stopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shouldCallMethod = false;
            }
        });


    }


    public void webViewDisplayOne(String webUrl, String webEndPoint) {
        webViewOne.loadUrl("http://" + webUrl + "/" + webEndPoint);
        //webViewOne.loadUrl("https://www.google.com/");
        webViewOne.setWebViewClient(new WebViewClient());
        webViewOne.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                // show "No data" message in WebView
                String html = "<html><body><h1>No data</h1></body></html>";
                view.loadData(html, "text/html", "UTF-8");

            }
        });

    }

    private void initView() {
        ipEt = findViewById(R.id.ip_et);
        webViewOne = findViewById(R.id.web_view_one);
        startBt = findViewById(R.id.start_bt);
        stopBt = findViewById(R.id.stop_bt);
    }
}