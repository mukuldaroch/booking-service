import express from "express";

import { PORT, Logger } from "./config/index.js";
import apiRoutes from "./routes/index.js";

const app = express();

/* MIDDLEWARE */
app.use(express.json()); // <-- this parses JSON request bodies

/* ROUTES */
app.use("/api", apiRoutes);

app.listen(PORT, () => {
    console.log(`Successfully started the server on PORT : ${PORT}`);
    Logger.info(`Successfully started the server`, `root`, {});
});
