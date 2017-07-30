package com.thesis.brown.brown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;
import com.thesis.brown.brown.realm_model.Category;

import io.realm.Realm;
import io.realm.RealmResults;

public class CategoryFragment extends Fragment{

    public static final String TAB_NAME = "CATEGORIES";
    public static final String POSITION_KEY = "FragmentPositionKey";
    private final String TAG = "DEBUGGGGGGGG";
//    ListView listView;
    RecyclerView recyclerView;
    RealmResults<Category> allCategories = null;
    ProgressBar proLoading;
    Realm realm;

    public static CategoryFragment newInstance(Bundle args) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        realm = Realm.getDefaultInstance();
//        listView = (ListView) root.findViewById(R.id.listview);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        proLoading = (ProgressBar) root.findViewById(R.id.proLoadingMainCategory);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadCategory();
//        setEvents();
        return root;
    }

//    private void setEvents() {
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("click", "\n Type : " + allCategories.get(i).getType() + "\n Name : " + allCategories.get(i).getName() + "\n ID : " + allCategories.get(i).getId());
//                // We can understand what we are gonna get detail whether cate or sub-cate
//                Intent intent = new Intent(getActivity().getApplicationContext(), SubcategoryActivity.class);
//                intent.putExtra("CATEGORY_ID", allCategories.get(i).getId());
//                startActivity(intent);
//                Toast.makeText(getActivity().getApplicationContext(), allCategories.get(i).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void loadCategory() {
//        listView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        proLoading.setVisibility(View.GONE);
        allCategories = realm.where(Category.class).findAll();

//        listView.setAdapter(new CategoryListAdapter(getContext(), allCategories));
        recyclerView.setAdapter(new RecyclerAdapter(getContext(), allCategories, 3));
        Log.d("result", String.valueOf(allCategories));
    }

}
