# API_REST_SOFTLOND
Api rest de un supermercado - softlond.
La consigna es la siguiente: 

En este supermercado te comunicas con un asesor, haces tu compra y el se encarga de
registrarla por ti.
Si es primera vez que vas a comprar el asesor te pedirá algunos datos para guardarte como
cliente y que
puedas acceder a diferentes ofertas. Los clientes siempre deben ser inscritos en su primera
compra.

1- El sistema debe permitir gestionar las categorias de productos → CRUD
2- El sistema debe permitir gestionar los productos → CRUD
3- El sistema debe permitir gestionar los clientes → CRUD
4- Implementar registro de ventas de tal manera que:
- Puedas consultar la lista de ventas por fecha
- Puedas consultar la lista de ventas por cliente
- Puedas consultar la lista de ventas por cliente y rango de fecha
- Las consultas que determines necesarias para cumplir con los requerimientos
5- Si un cliente ha hecho compras mayores a un millón de pesos en un plazo de 31 días
anteriores a la
fecha de la compra actual se le aplicará un descuento de un porcentaje que es definido por
la
compañía(supermercado) y que puede ser cambiado en cualquier momento.
6- Al momento de cerrar la compra se ejecutará automáticamente un juego de 3 opciones
que definirán si al cliente se le aplica un descuento sobre el total de la compra.
Para estos sorteos la empresa también definirá un porcentaje de descuento que podrá
cambiar en
cualquier momento. Las 3 opciones son:
- El descuento definido por la empresa
- Un nuevo intento
- La opción que indica que no ganaste
Nota: Máximo se pueden dar tres intentos


Tuve que implementar las capas mas comunes, el entity, repository, service y controller. Tuve que hacer uso de las relaciones valiendome de Jpa. Decidk implementar un manejo de excepciones para controlar mejor los metodos http. Ahora he implementado un poco de seguridad pero de forma basica, es la primera vex que lo implemento pero aun falta terminar esta parte de seguridad.
