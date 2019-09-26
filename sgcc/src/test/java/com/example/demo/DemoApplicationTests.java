package com.example.demo;


import com.example.Utils;

import java.util.*;

public class DemoApplicationTests {


    public static void main(String[] args) {
        java.sql.Date date_sql = new java.sql.Date(System.currentTimeMillis());
        java.util.Date date = new java.util.Date(date_sql.getTime());
        String tiem = Utils.GetCurrentTime();
    }


}
