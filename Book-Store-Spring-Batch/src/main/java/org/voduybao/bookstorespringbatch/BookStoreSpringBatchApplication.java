package org.voduybao.bookstorespringbatch;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.voduybao.tools.dao.entities.location.Commune;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookStoreSpringBatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreSpringBatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> csvFiles = Arrays.asList(
                "location/commune1.csv",
                "location/commune2.csv",
                "location/commune3.csv",
                "location/commune4.csv",
                "location/commune5.csv",
                "location/commune6.csv",
                "location/commune7.csv",
                "location/commune8.csv",
                "location/commune9.csv",
                "location/commune10.csv",
                "location/commune11.csv"
        );

        for (String csvFile : csvFiles) {
            System.out.println("🔍 Reading file: " + csvFile);

            FlatFileItemReader<Commune> reader = new FlatFileItemReader<>();
            reader.setResource(new ClassPathResource(csvFile));
            reader.setLinesToSkip(1);

            reader.setLineMapper(new DefaultLineMapper<>() {{
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setNames("id", "province_id", "district_id", "code", "name", "slug", "level");
                }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Commune.class);
                }});
            }});

            reader.open(new ExecutionContext());

            Commune commune;
            while ((commune = reader.read()) != null) {
                System.out.println("✅ Read from " + csvFile + ": " + commune);
            }

            reader.close();
        }
    }
}
