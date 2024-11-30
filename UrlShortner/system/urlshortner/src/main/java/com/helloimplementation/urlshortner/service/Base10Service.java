package com.helloimplementation.urlshortner.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class Base10Service {
    Set<Integer> seen;
    Random rng;
    public Base10Service(){
        this.seen=new HashSet<>();
        this.rng= new Random();
    }
    public int getCurrentNumber(){
        int next;
        // Loop until a unique, positive number is found
        do {
            next = rng.nextInt(Integer.MAX_VALUE); // Ensure positive numbers
        } while (seen.contains(next)); // Keep generating until it's unique

        seen.add(next);
        return next;
    }
}
