package org.java.db.repo;

import org.java.db.pojo.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Integer> {
	
}
