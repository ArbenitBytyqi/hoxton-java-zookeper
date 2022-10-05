package com.zookeper.hoxtonjavazookeper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooAnimalController {
    @Autowired
    private ZooAnimalRepository ZooAnimalRepository;

    @GetMapping("/animals")
    public List<Animal> getAllAnimals() {
        return ZooAnimalRepository.findAll();
    }

    @PostMapping("/animals")
    public Animal createNewAnimal(@RequestBody Animal animalData) {
        return ZooAnimalRepository.save(animalData);
    }

    @DeleteMapping("/animals/{id}")
    public String deleteAnimal(@PathVariable Integer id) {
        ZooAnimalRepository.deleteById(id);
        return "ANIMAL SUCCESSFULLY DELETED!";
    }

    @PatchMapping("/animals/{id}")
    public Animal updateAnimal(@RequestBody Animal AnimalsData, @PathVariable Integer id) {
        AnimalsData.id = id;
        return ZooAnimalRepository.save(AnimalsData);
    }
}

@Entity
class Animal {
    @Id
    public Integer id;
    public String name;
    public String species;
    public String origin;
    public boolean isHungry;

    public Animal() {
    }
}

interface ZooAnimalRepository extends JpaRepository<Animal, Integer> {
}
