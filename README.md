# GenericECommerce
Generic ECommerce portal using Java and Angular/Ionic

## Registro de usuarios

Se agregó el endpoint POST `/register` para crear un nuevo usuario en SQLite.

- URL base por defecto: `http://localhost:8080/register`
- Body (JSON):
```
{
	"username": "juan",
	"email": "juan@example.com",
	"password": "secreta123"
}
```
- Respuesta 201 (application/json):
```
{
	"id": 1,
	"username": "juan",
	"email": "juan@example.com"
}
```
- Errores 409: "El nombre de usuario ya existe" o "El email ya está registrado".

La base SQLite se crea en `${user.home}/GenericECommerce/data/ecommerce.db`. Puedes cambiar la ruta en `src/main/resources/application.properties`.
