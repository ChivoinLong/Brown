package com.thesis.brown.brown.store_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thesis.brown.brown.MainShowDetailStore;
import com.thesis.brown.brown.R;

import java.util.ArrayList;

public class MainListStore extends Fragment {

    ListView lisStoreList;
    ListStoreListAdp adp;

    //set the models ArrayList to be data ready variable.
    ArrayList<ListStoreListModel> models = getListData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_list_store, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lisStoreList = (ListView) getActivity().findViewById(R.id.lisStoreList);


        adp = new ListStoreListAdp(getActivity(), R.layout.lis_store_list, models);
        lisStoreList.setAdapter(adp);

        lisStoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MainShowDetailStore.class);

                Bundle storeDataBundle = new Bundle();
                storeDataBundle.putString("name",models.get(i).getTitle());
                storeDataBundle.putString("address",models.get(i).getRoad());
                storeDataBundle.putString("phone",models.get(i).getPhone());
                storeDataBundle.putString("time",models.get(i).getOpeningTime());
                storeDataBundle.putInt("storeImage",models.get(i).getImage());
                storeDataBundle.putDouble("latitude",models.get(i).getLatitude());
                storeDataBundle.putDouble("longitude",models.get(i).getLongitude());

                intent.putExtra("storeData", storeDataBundle);

                startActivity(intent);
            }
        });
    }


    //work : Return list of stores
    ArrayList<ListStoreListModel> getListData (){
        ArrayList<ListStoreListModel> models = new ArrayList<>();

        models.add(
                new ListStoreListModel(
                        "Brown Pencil",
                        "#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Cambodia",
                        "069 697 079",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 08:00 P.M",
                        R.drawable.brown_pencil,
                        11.561386, 104.925843));
        models.add(
                new ListStoreListModel(
                        "Brown 51",
                        "St. 51 Corner 302 Near British International School, Boengkengkang 1, Khan Chamkarmorn, Phnom Penh, C A.Mbodia",
                        "069 737 567",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_51,
                        11.553199, 104.926741));
        models.add(
                new ListStoreListModel(
                        "Brown Riverside",
                        "#1 st. 98 corner Sisowath Quay (adjacent to KFC Riverside), Wat Phnom, Khan Daun Penh, Phnom Penh, Cambodia",
                        "010 917 907",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_riverside,
                        11.576076, 104.926168));
        models.add(
                new ListStoreListModel(
                        "Brown IFL",
                        "#114 Russian Blvd corner st.259, Tuk Laak 1, Khan Toulkok, Phnom Penh, Cambodia",
                        "098 555 221",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_ifl,
                        11.568193, 104.894405));
        models.add(
                new ListStoreListModel(
                        "Brown Sothearos",
                        "#102, Sothearos Blvd, St. 266, Phnom Penh, Cambodia", "098 666 221",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_sothearos,
                        11.558378, 104.933264));
        models.add(
                new ListStoreListModel(
                        "Brown TK",
                        "#80, st.315, Boengkak 1, Khan Toulkok, Phnom Penh, Cambodia",
                        "098 777 113",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_tk,
                        11.583269,
                        104.898683));
        models.add(
                new ListStoreListModel("Brown Roastery BKK",
                        "#23, st. 57 Corner 294, Boengkengkang 1, Khan Chamkarmorn, Phnom Penh, Cambodia",
                        "098 888 331",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 10:00 P.M",
                        R.drawable.brown_roastery_bkk,
                        11.553241,
                        104.924805));
        models.add(
                new ListStoreListModel(
                        "Brown AEON",
                        "Lot 038, AEON Mall, Tonle Basak, Khan Chamkarmorn, Phnom Penh, Cambodia",
                        "098 888 692",
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 10:00 P.M",
                        R.drawable.brown_aeon,
                        11.547848,
                        104.934323));
        models.add(
                new ListStoreListModel("Brown Bokor",
                        "#16, Mao Tse Tung corner 71, Tonle Basak, Khan Chamkarmorn, Phnom Penh, Cambodia",
                        "098 888 527", 
                        "www.facebook.com/browncoffee.kh",
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_bokor,
                        11.544028,
                        104.923345));
        models.add(
                new ListStoreListModel(
                        "Brown Preah Norodom",
                        "114c Norodom Blvd, Tonle Basak, Khan Chamkarmorn, Phnom Penh, Cambodia", 
                        "098 888 010", 
                        "www.facebook.com/browncoffee.kh" ,
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_preah_norodom,
                        11.551117,
                        104.928773));
        models.add(
                new ListStoreListModel("Brown Raintree",
                        "st 110 near Canadia Tower, Wat Phnom, Khan Daun Penh, Phnom Penh, Cambodia", 
                        "098 999 318", 
                        "www.facebook.com/browncoffee.kh" ,
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_raintree,
                        11.572002,
                        104.919136));
        models.add(
                new ListStoreListModel(
                        "Brown Roastery TK",
                        "st 110 near Canadia Tower, Wat Phnom, Khan Daun Penh, Phnom Penh, Cambodia", 
                        "070 222 194", 
                        "www.facebook.com/browncoffee.kh" ,
                        "Everyday 06:30 A.M - 09:00 P.M",
                        R.drawable.brown_roastery_tk,
                        11.580663,
                        104.895895));
        return models;
}


}
