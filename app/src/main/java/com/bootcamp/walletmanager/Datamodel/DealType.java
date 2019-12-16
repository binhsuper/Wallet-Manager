package com.bootcamp.walletmanager.Datamodel;

public class DealType {
    String typeName;
    String typeKind;
    int typeImg;

    public String getTypeName() {
        return typeName;
    }

    public String getTypeKind() {
        return typeKind;
    }

    public int getTypeImg() {
        return typeImg;
    }

    public DealType(String typeName, int typeImg, String typeKind) {
        this.typeName = typeName;
        this.typeKind = typeKind;
        this.typeImg = typeImg;
    }
}
