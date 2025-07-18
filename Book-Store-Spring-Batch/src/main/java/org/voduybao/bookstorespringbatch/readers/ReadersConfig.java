//package org.voduybao.bookstorespringbatch.readers;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.voduybao.tools.dao.entities.user.Education;
//
//@Configuration
//public class ReadersConfig {
//
//    @Value("${import.file.csv}")
//    private String csvFile;
//
//    // Reader
//    @Bean
//    public FlatFileItemReader<Education> readerCsv() {
//        FlatFileItemReader<Education> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource(csvFile));
//        reader.setLinesToSkip(1); // bỏ header
//        reader.setLineMapper(new DefaultLineMapper<>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames("edu_id", "title");
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                setTargetType(Education.class);
//            }});
//        }});
//        return reader;
//    }
//
//    // Writer
//    @Bean
//    public ItemWriter<Education> writerConsole() {
//        return items -> items.forEach(item -> System.out.println("Read item: " + item));
//    }
//
//    // Step (tạo thủ công bằng StepBuilder)
//    @Bean
//    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("education-step", jobRepository)
//                .<Education, Education>chunk(10, transactionManager)
//                .reader(readerCsv())
//                .writer(writerConsole())
//                .build();
//    }
//
//    @Bean
//    public Job job(JobRepository jobRepository, Step step) {
//        return new JobBuilder("education-job", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(step)
//                .build();
//    }
//}
