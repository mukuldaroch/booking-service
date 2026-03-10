import { Prisma } from "../config/index.js";

class BookingRepository {
    async createBooking(data) {
        return Prisma.booking.create({
            data: {
                userId: data.userId,
                eventId: data.eventId,
                status: "PENDING",
                totalPrice: data.totalPrice,
            },
        });
    }

    async findById(id) {
        return Prisma.booking.findUnique({
            where: { id },
        });
    }

    async updateStatus(id, status) {
        return Prisma.booking.update({
            where: { id },
            data: { status },
        });
    }

    async findByUser(userId) {
        return Prisma.booking.findMany({
            where: { userId },
            orderBy: { createdAt: "desc" },
        });
    }

    async findExpiredBookings(expirationTime) {
        return Prisma.booking.findMany({
            where: {
                status: "PENDING",
                createdAt: {
                    lt: expirationTime,
                },
            },
        });
    }
}

export default BookingRepository;
