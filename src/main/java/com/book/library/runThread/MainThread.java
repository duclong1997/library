package com.book.library.runThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {
    public static void main(String[] args) {
//        100 thread run, 100 request
        ExecutorService executor = Executors.newFixedThreadPool(100);
        int index =0;
        for (int i = 0; i < 100; i++) {
            if (index==6)
            {
                index=0;
            }
                executor.execute(new RequestHandler("request-" + i, index));
                index++;
        }
        executor.shutdown(); // Không cho threadpool nhận thêm nhiệm vụ nào nữa

        while (!executor.isTerminated()) {
            // Chờ xử lý hết các request còn chờ trong Queue ...
        }
    }

}
