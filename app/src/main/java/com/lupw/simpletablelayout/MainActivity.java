package com.lupw.simpletablelayout;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import com.lupw.tablelayout.view.TableView;
import com.lupw.tablelayout.view.VPTopTableView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VPTopTableView vpTopTableView = findViewById(R.id.vpTableView);

        List<String> titleList = Arrays.asList("标题1", "标题2", "标题3", "标题4", "标题5", "标题6",
                "标题7", "标题8", "标题9", "标题10");
        List<Fragment> dataList = new ArrayList<>();
        for (String s : titleList) {
            dataList.add(MFragment.getInstance(s));
        }

        MFragmentAdapter adapter = new MFragmentAdapter(getSupportFragmentManager(), dataList);
        vpTopTableView.setTitles(titleList);
        vpTopTableView.setAdapter(adapter);
    }
}
