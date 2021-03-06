package com.lukemi.myandroid.widget;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lukemi.myandroid.view.ShowBigPicClass;

/**
 * View 截图
 */
public class ViewPrintActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frameLayout;
    private Button screenShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_view_print);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        frameLayout = ((FrameLayout) findViewById(com.lukemi.myandroid.R.id.frameLayout));
        screenShot = ((Button) findViewById(com.lukemi.myandroid.R.id.screenShot));
        screenShot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.myandroid.R.id.screenShot:
                Bitmap viewBitmap = getViewBitmap(frameLayout);
                ShowBigPicClass sbpc =  new ShowBigPicClass(this,viewBitmap);
                sbpc.showDetailPhoto();
                break;
        }
    }

    /**
     * 将View 转化成 BitMap
     *
     * @param addViewContent
     * @return
     */
    private Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);

        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }

}
