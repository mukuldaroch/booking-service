import express from "express";
import v1Routes from "./v1/v1.routes.js";

import { testSave, testFetch } from "../controllers/test.controller.js";

const router = express.Router();

router.use("/v1", v1Routes);

router.post("/testsave", testSave);
router.get("/testfetch", testFetch);

export default router;
