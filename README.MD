# Universidad Nacional de La Matanza Departamento de Ingeniería e Investigaciones Tecnológicas
## Tecnicatura Universitaria en Desarrollo de Aplicaciones para Dispositivos Móviles
### Programación Móvil 1
#### TP “Cadena de estacionamientos”
##### Profesores:
- Pardo,Sebastian
-  Bayarri,Brian
- Hoz,Aylen
- Gonzalez,Diego
- Uran, Jonatan

##### Descripción:

Una empresa Fintech llamada “Orange” quiere implementar un sistema de compra de criptomonedas. En esta primera versión, solo se podrá comprar Bitcoins.
Orange opera con 3 exchanges (intermediarios) con los cuales el usuario puede realizar la compra de las criptomonedas donde cada uno tiene sus propias reglas de negocio:

| **EXCHANGE** | **COMISION**                                                                                                          | **EJEMPLO**                                                                                                                                                                                                                                     |
|--------------|-----------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Criptomas    | Aplica una comisión del 2%                                                                                            | <ul><li>Si compro Bitcoins por $100, el monto total a pagar es de $102</li></ul>                                                                                                                                                                |
| Criptodia    | Si la compra se realiza entre las 20:00 hs y las 23:59 hs aplica una comisión del 1%, sino aplica una comisión del 3% | <ul><li>Si compro Bitcoins por $100 entre las 20:00 hs y las 23:59 hs, el monto total a pagar es de $101</li><li>Si compro Bitcoins por $100 en un horario por fuera de las 20:00 hs y las 23:59 hs, el monto total a pagar es de $103</li><ul> |
| Carrecripto  | Si la compra se realiza un sábado o domingo se aplica una comisión del 3%, sino aplica una comisión del 0.75%         | <ul><li>Si compro Bitcoins por $100 un sábado o domingo, el monto total a pagar es de $103</li><li>Si compro Bitcoins por $100 un día de semana, el monto total a pagar es de $100,75</li></ul>                                                 |

A su vez, como estrategia para atraer usuarios, Orange ofrece los siguientes beneficios:

| **BENEFICIO**                                                                                                              | **EJEMPLO**                                                                                                                                                       |
|----------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Si el usuario tiene 3 meses o menos de registro se le da un cashback del 5% en el total de la compra                       | Si el usuario compro Bitcoins por $100 a través de Criptomas, el monto total a pagar es de $102. Por el beneficio mencionado, Orange le dará un cashback de $5,10 |
| Si el usuario tiene más de 3 meses, pero 12 meses o menos de registro se le da un cashback del 3% en el total de la compra | Si el usuario compro Bitcoins por $100 a través de Criptomas, el monto total a pagar es de $102. Por el beneficio mencionado, Orange le dará un cashback de $3,06 |
| Si el usuario tiene más de 12 meses de registro no se le dará cashback                                                     ||

El objetivo de usted como desarrollador es permitirle al usuario comprar criptomonedas teniendo en cuenta lo siguiente:
- Calcular correctamente el monto a pagar aplicando comisiones segun corresponda
- Otorgar cashback en caso de que aplique
- Validar si el saldo en la cuenta del usuario es suficiente para realizar la compra
- Implementar un login mediante nickname y password

Por otro lado y como ultima funcionalidad, el usuario tiene que ser capaz de ver su historial de compras.
Los datos de un usuario se almacenan de la siguiente forma:

````json
{
  "nickname": String,
  "password": String,
  "nombre": String,
  "apellido": String,
  "dinero_en_cuenta": Double,
  "fecha_de_alta": String (formato "yyyy-MM-dd")
}
````

Los datos de una compra se almacenan de la siguiente forma:

````json
{
  "codigo_compra": Int,
  "fecha_compra": String (formato "yyyy-MM-dd"), 
  "hora_compra": String (formato "hh:mm"),
  "criptomenda": String,
  "valor_adquirido": Double (en criptomoneda), 
  "valor_pagado": Double (en pesos)
}
````

Notas:
- No necesita implementar una interfaz gráfica para este ejercicio.
- No necesita hacer uso de una base de datos para este ejercicio.
- Todas las reglas que la compañía usa para calcular el costo total fueron dadas
  anteriormente. No necesita aplicar ninguna otra regla.
  Requisitos:
- Hacer uso de dos o más tipos de clases diferentes.
- Hacer uso de herencia y polimorfismo.
- Hacer uso de funciones lambda y/o funciones acotadas.
- Crear y hacer uso de al menos una excepción.
- Construir un menú estático que le permita a un usuario realizar la operación que desea.