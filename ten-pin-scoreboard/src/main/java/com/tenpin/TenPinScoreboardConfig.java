package com.tenpin;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tenpin.model.Roll;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
public class TenPinScoreboardConfig {

    @Value("${game.path}")
    private String path;

    @Bean
    public List<Roll> deserialize() throws IOException {
        try (InputStream file = new FileInputStream(path)){
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(Roll.class)
                    .withColumnSeparator('\t')
                    .withLineSeparator("\n");
            Iterator<Roll> result = mapper.readerFor(Roll.class)
                    .with(schema)
                    .readValues(file);
            Iterable<Roll> games = () -> result;
            List<Roll> rolls = StreamSupport.stream(games.spliterator(),false).collect(Collectors.toList());
            validate(rolls);
            return rolls;
        }
    }

    private void validate(List<Roll> rolls) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Roll>> violations = new HashSet<>();
        for (Roll roll: rolls) {
            violations.addAll(validator.validate(roll));

        }
        violations.stream().findFirst().ifPresent(violation -> {
            String message = String.format((violation.getMessage()), violation.getRootBean());
            throw new ValidationException(message);
        });
    }
}
