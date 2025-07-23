const express = require("express");
const app = express();
const tasks = require("./routes/tasks");
const connectDB = require("./db/connect");
const notFound = require("./middleware/not-found");
const errorHandlerMiddleware = require("./middleware/error-handler");
require("dotenv").config();

// middlewere
app.use(express.static("public"));
app.use(express.json());

//routers

app.use("/api/v1/tasks", tasks);

app.use(notFound); 
app.use(errorHandlerMiddleware);

//port
const PORT = 5000;


const start = async () => {
  try {
    await connectDB(process.env.MONGO_URI);
    app.listen(PORT, console.log(`server is listening on port ${PORT} ....`));
  } catch (error) {
    console.log(error);
  }
};

start();
