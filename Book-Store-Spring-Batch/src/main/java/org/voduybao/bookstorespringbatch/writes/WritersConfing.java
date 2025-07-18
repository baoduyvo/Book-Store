//package org.voduybao.bookstorespringbatch.writes;
//
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
//import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.FileSystemResource;
//import org.voduybao.tools.dao.entities.user.Education;
//
//public class WritersConfing {
//
//    @Value("${export.file.csv}")
//    private String csvFile;
//
//    @Bean
//    public ItemWriter<Education> csvItemWriter()
//    {
//        FlatFileItemWriter<Education> csvWriter = new FlatFileItemWriter<Education>();
//        csvWriter.setResource(new FileSystemResource(csvFile));
//        csvWriter.setShouldDeleteIfExists(true);
//
//        DelimitedLineAggregator<Education> lineAgg = new DelimitedLineAggregator<Education>();
//        lineAgg.setDelimiter(",");
//        BeanWrapperFieldExtractor<Education> extractor = new BeanWrapperFieldExtractor<Education>();
//        extractor.setNames(new String[] { "edu_id", "title" });
//        lineAgg.setFieldExtractor(extractor);
//        csvWriter.setLineAggregator(lineAgg);
//        return csvWriter;
//    }
//}
