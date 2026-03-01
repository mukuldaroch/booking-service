import express from "express";
const router = express.Router();

import { info } from "../../controllers/index.js";

router.get('/info', info)

export default router;
