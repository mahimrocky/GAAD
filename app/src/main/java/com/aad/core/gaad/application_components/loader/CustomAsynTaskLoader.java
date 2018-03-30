package com.aad.core.gaad.application_components.loader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class CustomAsynTaskLoader extends AsyncTaskLoader<ArrayList<FriendsModel>> {

    private ArrayList<FriendsModel> friendsModels;
    private static final String TAG = CustomAsynTaskLoader.class.getSimpleName();
    private ArrayList<FriendsModel> friendsData;

    public CustomAsynTaskLoader(Context context,ArrayList<FriendsModel> friendsModels) {
        super(context);
        this.friendsModels = friendsModels;
    }

    @Override
    public ArrayList<FriendsModel> loadInBackground() {

        try {
            synchronized (this) {
                wait(300);
                setFriendsData(friendsModels);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return friendsModels;
    }

    @Override
    public void deliverResult(ArrayList<FriendsModel> data) {
        super.deliverResult(data);
    }

    private void setFriendsData(ArrayList<FriendsModel> friendsData) {

        friendsData.add(new FriendsModel("Hoang An", false));
        friendsData.add(new FriendsModel("Nguyen Minh Ngan", false));
        friendsData.add(new FriendsModel("Phan Van Binh", true));
        friendsData.add(new FriendsModel("Pham Nhat Thanh", false));
        friendsData.add(new FriendsModel("Bui Cong Thanh", true));
        friendsData.add(new FriendsModel("Vu Huu Nhan", true));
    }
}
