package com.movieinfo.MovieApp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.movieinfo.MovieApp.pojo.MovieInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class MovieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAppApplication.class, args);
	}

	@GetMapping("/testOmdB")
	public Object getMovie() {
		Gson g = new Gson();
		String url = "http://www.omdbapi.com/?t=game+of+thrones&apikey=3fe95daa";
		RestTemplate restTemplate = new RestTemplate();
		Object info = restTemplate.getForObject(url, Object.class);
		String json = g.toJson(info);
		MovieInfo movieInfo = g.fromJson(json, MovieInfo.class);
		System.out.println("\n" + movieInfo + "\n");
		return info;
	}
}
