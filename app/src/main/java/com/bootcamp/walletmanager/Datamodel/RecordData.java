package com.bootcamp.walletmanager.Datamodel;

import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.List;

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
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
    }
}
