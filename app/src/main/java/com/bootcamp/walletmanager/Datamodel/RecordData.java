package com.bootcamp.walletmanager.Datamodel;

import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecordData {
    private List<Records> mRecords;

    public List<Records> getRecords() {
        return mRecords;
    }

    public RecordData() {
        initializeData();
    }

    private void initializeData(){
        mRecords = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Records> Records = realm.where(Records.class).findAll();
        for (int i = 0; i < Records.size(); i++) {
            Records record = Records.get(i);
            mRecords.add(record);
        }
    }
}
