package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.model.Timetracker;

public interface TimetrackerRepository extends JpaRepository<Timetracker, Timetracker> {

}
