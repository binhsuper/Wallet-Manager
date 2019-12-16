package com.bootcamp.walletmanager.Datamodel;

import android.content.Context;

import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.List;

public class DealTypeData {
    private List<DealType> mTypes;

    public List<DealType> getTypes() {
        return mTypes;
    }

    public DealTypeData(int type) {
        switch (type) {
            case 0:
                getIncomeType();
                break;
            case 1:
                getSpendingType();
                break;
        }
    }

    public void getSpendingType(){
        mTypes = new ArrayList<>();
        mTypes.add(new DealType("Food", R.drawable.type_food, "spending"));
        mTypes.add(new DealType("Groceries", R.drawable.type_groceries, "spending"));
        mTypes.add(new DealType("Health", R.drawable.type_health,"spending"));
        mTypes.add(new DealType("Transport", R.drawable.type_transport,"spending"));
        mTypes.add(new DealType("Tax", R.drawable.type_tax,"spending"));
        mTypes.add(new DealType("Other", R.drawable.type_other,"spending"));
    }

    public void getIncomeType(){
        mTypes = new ArrayList<>();
        mTypes.add(new DealType("Gift", R.drawable.type_gift,"income"));
        mTypes.add(new DealType("Salary", R.drawable.type_salary,"income"));
        mTypes.add(new DealType("Sale", R.drawable.type_sale,"income"));
        mTypes.add(new DealType("Reward", R.drawable.type_reward,"income"));
        mTypes.add(new DealType("Other", R.drawable.type_other,"income"));
    }
}
