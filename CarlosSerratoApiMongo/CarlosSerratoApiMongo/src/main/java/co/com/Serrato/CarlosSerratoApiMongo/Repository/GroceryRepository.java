package co.com.Serrato.CarlosSerratoApiMongo.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.com.Serrato.CarlosSerratoApiMongo.Model.GroceryItem; 

@Repository
public interface GroceryRepository extends MongoRepository<GroceryItem, String> { 
}
