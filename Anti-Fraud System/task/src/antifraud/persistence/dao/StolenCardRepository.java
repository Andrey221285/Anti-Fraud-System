package antifraud.persistence.dao;

import antifraud.persistence.model.StolenCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StolenCardRepository extends JpaRepository<StolenCard, Long> {

    StolenCard findByNumber(String number);
}
