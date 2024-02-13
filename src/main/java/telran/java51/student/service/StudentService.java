package telran.java51.student.service;

import java.util.List;
import java.util.Set;

import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;

/*
 * Этот интерфейс у нас связывает Controller и веб-сервис,
 * Соответственно в нем должны быть те же методы, что есть в контроллере и в Сollection Postman-а
 * Поэтому чтобы их написать, мы просто берем весь список методов из Collection Postman-а и здесь прописываем
 * Переменны в сигнатуры, мы тожем берет из Collection
 */
public interface StudentService {

	Boolean addStudent (StudentCreateDto studentCreateDto);
	StudentDto findStudent(Integer id);
	StudentDto removeStudent (Integer id);
	StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);
	Boolean addScore(Integer id, ScoreDto scoreDto);
	List <StudentDto> findStudentsByNameDtos (String name);
	Long getStudentsNamesQuantity(Set <String> names);
	
	List <StudentDto> getStudentsByExamMinScore (String exam, Integer minScore);

}
