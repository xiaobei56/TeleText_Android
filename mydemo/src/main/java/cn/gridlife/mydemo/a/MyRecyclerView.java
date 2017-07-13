package cn.gridlife.mydemo.a;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gridlife.mydemo.R;

/**
 * cn.gridlife.mydemo
 * TeleText
 * Created by BEI on 2017/6/26.
 */

@Route("MyRecyclerView")
public class MyRecyclerView extends Activity {
    @BindView(R.id.recycler_view_recyclerView)
    RecyclerView recyclerView;
    private List<String> mDatas;
    MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);
//        ActionBar actionBar = getActionBar();
//        actionBar.show();
        ButterKnife.bind(this);
        initData();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,6));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        注意，这里更新数据集不是用adapter.notifyDataSetChanged()而是
//        notifyItemInserted(position)与notifyItemRemoved(position)
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_item:
                adapter.addData(1);
                break;
            case R.id.remove_item:
                adapter.removeData(1);
                break;
        }
        return true;
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
        public void addData(int position) {
            mDatas.add(position, "Insert One");
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
        @Override
        public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyRecyclerViewHolder holder=new MyRecyclerViewHolder(LayoutInflater.from(MyRecyclerView.this).inflate(R.layout.item_recycler_view,parent,false));

            return holder;
        }

        @Override
        public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }
    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyRecyclerViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_item_recycler);
        }
    }

}
