package com.example.fresher_manager;

import com.example.fresher_manager.repository.AreaRepository;
import com.example.fresher_manager.repository.LanguageRepository;
import com.example.fresher_manager.repository.RankRepository;
import com.example.fresher_manager.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.example.fresher_manager"})
public class FresherManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FresherManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AuthService authService, AreaRepository areaRepository, LanguageRepository languageRepository, RankRepository rankRepository){
		return args ->{
//			authService.savedAdmin();
//			areaRepository.save(new Area("Mỹ", "OK"));
//			areaRepository.save(new Area("Nhật Bản", "OK"));
//			areaRepository.save(new Area("Vệt Name", "OK"));
//			languageRepository.save(new Language("Java", "OK"));
//			languageRepository.save(new Language("Python", "OK"));
//			languageRepository.save(new Language(".NET", "OK"));
//			languageRepository.save(new Language("ReactJS", "OK"));
//			languageRepository.save(new Language("NodeJS", "OK"));
//			languageRepository.save(new Language("Tester", "OK"));
//			rankRepository.save(new Rank("Dễ", "Đầu vào"));
//			rankRepository.save(new Rank("Trung Bình", "Làm một thời gian"));
//			rankRepository.save(new Rank("Khó", "Bài cuối"));
		};
	}

}
