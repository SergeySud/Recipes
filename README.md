# recipes_project
The project is in recipes_project/src/main/java/

A multi-user web service with Spring Boot that allows storing, retrieving, updating, and deleting recipes in H2 database.
 
Users can register at POST `/api/register` endpoint. 

POST `/api/recipe/new` allows to add new recipes.

GET `/api/recipe/*{id}*` allows to find recipes by id.

DELETE `/api/recipe/*{id}*` allows to delete recipes.

PUT `/api/recipe/*{id}*` allows to overwrite recipe.

GET `/api/recipe/search` allows to search recipes by name or category.
