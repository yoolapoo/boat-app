print('Start #################################################################');

db.roles.insertMany([
    { name: "ROLE_USER" },
    { name: "ROLE_MODERATOR" },
    { name: "ROLE_ADMIN" },
]);
db.users.insertMany([
            { username: "admin",email: "admin@yopmail.com",password: "12345678", roles: ["user","mod"]},
            { username: "user",email: "user@yopmail.com",password: "12345678", roles: ["user"]}
        ]);
print('END #################################################################');
