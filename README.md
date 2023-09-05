# Jaya Code Challenge
Repository containing a coding challenge for Jaya recruitment process.

## The project consists of an REST API to manage the wishlists from an e-commerce clients. This API must allow it's users to:
- Add products to a wishlist
- Remove a given product from a wishlist
- Consult if a given product is in the wishlist
- Consult all the products from a given wishlist

### Configure database (docker compose needed):

- Install MongoDB image and start container
> **docker compose up -d**
- Access your image bash
> **docker exec -it local-mongo-db bash**
- Access mongo shell
> **mongosh admin -u root -p root**

#### In MondoBD shell you can now create 'wishlist' and 'product' collections and start testing the application


#### To restart databse in the future without needing to remove the existent container, use:
> docker start local-mongo-db 





