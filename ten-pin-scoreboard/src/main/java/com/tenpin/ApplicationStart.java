package com.tenpin;

import com.tenpin.model.Frame;
import com.tenpin.model.FramesFactory;
import com.tenpin.model.Roll;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;

@SpringBootApplication
public class ApplicationStart implements CommandLineRunner {

    private final List<Roll> rolls;
    private final FramesFactory factory;

    public ApplicationStart(List<Roll> rolls, FramesFactory factory) {
        this.rolls = rolls;
        this.factory = factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

    @Override
    public void run(String... args){
        Collection<List<Roll>> players = rolls.stream().collect(collectingAndThen(groupingBy(Roll::getName), Map::values));
        System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        for (List<Roll> player : players) {
            System.out.println(player.get(0).getName());
            List<Frame> frames = factory.build(player);
            System.out.println("Pinfalls" + frames.stream().findFirst().get().mark());;
            System.out.print("Score");
            Integer sum = 0;
            for (Frame frame: frames) {
                Integer value = frame.score();
                if (value == null){
                    break;
                }else {
                    sum += frame.score();
                    System.out.print("\t\t"+ sum);
                }
            }
            System.out.println();
        }


    }

}
