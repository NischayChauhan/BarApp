package com.example.nischay.crux;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nischay.crux.DB.drink;
import com.example.nischay.crux.dummy.DummyContent;
import com.example.nischay.crux.DB.drinks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An activity representing a list of Drinks. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DrinkDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DrinkListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private SimpleItemRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.drink_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.drink_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if(mAdapter.isNeeded())
            mAdapter.insertALL();
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, DummyContent.ITEM_MAP, mTwoPane);
        recyclerView.setAdapter(mAdapter);
    }


    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final DrinkListActivity mParentActivity;
        private final List<DummyContent.DummyItem> mValues;
        private final Map<String, DummyContent.DummyItem> mValues_map;
        private final boolean mTwoPane;


        private class mOnClickListener implements View.OnClickListener{
            int position;
            public mOnClickListener(int position){
                this.position = position;
            }
            @Override
            public void onClick(View view) {
                if(view!=null) {
                    DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//                    Snackbar.make(view, "ITEM NUMBER:: "+position, Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    remove(position);
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(DrinkDetailFragment.ARG_ITEM_ID, item.id);
                        DrinkDetailFragment fragment = new DrinkDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.drink_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, DrinkDetailActivity.class);
                        intent.putExtra(DrinkDetailFragment.ARG_ITEM_ID, item.id);

                        context.startActivity(intent);
                    }
                }
            }
        }

        SimpleItemRecyclerViewAdapter(DrinkListActivity parent,List<DummyContent.DummyItem> items,Map<String, DummyContent.DummyItem> items_map,boolean twoPane) {
            mValues = items;
            mValues_map = items_map;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        public void insertALL() {
            new load_DB().execute();
        }

        public boolean isNeeded(){
            if(mValues.size()==0)
                return true;
            return false;
        }

        private class load_DB extends AsyncTask<Void,Void,ArrayList<ArrayList<String>>>{
            @Override
            protected ArrayList<ArrayList<String>> doInBackground(Void... voids) {
                ArrayList<ArrayList<String>> res = new ArrayList<>();
                drinks db = drinks.getInstance();
                for(int i=0;i<db.drinks.size();i++){
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(db.drinks.get(i).getStrDrink());
                    temp.add(db.drinks.get(i).getStrDrinkThumb());
                    temp.add(db.drinks.get(i).getStrCategory());
                    temp.add(db.drinks.get(i).getStrAlcoholic());
                    temp.add(db.drinks.get(i).getStrInstructions());
                    String strIngredient = db.drinks.get(i).getStrIngredient1();
                    if(!db.drinks.get(i).getStrIngredient2().isEmpty()){
                        strIngredient += " , "+db.drinks.get(i).getStrIngredient2();
                        if(!db.drinks.get(i).getStrIngredient3().isEmpty()) {
                            strIngredient += " , " + db.drinks.get(i).getStrIngredient3();
                            if(!db.drinks.get(i).getStrIngredient4().isEmpty()) {
                                strIngredient += " , " + db.drinks.get(i).getStrIngredient4();
                                if(!db.drinks.get(i).getStrIngredient5().isEmpty()) {
                                    strIngredient += " , " + db.drinks.get(i).getStrIngredient5();
                                    if(!db.drinks.get(i).getStrIngredient6().isEmpty()) {
                                        strIngredient += " , " + db.drinks.get(i).getStrIngredient6();
                                        if(!db.drinks.get(i).getStrIngredient7().isEmpty()) {
                                            strIngredient += " , " + db.drinks.get(i).getStrIngredient7();
                                            if(!db.drinks.get(i).getStrIngredient8().isEmpty()) {
                                                strIngredient += " , " + db.drinks.get(i).getStrIngredient8();
                                                if(!db.drinks.get(i).getStrIngredient9().isEmpty()) {
                                                    strIngredient += " , " + db.drinks.get(i).getStrIngredient9();
                                                    if(!db.drinks.get(i).getStrIngredient10().isEmpty()) {
                                                        strIngredient += " , " + db.drinks.get(i).getStrIngredient10();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    temp.add(strIngredient);
                    res.add(temp);
                }
                return res;
            }

            @Override
            protected void onPostExecute(ArrayList<ArrayList<String>> strings) {
                super.onPostExecute(strings);
                for(int i=0;i<strings.size();i++){

                    String drink_name = strings.get(i).get(0);
                    String drink_image = strings.get(i).get(1);
                    String drink_category = strings.get(i).get(2);
                    String drink_alcoholic = strings.get(i).get(3);
                    String drink_instruction = strings.get(i).get(4);
                    String drink_ingredients = strings.get(i).get(5);
//                    Log.e("onPostExecute: ", drink_image);
                    DummyContent.DummyItem data = new DummyContent.DummyItem(String.valueOf(DummyContent.ITEMS.size()+1)
                            , drink_name,drink_image,drink_category,drink_alcoholic,drink_instruction,drink_ingredients);
                    mValues.add(data);
                    mValues_map.put(data.id,data);
                    notifyItemInserted(mValues.size());
                }
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drink_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).drink_name);
            holder.mContentView.setText(mValues.get(position).drink_category);
            if(!mValues.get(position).drink_image.isEmpty()){
                Picasso.get()
                        .load(mValues.get(position).drink_image)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.mDrinkImage);
            }
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(new mOnClickListener(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
            final ImageView mDrinkImage;
            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
                mDrinkImage = (ImageView) view.findViewById(R.id.drink_image);
            }
        }
    }
}
