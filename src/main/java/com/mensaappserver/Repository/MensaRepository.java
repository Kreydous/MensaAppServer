package com.mensaappserver.Repository;

import com.mensaappserver.Models.Mensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensaRepository extends JpaRepository<Mensa,Integer> {
    public Mensa findById(int id);
    public Mensa findByNameContaining(String name);
}
