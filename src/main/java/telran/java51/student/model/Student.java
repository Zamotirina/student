package telran.java51.student.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
@Document(collection ="students")


/*
 * 3. Добавляем в этот класс @NoArgsConstructor
 * 
 * Добавляем сеттеры ко всем полям
 * 
 * Прокидываем new HashMap<>(); в поле
 * 
 * После этого новый студент добавляется корректно
 */
public class Student {
	
	/*
	 * Важно: если мы не называем поле id, то нам ним надо поставить аннотацию @Id, чтобы MongoDB так его считал. Иначе будут проблемы
	 */
	
  //  @Setter
	@Id //На шаге 3 мы заменили аннотацию @Setter на @Id, когда конфигурировали наш ModelMapper. То есть теперь система сличает у нас названия полей у Student и StudentDto и находят у них соответствия. Поэтому мы можем обойтись без сеттера у этого поля
	int id;
	@Setter
	String name;
	@Setter
	String password;
	Map <String, Integer> scores;
	//Map <String, Integer> scores = new HashMap<>();;
	

	public Student(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	//	this.scores = new HashMap<>();
	}
	
	
	
	public boolean addScore(String exam, int score) {
		
		return scores.put(exam, score) == null;
		
		
	}

}
