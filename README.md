# recipes

A multi-user web service with Spring Boot that allows storing, retrieving, updating, and deleting recipes in H2 database.
 
Users can register at POST `/api/register` endpoint. 

POST `/api/recipe/new` allows adding new recipes.

GET `/api/recipe/*{id}*` allows finding recipes by id.

DELETE `/api/recipe/*{id}*` allows to delete recipes.

PUT `/api/recipe/*{id}*` allows to overwrite recipe.

GET `/api/recipe/search` allows searching recipes by name or category.
