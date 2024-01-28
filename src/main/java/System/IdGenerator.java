package System;

import java.util.Random;

public interface IdGenerator {
      default String getRandomId(int lower_bound , int upper_bound){
        Random random_no = new Random();
        int randomValue = random_no.nextInt((upper_bound - lower_bound) + 1) + lower_bound;
        return "#" + randomValue;

    }
    boolean isIdExists(String id);
}
