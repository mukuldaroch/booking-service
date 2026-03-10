import express from "express";
const router = express.Router();

import { info, CreateBooking } from "../../controllers/index.js";

router.get("/info", info);
router.post("/booking", CreateBooking);

export default router;
