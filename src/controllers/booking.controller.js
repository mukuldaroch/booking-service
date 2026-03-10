import { StatusCodes } from "http-status-codes";
import BookingRepository from "../repositories/booking.repository.js";

const bookingRepository = new BookingRepository();

export const info = (req, res) => {
    return res.status(StatusCodes.OK).json({
        sucess: "true",
        message: "API is live",
        error: {},
        data: {},
    });
};

export async function CreateBooking(req, res) {
    try {
        const { userId, eventId, totalPrice } = req.body;

        if (!userId || !eventId || !totalPrice) {
            return res.status(StatusCodes.BAD_REQUEST).json({
                message: "userId, eventId and totalPrice are required",
            });
        }

        const booking = await bookingRepository.createBooking({
            userId,
            eventId,
            totalPrice,
        });

        return res.status(StatusCodes.OK).json({
            message: "Booking created successfully",
            data: booking,
        });
    } catch (error) {
        console.error(error);
        return res.status(StatusCodes.INTERNAL_SERVER_ERROR).json({
            message: "Something went wrong",
        });
    }
}
