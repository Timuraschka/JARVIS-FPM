package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import edu.fra.uas.model.Card;

public interface CardRepository extends JpaRepository<Card, Card> {

}
