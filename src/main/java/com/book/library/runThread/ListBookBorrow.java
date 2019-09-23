package com.book.library.runThread;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ListBookBorrow {
    private List<BookBrowedModelThread> listBookBorrow = new ArrayList<>();

    public ListBookBorrow(){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        long tsBegin = timestamp.getTime();

        Random ran = new Random();
        int valueRandom = 10+ ran.nextInt(295);
        long tsEnd =tsBegin+valueRandom;

        int index = 20+ ran.nextInt(12);
        int i=1;
        while (i<=index) {
            listBookBorrow.add(new BookBrowedModelThread(i, tsBegin, tsEnd));
            i++;
        }
    }
}
