package com.example.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestRedisDTO implements Serializable {
    private static final long serialVersionUID = 4936046840267971799L;

    private  String id;
    private String name;
    private int age;

}
