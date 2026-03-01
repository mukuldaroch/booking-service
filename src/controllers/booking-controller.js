import { StatusCodes } from "http-status-codes";

export const info = (req, res) => {
    return res.status(StatusCodes.OK).json({
        sucess: "true",
        message: "API is live",
        error: {},
        data: {},
    });
};
