import * as z from "zod";

const bookingItemSchema = z.object({
    ticketTypeId: z.string().uuid(),
    quantity: z.number().int().positive(),
});

const createBookingSchema = z.object({
    userId: z.string().uuid(),
    eventId: z.string().uuid(),
    items: z.array(bookingItemSchema).min(1),
});
