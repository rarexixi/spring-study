package org.xi.ehcache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[] {2,3,2};

        System.out.println(Arrays.stream(array).max((item1, item2) -> (item1 > item2) ? 1 : -1).get());
    }
}
