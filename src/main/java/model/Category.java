package model;

public class Category {
    private int idCategory;
    private String nameCategory;

    public Category(int id, String name) {
        this.idCategory = id;
        this.nameCategory = name;
    }

    public Category(String name) {
        this.nameCategory = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
