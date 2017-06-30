package cn.gridlife.mydemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chenenyu.router.Router;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gridlife.bzblibrary.utils.BzbToast;
import cn.gridlife.mydemo.bean.MainListViewBean;

public class MainActivity extends Activity {
    @BindView(R.id.lv_main_activity)
    ListView listView;
    MyAdapter adapter;
    String[] titles = {"1.MyRecyclerView基本使用"};
    String[] contents = {"RecyclerView的基本使用"};
    MainListViewBean bean;
    ArrayList<MainListViewBean> list = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyItemClick());

        for (int i = 0; i < titles.length; i++) {
            bean = new MainListViewBean();
            bean.setTitle(titles[i]);
            bean.setContent(contents[i]);
            list.add(bean);
        }
        adapter.notifyDataSetChanged();


    }

    private class MyItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    Router.build("MyRecyclerView").go(MainActivity.this);

                    break;
            }
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView == null) {
                holder=new ViewHolder();
//                v = View.inflate(getApplicationContext(), R.layout.itme_activity_main, null);
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.itme_activity_main, null);

                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_item_activity_main);
                holder.tvContent = (TextView) convertView .findViewById(R.id.tv_content_item_activity_main);
                convertView.setTag(holder);
            } else {
                holder= (ViewHolder) convertView.getTag();
            }
            holder.tvTitle.setText(list.get(position).getTitle());
            holder.tvContent.setText(list.get(position).getContent());
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
    }
}
