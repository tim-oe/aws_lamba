use multi;
db.createCollection("multi");
db.multi.createIndex({"name":1}, { unique: true } );
// use admin;
db.createUser(
    {
        user: "multi",
        pwd: "multi",
        roles: [ { role: "readWrite", db: "multi" } ]
    }
);