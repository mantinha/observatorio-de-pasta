package com.mantinha.observatoriodepasta.job;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mantinha.observatoriodepasta.model.Person;

@Configuration
public class Steps {
	
	private static final String READER = "reader";
	private static final String RESOURCE = "file.txt";
	
	@StepScope
	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>()
				.name(READER)
				.resource(new ClassPathResource(RESOURCE))
				.fixedLength()
				.columns(new Range[] {new Range(1,3), new Range(4,6)})
				.names(new String[] {"name", "lastName"})
				.saveState(false)
				.targetType(Person.class)
				.build();
	}
	
	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.dataSource(dataSource)
				.sql("INSERT INTO person (name, lastName) VALUES(?, ?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter())
				.build();
	}

	private ItemPreparedStatementSetter<Person> itemPreparedStatementSetter() {
		return new ItemPreparedStatementSetter<Person>() {
			@Override
			public void setValues(Person item, PreparedStatement ps) throws SQLException {
				ps.setString(1, item.getName());
				ps.setString(2, item.getLastName());
			}			
		};
	}

}
