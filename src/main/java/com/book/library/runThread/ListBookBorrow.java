package com.book.library.runThread;

import com.book.library.models.BookBrowedUserModel;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
public class ListBookBorrow {
    private List<BookBrowedModelThread> listBookBorrow = new ArrayList<>();

    public ListBookBorrow(){

        long ts = System.currentTimeMillis()/1000;

        Random ran = new Random();
        int valueRandom = 10+ ran.nextInt(295);
        long ts1 =ts+valueRandom;

        Random valueRan= new Random();
        int index = 20+ ran.nextInt(12);
        int i=1;
        while (i<=index) {
            listBookBorrow.add(new BookBrowedModelThread(i, ts, ts1));
            i++;
        }
    }
}
