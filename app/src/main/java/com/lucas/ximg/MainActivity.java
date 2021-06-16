package com.lucas.ximg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.lucas.library.XImg;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView)findViewById(R.id.img);

        XImg.getInstance().loadImgCircle(img, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2012%2F5%2F10%2F886f35f5791f425285e4839dcb01ff06-3.jpg&refer=http%3A%2F%2Fbos.pgzs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626324090&t=501a9cd7af4dc38f5460857f30c3b4ee");

    }
}