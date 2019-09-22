package com.book.library.runThread;

import com.book.library.models.BookBrowedUserModel;
import com.google.gson.Gson;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class RequestHandler implements Runnable {
    String name;
    int index =0;
    public RequestHandler(String name, int index){
        this.name =name;
        this.index =index;
    }

    String USER_AGENT ="application/json";
    // 0: long
    String AUTHENTICATION= "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjkyNDM3NTgsInVzZXJuYW1lIjoibG9uZyJ9.CHxl8Vf-1D61pzfngQbh-DR5x6Eyw2G_IwKEOexmr0g";
    // 1: Nmam
    String AUTHENTICATION_1 ="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjkyNDM3ODAsInVzZXJuYW1lIjoiTm1hbSJ9.3TfIK6Se-w5JoznuLFOle-7i3UlDivIQ6Fh332RY7Bw";
    // 2: 1asdasd
    String AUTHENTICATION_2 ="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjkyNDM4MTAsInVzZXJuYW1lIjoiMWFzZGFzZCJ9.1iNK_bL1Vlfk0aBCLD_8d0Co-9Hfl41h36kD8WfAEZ0";
    // 3: duong12348767998
    String AUTHENTICATION_3 = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjkyNDM4MzIsInVzZXJuYW1lIjoiZHVvbmcxMjM0ODc2Nzk5OCJ9.CkPbQyKsC8YGsPOWtA72MKZPMLZmzB4lkra4ot_W1zs";
    // 4: duong123487678
    String AUTHENTICATION_4 = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjkyNDM4NTQsInVzZXJuYW1lIjoiZHVvbmcxMjM0ODc2NzgifQ.2Yq-LrE0qI9zMPXoJ6uSlFB4AuwZhYMk5XaDxo7Dx6E";
    // 5: duong12348778
    String AUTHENTICATION_5 ="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjkyNDM4NzIsInVzZXJuYW1lIjoiZHVvbmcxMjM0ODc3OCJ9.hpj7jun4LaCW2VuU6AZXDtWMLSPZLr0mfnhyNChQkWs";
    // HTTP POST request
    private void sendPost() throws Exception {

        // send value
        ListBookBorrow listBookBorrow = new ListBookBorrow();
        List<BookBrowedModelThread> bookBrowedModelThreads = listBookBorrow.getListBookBorrow();

        String url = "http://localhost:8080/api/user/userBrow";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", USER_AGENT);
        if (index == 0) {
            con.setRequestProperty("Authorization", AUTHENTICATION);
        }
        else if (index == 1)
        {
            con.setRequestProperty("Authorization", AUTHENTICATION_1);
        }
        else if (index == 2)
        {
            con.setRequestProperty("Authorization", AUTHENTICATION_2);
        }
        else if (index == 3)
        {
            con.setRequestProperty("Authorization", AUTHENTICATION_3);
        }
        else if (index == 4)
        {
            con.setRequestProperty("Authorization", AUTHENTICATION_4);
        }
        else
        {
            con.setRequestProperty("Authorization", AUTHENTICATION_5);
        }

        // Send post request
        con.setDoOutput(true);
        con.setDoInput(true);

        Gson gson = new Gson();
        String jsonBookBorrows = gson.toJson(bookBrowedModelThreads);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(jsonBookBorrows.getBytes("UTF-8"));
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " );
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        System.out.println(response.toString());
    }

    @Override
    public void run() {
        try {
            //send request
            sendPost();
            // Bắt đầu xử lý request đến
            System.out.println(Thread.currentThread().getName() + " Starting process " + name);
            // cho ngủ 500 milis để ví dụ là quá trình xử lý mất 0,5 s
            Thread.sleep(500);
            // Kết thúc xử lý request
            System.out.println(Thread.currentThread().getName() + " Finished process " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
