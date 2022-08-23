package antifraud.persistence.dao;

import antifraud.persistence.model.SuspiciousIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuspiciousIpRepository extends JpaRepository<SuspiciousIp, Long> {

    SuspiciousIp findByIp(String ip);


}
