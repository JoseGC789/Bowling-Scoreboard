package com.tenpin;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tenpin.model.Scoreboard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Configuration
public class TenPinScoreboardConfig {

    @Value("${scoreboard.path}")
    private String path;

    @Bean
    public List<Scoreboard> deserialize() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Scoreboard.class)
                .withColumnSeparator('\t')
                .withLineSeparator("\n");
        List<Scoreboard> scoreboard = new ArrayList<>();
        Iterator<Scoreboard> result = mapper.readerFor(Scoreboard.class)
                .with(schema)
                .readValues(new FileInputStream(path));
        result.forEachRemaining(scoreboard::add);
        validate(scoreboard);
        return scoreboard;
    }

    private void validate(List<Scoreboard> scoreboard) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Scoreboard>> violations = new HashSet<>();
        for (Scoreboard score: scoreboard) {
            violations.addAll(validator.validate(score));

        }
        violations.stream().findFirst().ifPresent(violation -> {
            String message = String.format((violation.getMessage()), violation.getRootBean());
            throw new ValidationException(message);
        });
    }
}
