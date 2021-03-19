package com.learn.lombok;

import lombok.Builder;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Builder
public class BuilderExample {
    @Builder.Default
    private long created = System.currentTimeMillis();
    private String name;
    private int age;
    @Singular
    private Set<String> occupations;

    public static void main(String[] args) {
        Set<String> occupations1 = new HashSet<>();
        occupations1.add("1");
        occupations1.add("2");
        BuilderExample be1 = BuilderExample.builder()
                .age(15)
                .name("Tom")
                .build();

        Set<String> occupations2 = new HashSet<>();
        occupations2.add("3");
        occupations2.add("4");
        BuilderExample be2 = BuilderExample.builder()
                .age(18)
                .name("King")
                .occupation("1")
                .occupation("2")
                .build();
        System.out.println(be1);
        System.out.println(be2);

    }
}
