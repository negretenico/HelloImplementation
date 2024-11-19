import express from "express";
import { fetchMockedData } from "./src/util/data_layer.js";
const app = express();
const port = 3000;
const startServer = async () => {
  // Fetch the mocked data before starting the server

  // Handle the route to return top videos
  app.get("/views/top", async (req, res) => {
    const data = await fetchMockedData();
    res.send(data);
  });

  // Start the server
  app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
  });
};

// Call the function to start the server
startServer();
