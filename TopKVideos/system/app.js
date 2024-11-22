import express from "express";
import { fetchMockedData } from "./src/util/data_layer.js";
import { controller } from "./src/controller/controller.js";
const app = express();
const port = 3000;
const startServer = async () => {
  // Fetch the mocked data before starting the server

  // Handle the route to return top videos
  app.get("/views/top", async (req, res) => {
    try {
      // Fetch the mocked data
      const data = await fetchMockedData();
      // Extract query parameters for top K, startDate, and endDate
      const { k = 5, startDate = '', endDate = '' } = req.query;
      // Validate `k` parameter
      const topKValue = parseInt(k, 10);
      if (isNaN(topKValue) || topKValue <= 0) {
          return res.status(400).send({ error: "Invalid 'k' value. Must be a positive integer." });
      }

      // Pass the data and parameters to the controller
      const result = controller(data, topKValue, startDate, endDate);

      // Respond with the result
      res.status(200).send(result);
  } catch (error) {
      console.error("Error handling /views/top request:", error.message);
      res.status(500).send({ error: "An unexpected error occurred. Please try again later." });
  }
  });

  // Start the server
  app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
  });
};

// Call the function to start the server
startServer();
