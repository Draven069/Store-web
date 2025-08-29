#language: es
@testfeature

Característica: Product - Store
Yo, como usuario
Quiero, tener una opción para comprar productos en la tienda online
Para poder adquirir productos de diferentes categorías

  @test
  Escenario: Validación del precio de un producto con credenciales válidas
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "gaxivi2183@cavoyar.com" y clave "Lj19q1£_a"
    Cuando navego a la categoria "Clothes" y subcategoria "Men"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

  @test
  Escenario: Validación con categoría inexistente
  Dado estoy en la página de la tienda
  Y me logueo con mi usuario "gaxivi2183@cavoyar.com" y clave "Lj19q1£_a"
  Cuando navego a la categoria "Autos" y subcategoria "Deportivos"
  Entonces valido que no se encuentre la categoría solicitada

  @test
  Escenario: Validación con credenciales inválidas
  Dado estoy en la página de la tienda
  Y me logueo con mi usuario "usuario_invalido@test.com" y clave "Pablito"
  Entonces valido que se muestre el mensaje de error de login