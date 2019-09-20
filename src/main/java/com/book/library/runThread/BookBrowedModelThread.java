package com.book.library.runThread;

import lombok.Data;

@Data
public class BookBrowedModelThread {
    private int idBook;
    private long beginBrow;
    private long endBrow;
    public BookBrowedModelThread(int ibBook, long beginBrow, long endBrow){
        this.idBook =ibBook;
        this.beginBrow =beginBrow;
        this.endBrow= endBrow;
    }
}
