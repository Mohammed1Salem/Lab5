package com.examplet.lab5q2tracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tracker {
    private String id;
    private String title;
    private String desc;
    private String status;
    private String companyName;
}
