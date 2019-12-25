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

    public DealType() {
    }

    public DealType(String typeName, int typeImg, String typeKind) {
        this.typeName = typeName;
        this.typeKind = typeKind;
        this.typeImg = typeImg;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTypeKind(String typeKind) {
        this.typeKind = typeKind;
    }

    public void setTypeImg(int typeImg) {
        this.typeImg = typeImg;
    }
}
