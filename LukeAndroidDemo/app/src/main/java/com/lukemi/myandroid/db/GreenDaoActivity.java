package com.lukemi.myandroid.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_green_dao);

        initView();
    }

    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.addDB).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.delDB).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.updateDB).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.queryDB).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.chgDBStructure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.myandroid.R.id.addDB:
                //增加
                break;
            case com.lukemi.myandroid.R.id.delDB:
                //删除
                break;
            case com.lukemi.myandroid.R.id.updateDB:
                //更新
                break;
            case com.lukemi.myandroid.R.id.queryDB:
                //查询
                break;
            case com.lukemi.myandroid.R.id.chgDBStructure:
                //升级
                break;
            default:
                break;
        }
    }
}
