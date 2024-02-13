package telran.java51.student.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.student.dao.StudentRepository;
import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;
import telran.java51.student.dto.exceptions.StudentNotFoundException;
import telran.java51.student.model.Student;


@RequiredArgsConstructor
@Service 
public class StudentServiceImpl implements StudentService {
	
	
	final StudentRepository studentRepository;
	final ModelMapper modelMapper; //Добавили после создания класса ServiceConfiguration
	
	//Это поле попадает в конструктор, дальше будет autowire и мы получим нужный результат


	/*
	 * 2. Теперь мы переписываем методы, используя ModelMapper
	 */
	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
		
		if(studentRepository.existsById(studentCreateDto.getId())) {
		
		return false;
	}
		
		//Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(), studentCreateDto.getPassword());
		
		/*
		 * Мы переписываем код, но он не сработает, потому что классу Student нужен NoArgsConstructor, раньше его не было
		 * 
		 * Но и когда мы добавили конструктор, проблемы не ушли. У насдобавился новый студент, но вместо указанного id у него стоит ноль, а мапа с оценками в принципе не добавилась
		 * 
		 * Причина - в том, что ModelMapper создает объекты через дефолтный конструктор, а заполняет поля через сеттеры. У класса Student сеттеры у нас были только у полей имя и пароль. Мапа с оценками тоже не добавилась, потому что после дефолтного конструктора она соответствует null, а для MongoDB null - это ничего
		 * 
		 * Мы идем в класс Student, добавляем там сеттеры к поля и добавляем new HashMap() к полю с оценками
		 * 
		 * После этого все срабатывает корректо и ноый студент добавляется. 
		 * 
		 * Но появляется новая проблема: мы добавили сеттер к полю id, где по логике вещей его не должно быть
		 * 
		 * Поэтому мы идем в класс ServiceConfiguration и начинаем конфигурировать ModelMapper
		 */
		
		Student student = modelMapper.map(studentCreateDto, Student.class);
		
		studentRepository.save(student);
		
		return true;
	}


	@Override
	public StudentDto findStudent(Integer id) {
		
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		
		//return new StudentDto(student.getId(), student.getName(), student.getScores());
		
		return modelMapper.map(student, StudentDto.class);
	}


	@Override
	public StudentDto removeStudent(Integer id) {
		
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

		studentRepository.deleteById(id);
		
		//return new StudentDto(student.getId(), student.getName(), student.getScores());
		
		return modelMapper.map(student, StudentDto.class);
	}
	


	@Override
	public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {


		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		
		student.setName(studentUpdateDto.getName());
		student.setPassword(studentUpdateDto.getPassword());
		

	student =studentRepository.save(student);
	
	//return new StudentCreateDto(student.getId(),studentUpdateDto.getName(), studentUpdateDto.getPassword());

	return modelMapper.map(student, StudentCreateDto.class);
		
	}
	


	@Override
	public Boolean addScore(Integer id, ScoreDto scoreDto) {
		
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
	
		boolean res=student.addScore(scoreDto.getExamName(),scoreDto.getScore());
		student=studentRepository.save(student);
		return res;
	
	}
	

	@Override
	public List<StudentDto> findStudentsByNameDtos(String name) {
		
		Predicate <Student> predicate = student -> student.getName().equalsIgnoreCase(name);
		
		return studentRepository.findAllByNameIgnoreCase(name)
				//.map(x-> new StudentDto(x.getId(),x.getName(),x.getScores()))
				.map(x-> modelMapper.map(x,StudentDto.class))
				.toList();
		
	}
	

	@Override
	
	public Long getStudentsNamesQuantity(Set<String> names) {
		
	
		return studentRepository.countByNameInIgnoreCase(names);
	}


	@Override
	public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
	
		return studentRepository.findByExamAndScoreGreaterThan(exam,minScore)
				//.map(x-> new StudentDto(x.getId(),x.getName(),x.getScores()))
				.map(x-> modelMapper.map(x,StudentDto.class))
				.toList();

	}

}
