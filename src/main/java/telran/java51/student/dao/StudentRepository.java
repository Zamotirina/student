package telran.java51.student.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.model.Student;


public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	
	Stream <Student> findBy();
	Stream <Student> findAllByNameIgnoreCase(String name);
	long countByNameInIgnoreCase(Set<String> names);
	@Query("{'scores.?0': {$gte: ?1}}")// Эта аннотация означает, что мы ищем всех студентов с оценкой выше указанной. ?0 и ?1 - это индексы аргументов из метода. Название экзамена - это нулевой аргумент. Оценка - первый аргумент. Доступ к вложенному объекту - через точку 
	Stream <Student> findByExamAndScoreGreaterThan(String exam, int score);
}
