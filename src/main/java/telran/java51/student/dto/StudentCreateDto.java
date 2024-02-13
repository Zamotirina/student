package telran.java51.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter//Проставляем геттеры для jackson, дефолтный конструктор есть и так
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateDto {
	
	
	Integer id;
	String name;
	String password;

}
