package com.mantinha.observatoriodepasta.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class MainJob {
	
	private static final String GET_JOB = "job";
	private static final String GET_STEP = "step";
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job(Step step) {
		return jobBuilderFactory.get(GET_JOB)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public Step step(ItemReader reader, ItemWriter writer) {
		return stepBuilderFactory.get(GET_STEP)
				.chunk(1)
				.reader(reader)
				.writer(writer)
				.build();
	}

}
