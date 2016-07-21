This project is based on MVC-pattern. 
It consists of 3 layers: Model, View and Controller.
        Model. 
		  It is responsible for business-logic and database-interaction. 
		  Model consists of two layers: services and persistance. 
		     Services. 
			    Contains business logic.
             Persistance.
			    Communicates with database. 
				It has three packages: Entity, JpaRepository, Dao.
			    Entity. 
				   These dto classes are mapped to database tables. There are four main objects: person, product, order, cartitem.
                   Cartitem - is a class, containig product id and its amount in user's cart.
				JpaRepository.
				   Extended Spring's interfaces JpaRepository.
                   They already have crud operations realised. 
				Dao.
				   Provides interaction between Services layer and JpaRepository.
	    Controller. 
		  Delegates user's requests to Model.
		View.
		  Jsp pages.


 

