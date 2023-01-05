package recipes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Indexed;

import java.util.List;

@Indexed
public interface UserRepository extends CrudRepository<User, String> {

}