use lambda;
db.createCollection("lambda");
db.lambda.createIndex({"name":1}, { unique: true } );
// use admin;
// db.createUser(
//     {
//         user: "lambda",
//         pwd: "lambda",
//         roles: [ { role: "readWrite", db: "lambda" } ]
//     }
// );