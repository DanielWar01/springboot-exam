# API de Gestión de Ventas

**Desarrollador**: Daniel Alejandro Guerra Muñoz

## Descripción

Esta API proporciona un conjunto de endpoints para gestionar ventas, productos y clientes. Fue diseñada para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre estas entidades. Incluye controladores específicos para cada recurso y utiliza Swagger para documentar y probar los endpoints.

---

## Controladores

### **Sale Controller**
Permite la gestión de ventas. A continuación, se describen los endpoints disponibles:

- **GET** `/api/sales/{id}`  
  Obtiene una venta específica por su ID.

- **PUT** `/api/sales/{id}`  
  Actualiza una venta existente.

- **DELETE** `/api/sales/{id}`  
  Elimina una venta por su ID.

- **GET** `/api/sales`  
  Obtiene todas las ventas.

- **POST** `/api/sales`  
  Crea una nueva venta.

---

### **Product Controller**
Permite la gestión de productos. Los endpoints disponibles son:

- **GET** `/api/products/{id}`  
  Obtiene un producto específico por su ID.

- **PUT** `/api/products/{id}`  
  Actualiza un producto existente.

- **DELETE** `/api/products/{id}`  
  Elimina un producto.

- **GET** `/api/products`  
  Obtiene todos los productos.

- **POST** `/api/products`  
  Crea un nuevo producto.

---

### **Customer Controller**
Permite la gestión de clientes. Los endpoints disponibles son:

- **GET** `/api/customers/{id}`  
  Obtiene un cliente específico por su ID.

- **PUT** `/api/customers/{id}`  
  Actualiza un cliente existente.

- **DELETE** `/api/customers/{id}`  
  Elimina un cliente.

- **GET** `/api/customers`  
  Obtiene todos los clientes.

- **POST** `/api/customers`  
  Crea un nuevo cliente.

---

## Esquemas

### **Product**
Representa un producto disponible para la venta. Los atributos son:

- `name` (string) *(requerido)*  
  Nombre del producto.  
  **Ejemplo**: `"Producto A"`

- `description` (string)  
  Descripción del producto.  
  **Ejemplo**: `"Descripción detallada del Producto A"`

- `price` (number) *(requerido)*  
  Precio del producto.  
  **Ejemplo**: `100.0`

- `stock` (integer) *(requerido)*  
  Cantidad en inventario.  
  **Ejemplo**: `50`

---

### **Customer**
Representa un cliente. Los atributos son:

- `name` (string) *(requerido)*  
  Nombre del cliente.  
  **Ejemplo**: `"Cliente 1"`

- `email` (string) *(requerido)*  
  Correo electrónico del cliente.  
  **Ejemplo**: `"cliente1@example.com"`

- `phone` (string)  
  Teléfono del cliente.  
  **Ejemplo**: `"123456789"`

- `address` (string)  
  Dirección del cliente.  
  **Ejemplo**: `"Dirección del Cliente 1"`

---

### **Sale**
Representa una venta realizada. Los atributos son:

- `saleDate` (string, formato `date-time`) *(requerido)*  
  Fecha y hora de la venta.

- `total` (number) *(requerido)*  
  Monto total de la venta.  
  **Ejemplo**: `200.0`

- `items` (array de `SaleItem`)  
  Lista de artículos vendidos en la venta.

---

### **SaleItem**
Representa un artículo en una venta. Los atributos son:

- `product` (Product) *(requerido)*  
  Producto asociado al artículo de venta.

- `quantity` (integer) *(requerido)*  
  Cantidad vendida.  
  **Ejemplo**: `2`

- `price` (number) *(requerido)*  
  Precio del artículo.  
  **Ejemplo**: `100.0`

---

## Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/DanielWar01/api-ventas.git
