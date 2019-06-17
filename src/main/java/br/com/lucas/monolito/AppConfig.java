package br.com.lucas.monolito;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import br.com.lucas.monolito.batch.ClienteProcessor;
import br.com.lucas.monolito.model.Cliente;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class AppConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;

    @Bean
    public FlatFileItemReader<Cliente> csvClienteReader() {
        FlatFileItemReader<Cliente> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("monolito_2019_01_06.csv"));
        reader.setLineMapper(new DefaultLineMapper<Cliente>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setDelimiter(";");

                        setNames(new String[]{
                                "nome",
                                "sobrenome",
                                "dtnascimento"

                        });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Cliente>() {
                    {
                        setTargetType(Cliente.class);
                    }
                });
            }
        });
        return reader;
    }


    @Bean
    ItemProcessor<Cliente, Cliente> csvClienteProcessor() {
        return new ClienteProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Cliente> csvClienteWriter() {
        JdbcBatchItemWriter<Cliente> excelAnimeWriter = new JdbcBatchItemWriter<Cliente>();
        excelAnimeWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Cliente>());
        excelAnimeWriter.setSql("INSERT INTO clientes (nome, sobrenome, dtnascimento, idade) VALUES (:nome, :sobrenome, :dtnascimento, :idade)");
        excelAnimeWriter.setDataSource(dataSource);
        return excelAnimeWriter;
    }


    @Bean
    public Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<Cliente, Cliente>chunk(10)
                .reader(csvClienteReader())
                .writer(csvClienteWriter())
                .processor(csvClienteProcessor())
                .faultTolerant()
                .skipLimit(1)
                .skip(RuntimeException.class)
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob() {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }
}
