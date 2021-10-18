package com.lovish.mvvmapp.main;

public class MainRepository {

    private static MainRepository instance = null;

    public static MainRepository getInstance() {
        if (instance == null) {
            instance = new MainRepository();
        }
        return instance;
    }
}
