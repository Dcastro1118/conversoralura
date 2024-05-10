Conversor Alura

Bienvenidos a mi proyecto para el programa de Oracle + Alura: ONE, es un gusto para mi ser parte de este programa.
Antes de comenzar me presento mi nombre es David Castro Carvajal de Costa Rica, sin mas que decir empecemos!

Diagrama de clases:

![Diagrama de clases __ ConversorAlura](https://github.com/Dcastro1118/conversoralura/assets/135305349/f57999f1-1789-4c47-b4b7-5c0db806a246)


Manual de usuario:

La primer pantalla que se nos muestra es un menu de opciones, este menu de opciones esta conformado por 5 opciones:

1.Consultar tipo de cambio: En esta opcion podremos saber cual es el tipo de cambio actual entre dos monedas.

2.Convertir un monto de una moneda a otra: En esta opcion podemos saber cual seria la conversion de un monto en especifico de una moneda a otra.

3.Consultar los codigos de monedas: En esta seccion podemos consultar cuales son las monedas disponibles

4.Historial de conversiones: Aqui podemos ver un historial de las conversiones que hemos realizado previamente

5.Salir: con esta opcion cerramos el programa

Una vez decidimos cual es la accion que vamos a realizar debemos tener cuidado al ingresar los datos, en los codigos de las monedas no deben estar en mayusculas, pero si es importante que este el codigo completo, los 3 digitos.
Para poder revisar nuestro historial debemos haber realizado por lo menos una conversion si no el historial se nos devolvera "Historial vacio".

Logre realizar una funcion que consulte al Api los "supported_codes" para las monedas asi que cualquier moneda disponible en el API esta disponible en nuestro sistema!

Trate de manejar las excepciones lo mejor posible, como las del HttpRequest, las NumberFormatExepcion y las InputMismatchException
