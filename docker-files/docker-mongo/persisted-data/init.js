//When a container is started for the first time it will execute files with extensions
// .sh and .js that are found in /docker-entrypoint-initdb.d

db = db.getSiblingDB('rupesh');

db.createCollection('products');

db.products.insertMany(
    [
        {
            "name": "iphone",
            "price": "1200"
        },
        {
            "name": "ipad",
            "price": "800"
        },
        {
            "name": "mac",
            "price": "3000"
        }
    ]
)