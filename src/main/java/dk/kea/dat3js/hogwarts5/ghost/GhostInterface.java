package dk.kea.dat3js.hogwarts5.ghost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GhostInterface extends JpaRepository<Ghost, Integer>{
    Ghost findByName(String name);
}
