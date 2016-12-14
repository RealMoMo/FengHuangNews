package momo.com.week10_project.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import momo.com.week10_project.R;
import momo.com.week10_project.entity.SearchContentEntity;

/**
 * 搜索Activity
 */
public class SearchActivity extends AppCompatActivity {

    private EditText et_search;
    private ImageView iv_clear;
    private TextView iv_search;
    private ListView lv;

    private List<SearchContentEntity> totalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
