package com.example.jh.swipelayoutdelete;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jh.swipelayoutdelete.adapter.CommonAdapter;
import com.example.jh.swipelayoutdelete.adapter.ViewHolder;
import com.example.jh.swipelayoutdelete.layout.SwipeLayout;
import com.example.jh.swipelayoutdelete.layout.SwipeLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 鉴于自己的github上没有写过侧滑删除，项目中也正好需要所以写个侧滑删除，也算是学习。
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    SwipeLayout swipelayout_button;
    ListView listView;
    private Adapter adapter;
    private ArrayList<String> datas = new ArrayList<>();
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //—————————————BUTTON———————————————
//        swipelayout_button.setOnSwipeLayoutClickListener(new SwipeLayout.OnSwipeLayoutClickListener() {
//            @Override
//            public void onClick() {
//                Toast.makeText(MainActivity.this, "BUTTON", Toast.LENGTH_SHORT).show();
//            }
//        });
        swipelayout_button.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick");
                Toast.makeText(MainActivity.this, "BUTTON", Toast.LENGTH_SHORT).show();
            }
        });
        ((LinearLayout)swipelayout_button.getDeleteView()).getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "DELETE", Toast.LENGTH_SHORT).show();
            }
        });

        // ----------------------------ListView--------------------
        initData();
        adapter = new Adapter(this, datas, R.layout.item_listview);
        listView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++){
            datas.add(i + "项");
        }
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        swipelayout_button = (SwipeLayout) findViewById(R.id.swipelayout_button);
        // 初始化SwipeLayout
        SwipeLayoutManager.getInstance().closeOpenInstance();
    }


    // adapter适配器
    public class Adapter extends CommonAdapter<String> {

        // 默认构造
//    public Adapter(Context mContext, List mDatas, LayoutInflater mInflater, int layoutId) {
//        super(mContext, mDatas, mInflater, layoutId);
//    }

        public Adapter(Context mContext, List<String> datas, int layoutId) {
            super(mContext, datas, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final String s) {
            holder.setText(R.id.tv_name, s);
            SwipeLayout swipeLayout = holder.getView(R.id.swipelayout);
            swipeLayout.setOnSwipeLayoutClickListener(new SwipeLayout.OnSwipeLayoutClickListener() {
                @Override
                public void onClick() {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            });
            ((LinearLayout)swipeLayout.getDeleteView()).getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "call", Toast.LENGTH_SHORT).show();
                }
            });

            ((LinearLayout)swipeLayout.getDeleteView()).getChildAt(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SwipeLayoutManager.getInstance().closeOpenInstance();
                    datas.remove(s);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(mContext, "datas.size():" + datas.size(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
