package usn.obj2100.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.*;
import usn.obj2100.model.Plassering;

public class PlasseringRepository
	extends JpaRepository<Plassering, Integer>
{
}
