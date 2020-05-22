package com.kcq.coolweather.jbtab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jd.tv.smarthelper.JDSmartSDK;
import com.jd.tv.smarthelper.interf.IResult;
import com.kcq.coolweather.R;
import com.kcq.coolweather.jbtab.gson.SmartTabProductEntity;
import com.kcq.coolweather.jbtab.gson.SmartTabProductGroup;
import com.kcq.coolweather.jbtab.gson.SmartTabTitleGroup;
import com.kcq.coolweather.uitls.JsonHandleResult;
import com.kcq.coolweather.uitls.Utility;

public class JDTabActivity extends AppCompatActivity implements JsonHandleResult {
    private static final String TAG = "JDTabActivity";
    private Button btnRefresh;
    private TextView tvResponse;
    private Button btnLogin;
    private Button btnShopping;
    private Button btnJump;
    private String filePath;
    private String action;
    private Uri jumpUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdtab);
        btnRefresh = findViewById(R.id.btn_refresh);
        tvResponse = findViewById(R.id.tv_response);
        btnLogin = findViewById(R.id.btn_login);
        btnJump = findViewById(R.id.btn_jump);
        btnShopping = findViewById(R.id.btn_shopping);
        filePath = getExternalFilesDir("file").getPath();
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null) {
                    Intent it = new Intent();
                    it.setAction(action);
                    it.setData(jumpUri);
                    startActivity(it);
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setAction("jd.tv.jump");
                it.setData(Uri.parse("jd://tv.jump.activity?customtype=24"));
                startActivity(it);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick is connect: " + JDSmartSDK.getInstance().isConnect());
                JDSmartSDK.getInstance().getSmartTabData(new IResult<String>() {
                    @Override
                    public void callBack(String s) {
//                        FileUtils.writeToFile(filePath+"/jdresponse.txt",s);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResponse.setText("Start");
                            }
                        });
//                        Log.i(TAG, "callBack: "+s);
                        int strLength = s.length();
                        int start = 0;
                        int end = 1000;
                        Log.d(TAG, "callBack:no.1");
                        Utility.handleSmartTabInfo(s, JDTabActivity.this, filePath);
                 /*       for (int i = 0; i < 100; i++) {
                            //剩下的文本还是大于规定长度则继续重复截取并输出
                            if (strLength > end) {
                                Log.e(TAG + i, s.substring(start, end));
                                start = end;
                                end = end + 1000;
                            } else {
                                Log.e(TAG, s.substring(start, strLength));
                                break;
                            }
                        }*/
                    }
                });
            }
        });

        btnShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JDSmartSDK.getInstance().getShoppingTabData(new IResult<String>() {
                    @Override
                    public void callBack(String s) {
                        Utility.handleSmartTabInfo(s, JDTabActivity.this, filePath);
                    }
                });
            }
        });

    }

    @Override
    public void smartData(SmartTabTitleGroup smartTabTitleGroup, final SmartTabProductGroup smartTabProductGroup) {
        Log.d(TAG, "smartData: ");
        final SmartTabProductEntity smartTabProductEntity = smartTabProductGroup.getItems().get(0);
        action = smartTabProductEntity.getAction();
        jumpUri = Uri.parse(smartTabProductEntity.getData());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResponse.setText(smartTabProductEntity.toString());
            }
        });
    }
}
