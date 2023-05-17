//create db
db = db.getSiblingDB('job');

//use db;
//create user
db.createUser({
    user: "job_user",
    pwd: "job_password",
    roles:[
           {role: "readWrite", db: "job"}
    ]
});

//create collection
db.createCollection('job');

//insert data in the collection
db.job.insertMany([
      {
          description: "senior java dev",
          company: "amazon",
          skills: ["java", "spring", "docker"],
          salary: 10000,
          isRemote: false
      },
      {
                description: "junior java dev",
                company: "aws",
                skills: ["java"],
                salary: 1000,
                isRemote: false
      },
      {
                description: "data engineer",
                company: "google",
                skills: ["python", "spark", "docker"],
                salary: 15000,
                isRemote: true
      },
      {
                description: "Manager",
                company: "Virtusa",
                skills: ["java", "spring", "docker"],
                salary: 100000,
                isRemote: false
      },
      {
                description: "Tester",
                company: "ABB",
                skills: ["java", "selenium", "docker"],
                salary: 1000,
                isRemote: false
      }
]);

