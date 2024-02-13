package telran.java51.student.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter//Нужны для jackson
@AllArgsConstructor//Так как это Dto у нас из respons-а, а не запроса, мы его будем создавать сами и нам точно приходится конструктор с аргументами
@NoArgsConstructor//Если есть коснтруктор с аргументами, понадобится и без аргументов
public class StudentDto {

	Integer id;
	String name;
	Map <String, Integer> scores;
}
