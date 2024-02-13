package telran.java51.student.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;
import telran.java51.student.service.StudentService;

@RestController//Обязательная аннотация, чтобы система поняла, что это именно Controller
@RequiredArgsConstructor//Добавили в действии 6, чтобы заменить аннотаицю Autowired по аналогии с сервисом
public class StudentController {
	
//	@Autowired //Аннотация для связи между классом веб-сервиса и контроллером
	final StudentService studentService; //Это поле с переменной типа интерфейс, то есть ссылка на объект интерфейса, за счет которой у нас и осуществляется связь между контроллером и веб-сервисом

	/*
	 * Важно: чтобы методы контроллера вызывались, они должны быть НЕстатическими
	 */

	@PostMapping("/student")
	public Boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
		
		return studentService.addStudent(studentCreateDto);
	}
	
	/*
	 * Чтобы написать тело метода, мы тупо берем поле-ссылку и пишем одноименный метод интерфейса. 
	 */
	
	/*
	 * В методах ниже мы используем переменную пути, то есть параметр, который добавится к url. Это один из двух стилей. Ранее Эдуард нам показывал аннотацию @PathParam, то есть параметр
	 * Здесь мы уже прибавляем кусок к url и для этого используем аннотацию @PathVariable
	 * 
	 * Мы это делаем в двух варианциях, когда название в фигурных скобках и название переменной совпадают, тогда ничего не пишем. Если не совпадают, то прописываем название в скобках
	 */

	@GetMapping("/student/{studentId}")
	public StudentDto findStudent(@PathVariable("studentId") Integer id) {
		
		return studentService.findStudent(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentDto removeStudent(@PathVariable Integer id) {
		return studentService.removeStudent(id);
	}

	@PutMapping("/student/{id}")
	public StudentCreateDto updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
	
		return studentService.updateStudent(id, studentUpdateDto);
	}

	@PutMapping("/score/student/{id}")
	public Boolean addScore(@PathVariable Integer id,  @RequestBody ScoreDto scoreDto) {
		
		return studentService.addScore(id, scoreDto);
	}

	@GetMapping("/students/name/{nameStudent}")
	public List<StudentDto> findStudentsByNameDtos(@PathVariable("nameStudent") String name) {
	
		return studentService.findStudentsByNameDtos(name);
	}

	@PostMapping("/quantity/students")
	public Long getStudentsNamesQuantity(@RequestBody Set<String> names) {
		
		return studentService.getStudentsNamesQuantity(names);
	}

	@GetMapping("/students/exam/{exam}/minscore/{minScore}")
	public List<StudentDto> getStudentsByExamMinScore(@PathVariable String exam, @PathVariable Integer minScore) {
		
		return studentService.getStudentsByExamMinScore(exam,minScore);
	}

}
