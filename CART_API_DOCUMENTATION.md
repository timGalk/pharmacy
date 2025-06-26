# Cart API Documentation

This document describes the REST API endpoints for cart management in the pharmacy application.

## Base URL
All cart endpoints are prefixed with `/api/carts`

## Endpoints

### 1. Get Cart by Cart ID
**GET** `/api/carts/{cartId}`

Retrieves a cart by its ID.

**Path Parameters:**
- `cartId` (Long): The ID of the cart

**Response:**
```json
{
  "id": 1,
  "userId": 123,
  "itemList": [
    {
      "id": 1,
      "medicine": {
        "id": 1,
        "name": "Aspirin",
        "description": "Pain reliever",
        "price": 5.99,
        "stockQuantity": 100,
        "expirationDate": "2025-12-31",
        "image": "aspirin.jpg"
      },
      "quantity": 2
    }
  ]
}
```

### 2. Get Cart by User ID
**GET** `/api/carts/user/{userId}`

Retrieves a cart for a specific user.

**Path Parameters:**
- `userId` (Long): The ID of the user

**Response:** Same as above

### 3. Create Cart for User
**POST** `/api/carts/user/{userId}`

Creates a new cart for a user.

**Path Parameters:**
- `userId` (Long): The ID of the user

**Response:** Same as above (201 Created)

### 4. Add Item to Cart
**POST** `/api/carts/items`

Adds an item to a cart. If the item already exists, the quantity will be increased.

**Request Body:**
```json
{
  "cartId": 1,
  "medicineId": 1,
  "quantity": 2
}
```

**Validation:**
- `cartId`: Required, must be a valid cart ID
- `medicineId`: Required, must be a valid medicine ID
- `quantity`: Required, must be at least 1

**Response:** Updated cart DTO

### 5. Update Cart Item Quantity
**PATCH** `/api/carts/items/{itemId}`

Updates the quantity of a specific item in the cart.

**Path Parameters:**
- `itemId` (Long): The ID of the cart item

**Request Body:**
```json
{
  "quantity": 3
}
```

**Validation:**
- `quantity`: Required, must be at least 1

**Response:** Updated cart DTO

### 6. Remove Item from Cart
**DELETE** `/api/carts/items/{itemId}`

Removes a specific item from the cart.

**Path Parameters:**
- `itemId` (Long): The ID of the cart item

**Response:** Updated cart DTO

### 7. Clear Cart
**DELETE** `/api/carts/{cartId}/items`

Removes all items from a cart.

**Path Parameters:**
- `cartId` (Long): The ID of the cart

**Response:** Empty cart DTO

## Error Responses

All endpoints may return the following error responses:

### 404 Not Found
```json
{
  "message": "Cart with id 1 not found"
}
```

### 400 Bad Request
```json
{
  "message": "Validation failed",
  "errors": [
    "Quantity must be at least 1"
  ]
}
```

### 500 Internal Server Error
```json
{
  "message": "Cart already exists for user 123"
}
```

## Usage Examples

### Creating a cart and adding items:

1. **Create cart for user:**
   ```bash
   POST /api/carts/user/123
   ```

2. **Add medicine to cart:**
   ```bash
   POST /api/carts/items
   Content-Type: application/json
   
   {
     "cartId": 1,
     "medicineId": 1,
     "quantity": 2
   }
   ```

3. **View cart:**
   ```bash
   GET /api/carts/1
   ```

4. **Update item quantity:**
   ```bash
   PATCH /api/carts/items/1
   Content-Type: application/json
   
   {
     "quantity": 3
   }
   ```

5. **Remove item:**
   ```bash
   DELETE /api/carts/items/1
   ```

6. **Clear entire cart:**
   ```bash
   DELETE /api/carts/1/items
   ```

## Business Logic

- Each user can have only one cart
- When adding an item that already exists in the cart, the quantities are summed
- Cart items are automatically removed when the cart is deleted (cascade)
- All operations are transactional to ensure data consistency 