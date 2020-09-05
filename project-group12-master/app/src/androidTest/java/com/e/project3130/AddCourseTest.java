package com.e.project3130;

import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.assertEquals;

public class AddCourseTest {

    @Test
    public void timeConflictCheck()throws IOException {
        URL url = new URL("https://web.cs.dal.ca/~ziwei/api/?email=zw281905@dal.ca&term=2019Fall&date=FM&time=1:00-4:00");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String jsonData = "";
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            jsonData = jsonData + line;
        }
        bufferedReader.close();
        isr.close();
        is.close();

        String status = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            status = jsonObject.getString("status");
        }catch (Exception e){
            System.out.print(e.toString());
        }
        assertEquals("Error", status);

    }

}
