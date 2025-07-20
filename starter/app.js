const express = require("express");
const app = express();
const tasks = require("./routes/tasks");
const PORT = 5000;
const connectDB = require("./db/connect");
require("dotenv").config();

// middlewere
app.use(express.static("public"));
app.use(express.json());

//routers

app.use("/api/v1/tasks", tasks);

const start = async () => {
  try {
    await connectDB(process.env.MONGO_URI);
    app.listen(PORT, console.log(`server is listening on port ${PORT} ....`));
  } catch (error) {
    console.log(error);
  }
};

start();
