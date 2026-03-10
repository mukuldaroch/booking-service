import { StatusCodes } from "http-status-codes";
import { Prisma } from "../config/index.js";
// save data
export async function testSave(req, res) {

    try {
        const { name } = req.body || {};

        if (!name) {
            return res.status(400).json({
                message: "name is required"
            });
        }

        const data = await Prisma.booking.create({
            data: { name }
        });

        return res.json({
            message: "Data saved successfully",
            data
        });

    } catch (error) {
        console.error(error);
        res.status(500).json({
            message: "Something went wrong"
        });
    }
}

// fetch data
export async function testFetch(req, res) {
    try {
        const data = await Prisma.booking.findMany();

        return res.json({
            message: "Data fetched successfully",
            data,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({
            message: "Something went wrong",
        });
    }
}
