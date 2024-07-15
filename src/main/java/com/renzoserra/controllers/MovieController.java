package com.renzoserra.controllers;


import com.renzoserra.models.Movie;
import com.renzoserra.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @CrossOrigin
    @GetMapping
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie saveMovie = movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMovie);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable("id") Long id){
        if (!movieRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        movieRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id,@RequestBody Movie updateMovie){
        if (!movieRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updateMovie.setId(id);
        Movie savedMovie = movieRepository.save(updateMovie);
        return ResponseEntity.ok(savedMovie);
    }

    @CrossOrigin
    @GetMapping("/vote/{id}/{rating}")
    public ResponseEntity<Movie> voteMovie (@PathVariable("id") Long id,@PathVariable("rating") double rating){
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (!movieRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Movie movie = optionalMovie.get();
        Double currentRating = movie.getRating();
        if (currentRating == null) {
            currentRating = 0.0;
        }
        int currentVotes = movie.getVotes();
        if (currentVotes == 0) {
            currentVotes = 0;
        }

        double newRating = ((currentVotes * currentRating) + rating) / (currentVotes + 1);
        movie.setVotes(currentVotes + 1);
        movie.setRating(newRating);

        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }


}
