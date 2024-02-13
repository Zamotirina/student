package telran.java51.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;

/*
 * Мы создали этот класс для ModelMapper и всяких конфигурационных штук во всем проекте
 * 
 * Когда мы создаем классы, самое важное, чтобы они находились в иерархии ниже, чем telran.java51 и файл StudentServiceApplication
 */

@Configuration //Вставили после @Bean
public class ServiceConfiguration {


/*
 * 1. Пишем метод, который просто возвращает новый ModelMapper
 * 
 * Ставим над ним аннотацию @Bean, то есть теперь ModelMapper у нас в апликационном контексте
 * 
 * Но одна аннотация @Bean не прокатывает, потому что Spring ее не находит
 * 
 * При старте аппликации Spring сканирует проект и классы и читает аннотации над ними. 
 * 
 * Над нашим классом сейчас аннотации нет, он его пропускает
 *
 * Чтобы это исправить, добавляем над классом аннотацию @Configuration. Вместо нее еще можно опять же использовать аннотацию @Component
 */
	
	
	/*
	 * 3. Мы начинаем конфигурировать ModelMapper, чтобы избавиться от сеттера для поля id
	 */
//	@Bean
//	public ModelMapper getModelMapper() {
//		
//		return new ModelMapper();
//	}
	
	@Bean
	public ModelMapper getModelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration()
		.setFieldMatchingEnabled(true)//Этим мы разрешаем сравнивать названия полей
		.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)//Этим мы даем доступ к приватным полям
		.setMatchingStrategy(MatchingStrategies.STRICT);//Этим мы указываем стратегию слечения полей. Она бывает Strict, Loose и Standart
		//Поля у нас должны быть в camelCase. Система делит их на элементы и сравнивает. При станадартной будут одинаковыми поля firstName и nameFirst. При Strict только строгое соответствие, а при loose, если хотя бы один элемент совпадает
		
		
		return modelMapper;
	}
	
}
