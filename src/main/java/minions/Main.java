package minions;

import java.util.List;

public class Main {
    public static void main(String ... args) {
        new FileReader("input/a_example.in");
        new FileReader("input/b_should_be_easy.in");
        new FileReader("input/c_no_hurry.in");
        new FileReader("input/d_metropolis.in");
        new FileReader("input/e_high_bonus.in");
        System.out.println(FileReader.overallScore);
    }
}
