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
        mTypes.add(new DealType("Thực phẩm", R.drawable.type_food, "spending"));
        mTypes.add(new DealType("Tạp hoá", R.drawable.type_groceries, "spending"));
        mTypes.add(new DealType("Sức khoẻ", R.drawable.type_health,"spending"));
        mTypes.add(new DealType("Di chuyển", R.drawable.type_transport,"spending"));
        mTypes.add(new DealType("Hoá đơn", R.drawable.type_tax,"spending"));
        mTypes.add(new DealType("Khác", R.drawable.type_other,"spending"));
    }

    public void getIncomeType(){
        mTypes = new ArrayList<>();
        mTypes.add(new DealType("Quà tặng", R.drawable.type_gift,"income"));
        mTypes.add(new DealType("Lương", R.drawable.type_salary,"income"));
        mTypes.add(new DealType("Bán đồ", R.drawable.type_sale,"income"));
        mTypes.add(new DealType("Thưởng", R.drawable.type_reward,"income"));
        mTypes.add(new DealType("Khác", R.drawable.type_other,"income"));
    }
}
