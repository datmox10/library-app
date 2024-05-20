package com.library.app.model;

import java.util.ArrayList;
import java.util.List;

public class BorrowBookResponse {

    private ArrayList<BorrowBookMD> list;

    public ArrayList<BorrowBookMD> getBorrowBookMDS() {
        return list;
    }

    public void setBorrowBookMDS(ArrayList<BorrowBookMD> borrowBookMDS) {
        this.list = borrowBookMDS;
    }
}
