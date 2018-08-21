package com.example.rd_softwaredeveloper.imageswitcherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    private Gallery gallery;
    private ImageSwitcher imageSwitcher;
    private SimpleAdapter simpleAdapter;
    private int[] image =
    {
            R.drawable.cat, R.drawable.flower, R.drawable.hippo,
            R.drawable.monkey, R.drawable.mushroom, R.drawable.panda,
            R.drawable.rabbit, R.drawable.raccoon
    };

    private String[] imgText =
    {
            "cat", "flower", "hippo", "monkey", "mushroom", "panda", "rabbit", "raccoon"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.image_switcher);
        gallery = (Gallery)findViewById(R.id.gallery);
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < image.length; i++)
        {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", image[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, items, R.layout.simple_adapter, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});

        gallery.setAdapter(simpleAdapter);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView()
            {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
                return imageView;
            }

        });

        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id)
            {
                imageSwitcher.setImageResource(image[position]);
            }
        });
    }
}
