package com.josegc789;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public List<ApplicationStart.Score> readGames() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ApplicationStart.Score.class)
                .withHeader()
                .withColumnSeparator('\t')
                .withLineSeparator("\n");
        List<ApplicationStart.Score> scores = new ArrayList<>();
        Iterator<ApplicationStart.Score> result = mapper.readerFor(ApplicationStart.Score.class)
                .with(schema)
                .readValues(getClass()
                        .getClassLoader()
                        .getResourceAsStream("game.txt")
                );
        result.forEachRemaining(scores::add);
        return scores;
    }
}
